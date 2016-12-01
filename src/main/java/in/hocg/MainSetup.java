package in.hocg;

import in.hocg.app.bean.Setting;
import in.hocg.app.service.SettingService;
import in.hocg.database.MainSeeder;
import in.hocg.def.service.RedisService;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.integration.quartz.NutQuartzCronJobFactory;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

/**
 * (๑`灬´๑)
 * Created by hocgin on 十一月28  028.
 */
public class MainSetup implements Setup {
    @Override
    public void init(NutConfig nc) {
        Ioc ioc = nc.getIoc();
        Dao dao = ioc.get(Dao.class);
        Daos.createTablesInPackage(dao, "in.hocg", true);
        _loadSenders(ioc);
        _initRedis(ioc);
        _initQuartz(ioc);
    }

    @Override
    public void destroy(NutConfig nc) {
    }

    /**
     * 初始化数据库
     * @param ioc
     */
    private void _loadSenders(Ioc ioc) {
        ioc.get(MainSeeder.class).handler();
    }

    /**
     * 提前Setting，初始化Redis
     * @param ioc
     */
    private void _initRedis(Ioc ioc) {
        SettingService service = ioc.get(SettingService.class);
        RedisService redis = ioc.get(RedisService.class);
        for (Setting setting : service.query("redis")) {
            redis.set(setting.getKey(), setting.getValue());
        }
    }

    /**
     * 初始化Quartz
     * @param ioc
     */
    private void _initQuartz(Ioc ioc) {
        ioc.get(NutQuartzCronJobFactory.class);
    }
}