package in.hocg.def.service;

import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.IocBean;

import static in.hocg.def.interceptor.RedisInterceptor.jedis;

/**
 * (๑`灬´๑)
 * Created by hocgin on 十一月28  028.
 */
@IocBean
public class RedisService {
    @Aop("redis")
    public void set(String key, String val) {
        jedis().set(key, val);
    }

    @Aop("redis") // 加上这个拦截器后jedis()才能返回Jedis实例哦
    public String get(String key) {
        return jedis().get(key);
    }
}
