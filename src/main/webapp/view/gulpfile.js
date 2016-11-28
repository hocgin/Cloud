`use strict`;
var gulp = require('gulp'),
    watch = require('gulp-watch'),
    /**
     * 重命名文件
     */
    rename = require('gulp-rename'),
    /**
     * 压缩css
     */
    cssmin = require('gulp-cssmin'),
    /**
     * 压缩js
     */
    uglify = require('gulp-uglify'),
    /**
     * 压缩image
     */
    imagemin = require('gulp-imagemin'),
    /**
     * sass 编译器
     */
    sass = require('gulp-sass'),
    /**
     * 实时浏览
     */
    browserSync = require('browser-sync').create(),
    /**
     * 设置浏览器版本自动处理浏览器前缀
     */
    autoPrefixer = require('gulp-autoprefixer'),
    /**
     * 合并多个js文件
     */
    concat = require('gulp-concat'),
    /**
     * 给页面引用url添加版本号，以清除页面缓存
     * Usa:
     * <link rel="stylesheet" href="css/style.css?rev=@@hash"/>
     * <script src="js/js-one.js?rev=@@hash"></script>
     * <img src="img/test.jpg?rev=@@hash" alt="" />
     */
    rev = require('gulp-rev-append'),
    jshint = require('gulp-jshint'),
    /**
     * Task 顺序执行
     * gulp.task('xxx', sequence('task1', ['task2.1', 'task2.2'], 'task3'));
     */
    sequence = require('gulp-sequence'),
    /**
     * eg. rm -rf
     */
    rimraf = require('rimraf'),
    /**
     * 删除文件
     */
    gulpRimraf = require('gulp-rimraf'),
    /**
     * 拷贝文件夹
     */
    copyDir = require('copy-dir'),
    /**
     * 文件流
     */
    fs = require('fs');
// ---------------------------------------------------


/**
 * NOTE 1:
 * gulp.task()
 * 加了return才是异步执行
 */
// ---------------------------------------------------
var path = {
    src: {
        dir: './src/',
        html: './src/',
        css: './src/css/',
        js: './src/js/',
        sass: './src/sass/',
        img: './src/img/',
        oth: './src/oth/',
        dist: './src/dist/',
    },
    build: {
        dir: './build/',
        html: './build/',
        css: './build/css/',
        js: './build/js/',
        sass: './build/sass/',
        img: './build/img/',
        oth: './build/oth/',
        dist: './build/dist/',
    },
    backup: './backup'
};

gulp.task('default-dev', ['sass-build:watch', 'browser-sync']);
/**
 * sass 编译任务
 */
gulp.task('sass-build', function () {
    var sassPath = path.src.sass + '**/*.scss';
    return gulp.src(sassPath)
        .pipe(sass().on('error', sass.logError))
        .pipe(gulp.dest(path.src.css));
});
/**
 * sass watch 编译任务
 * sass变化 -> 编译为css -> css 变化 -> auto-prefixer
 */
gulp.task('sass-build:watch', function () {
    var sassPath = path.src.sass + '**/*.scss';
    return gulp.src(sassPath)
    // .pipe(sass({outputStyle: 'compressed'}).on('error', sass.logError))
        .pipe(watch(sassPath))
        .pipe(sass().on('error', sass.logError))
        .pipe(autoPrefixer({
            browsers: ['last 2 versions', 'Android >= 4.0'],
            cascade: true, //是否美化属性值 默认：true 像这样：
            //-webkit-transform: rotate(45deg);
            //        transform: rotate(45deg);
            remove: true //是否去掉不必要的前缀 默认：true
        }))
        .pipe(gulp.dest(path.src.css));
});
/**
 * 自动浏览器前缀
 */
gulp.task('auto-prefixer', ['sass-build'], function () {
    var srcCss = path.src.css + '**/*.css';
    return gulp.src(srcCss).pipe(autoPrefixer({
        browsers: ['last 2 versions', 'Android >= 4.0'],
        cascade: true, //是否美化属性值 默认：true 像这样：
        //-webkit-transform: rotate(45deg);
        //        transform: rotate(45deg);
        remove: true //是否去掉不必要的前缀 默认：true
    })).pipe(gulp.dest(path.src.css));
});
/**
 * 实时浏览
 */
gulp.task('browser-sync', function () {
    var files = [
        path.src.dir + '**/*.html',
        path.src.dir + '**/*.css',
        path.src.dir + '**/*.js'
    ];
    browserSync.init(files, {
        server: {
            baseDir: path.src.dir
        }
    });
});
/**
 * 自动压缩 并 迁移到 ./build文件夹
 * [html,css,js,图片]
 **/
gulp.task('min', ['min-js', 'min-css', 'min-image', 'rev', 'mv-oth', 'mv-dist']);
gulp.task('min-js', ['concat-global-js'], function () {
    var srcJs = path.src.js + '**/*.js';
    return gulp.src(srcJs)
        .pipe(uglify())
        .pipe(rename({suffix: '.min'}))
        .pipe(gulp.dest(path.build.js));
});
gulp.task('min-css', ['sass-build'], function () {
    var srcCss = path.src.css + '**/*.css';
    return gulp.src(srcCss)
        .pipe(cssmin())
        .pipe(rename({suffix: '.min'}))
        .pipe(gulp.dest(path.build.css));
});
gulp.task('min-image', function () {
    var srcImg = path.src.img + '/**/*';
    return gulp.src(srcImg)
        .pipe(imagemin())
        .pipe(gulp.dest(path.build.img))
});
/**
 * 移动 oth文件夹内容至dist
 */
gulp.task('mv-oth', function () {
    var srcOth = path.src.oth + '/**/*';
    return gulp.src(srcOth)
        .pipe(gulp.dest(path.build.oth))
});
gulp.task('mv-dist', function () {
    var srcDist = path.src.dist + '/**/*';
    return gulp.src(srcDist)
        .pipe(gulp.dest(path.build.dist))
});
/**
 * 处理文件URL引入缓存
 */
gulp.task('rev', function () {
    var srcHtml = path.src.html + '**/*.html';
    gulp.src(srcHtml)
        .pipe(rev())
        .pipe(gulp.dest(path.build.html))
});
/**
 * 合并 全局js ./assert/js
 */
gulp.task('concat-global-js', function () {
    var allJs = [
        // path.src.js + 'app1.js',
        // path.src.js + 'app2.js',
    ];
    gulp.src(allJs)
        .pipe(concat('app.js'))//合并后的文件名
        .pipe(gulp.dest(path.src.js));
});
/**
 * JS代码校验
 */
gulp.task('jshint', function () {
    return gulp.src([
        path.src.js + '**/*.js'
    ]).pipe(jshint()).pipe(jshint.reporter('default'));
});
/**
 * 自动备份build目录到./backup目录
 */
gulp.task('auto-backup', function () {
    var date = new Date(),
        dirName = date.toISOString().split('T')[0].replace(/[\D]/g, ''),
        reg = /^\d+$/g,
        build = path.build.dir;
    dirName = path.backup + '/' + dirName;

    fs.exists(build, function (exists) {
        if (exists) {
            //备份目录是否存在
            fs.exists(dirName, function (exist) {
                if (!exist) {
                    backup();
                }
            });
        }
    });
    function backup() {
        fs.readdir(path.backup, function (err, data) {
            var dirs = [], l;
            if (data && data.length > 0) {
                for (var i = 0, j = data.length; i < j; i++) {
                    if (data[i].match(reg)) {
                        dirs.push(data[i]);
                    }
                }
                if (dirs.length > 2) {
                    for (i = 0, l = dirs.length; i < l - 1; i++) {
                        rimraf.sync(path.backup + dirs[i]);
                    }
                }
                rimraf.sync(dirName);
            }
            copyDir.sync(build, dirName);
        });
    }
});
/**
 * 先备份, 再清除dist文件夹
 */
gulp.task('clean-build', function () {
    console.log("具体问题 具体实现");
});
/**
 * 慎用..
 */
gulp.task('reset', function () {
    return gulp.src([
        path.backup + '**/*',
        path.build.dir + '**/*',
    ], { read: false }).pipe(gulpRimraf());
});