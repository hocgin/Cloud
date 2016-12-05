package in.hocg;

import in.hocg.app.modules.HomeModule;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.annotation.UrlMappingBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;
import org.nutz.plugins.apidoc.ApidocUrlMapping;
import org.nutz.plugins.apidoc.annotation.Api;
import org.nutz.plugins.apidoc.annotation.ApiMatchMode;

/**
 * (๑`灬´๑)
 * Created by hocgin on 十一月28  028.
 */
@Api(name="Cloud", description="The answer to life, the universe and everything", match= ApiMatchMode.ONLY)
@Modules(value = {HomeModule.class}, scanPackage = true)
@IocBy(type = ComboIocProvider.class,
        args = {
                "*js", "ioc/",
                "*anno", "in.hocg",
                "*tx",
                "*async",
                "*org.nutz.integration.quartz.QuartzIocLoader" // 任务队列
        })
@SetupBy(value = MainSetup.class) // 启动初始化数据库
@UrlMappingBy(ApidocUrlMapping.class) // api doc
public class MainModule {
}
