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

## 文件模块
### 文件表
- 文件大小
- 原文件名
- 服务器文件名
- 文件类型
- 上传作者
- 文件状态[分享中/加密中]


### 分享表 
> 分享的种类 `密码分享`/`限时分享`/`无条件分享`


## Seeder使用方式
1. 继承`Seeder.class`, `run()`函数中实现处理过程
2. 在`MainSeeder.class`中的`getSeeders`函数装载`Seeder`