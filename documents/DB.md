## 公有
### BaseTable
id `一般采用 UUID()`
createAt `默认为创建时间`
updateAt `默认为**Null**`

### SoftDeleted `extends BaseTable`
deleteAt `默认为**Null**`

## 短链模块(`short-url`) `SoftDeleted`
- code `/u/{code}, 短链**唯一**识别码`
- url  `code映射的url,格式【协议://域名:端口/路径?参数】`

## 文件管理模块
### 文件表
- 文件MD5
- 文件大小
- 原文件名
- 服务器文件名
- 文件类型[..]

### 网盘-uid
- 文件类型[目录/ ..]
- 父目录 `null为根级`
- 文件表id `目录类型为null`
- 拥有者
- 访问密码


### 分享表 
> 分享的种类 `密码分享`/`限时分享`/`无条件分享`
- `外键` 网盘表ID
- 密码
- 限时


## Seeder使用方式
1. 继承`Seeder.class`, `run()`函数中实现处理过程
2. 在`MainSeeder.class`中的`getSeeders`函数装载`Seeder`