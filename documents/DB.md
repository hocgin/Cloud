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


## Seeder使用方式
1. 继承`Seeder.class`, `run()`函数中实现处理过程
2. 在`MainSeeder.class`中的`getSeeders`函数装载`Seeder`