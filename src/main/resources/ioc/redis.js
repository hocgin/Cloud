/**
 * Created by Moy on 十一月28  028.
 */
var ioc = {
    jedisPoolConfig : {
        type : "redis.clients.jedis.JedisPoolConfig",
        fields : {
            testWhileIdle : true, // 空闲时测试,免得redis连接空闲时间长了断线
            maxTotal : 100 // 一般都够了吧
        }
    },
    jedisPool : {
        type : "redis.clients.jedis.JedisPool",
        args : [
            {refer : "jedisPoolConfig"},
            // 从配置文件中读取redis服务器信息
            {java : "$conf.get('redis.host', 'localhost')"},
            {java : "$conf.getInt('redis.port', 6379)"},
            {java : "$conf.getInt('redis.timeout', 2000)"},
            {java : "$conf.get('redis.password')"},
            {java : "$conf.getInt('redis.database', 0)"}
        ],
        fields : {},
        events : {
            depose : "destroy" // 关闭应用时必须关掉呢
        }
    }
};