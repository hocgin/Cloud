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