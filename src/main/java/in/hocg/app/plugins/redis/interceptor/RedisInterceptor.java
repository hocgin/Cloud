package in.hocg.app.plugins.redis.interceptor;

import in.hocg.app.plugins.redis.RedisService;
import org.nutz.aop.InterceptorChain;
import org.nutz.aop.MethodInterceptor;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * (๑`灬´๑)
 * Created by hocgin on 十一月28  028.
 *
 * Usa:
 *  @see RedisService
 */
@IocBean(name = "redis")
public class RedisInterceptor implements MethodInterceptor {
    @Inject
    private JedisPool jedisPool;

    static ThreadLocal<Jedis> TL = new ThreadLocal<>();

    @Override
    public void filter(InterceptorChain chain) throws Throwable {
        if (TL.get() != null) {
            chain.doChain();
            return;
        }
        try (Jedis jedis = jedisPool.getResource()) {
            TL.set(jedis);
            chain.doChain();
        } finally {
            TL.remove();
        }
    }

    public static Jedis jedis() {
        return TL.get();
    }
}
