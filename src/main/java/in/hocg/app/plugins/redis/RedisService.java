package in.hocg.app.plugins.redis;

import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.IocBean;

import static in.hocg.app.plugins.redis.interceptor.RedisInterceptor.jedis;

/**
 * (๑`灬´๑)
 * Created by hocgin on 十一月28  028.
 */
@IocBean
public class RedisService {
    
    /**
     * Redis 服务相关的 Key
     */
    public interface Service {
        String SHORT_URL_DOMAIN = "service.shortUrl.domain";
        String FILE_KEEP_PATH = "service.fileKeep.path";
        String FILE_TRASH_PATH = "service.fileTrash.path";
    }
    
    @Aop("redis")
    public void set(String key, String val) {
        jedis().set(key, val);
    }

    @Aop("redis") // 加上这个拦截器后jedis()才能返回Jedis实例哦
    public String get(String key) {
        return jedis().get(key);
    }
    
    
    /**
     * 获取文件上传目录
     * @return
     */
    public String getUploadDirectory() {
        return get(RedisService.Service.FILE_KEEP_PATH);
    }
    
    
    
    /**
     * 获取文件回收站目录
     * @return
     */
    public String getTrashDirectory() {
        return get(RedisService.Service.FILE_TRASH_PATH);
    }
}
