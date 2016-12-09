package in.hocg.database.seeders;

import in.hocg.app.beans.Setting;
import in.hocg.app.plugins.redis.RedisService;
import in.hocg.app.services.SettingService;
import in.hocg.database.Seeder;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import static in.hocg.MainModule.DEBUG;

/**
 * Created by hocgin on 16-12-5.
 */
@IocBean
public class SettingsSeeder implements Seeder {

    @Inject
    SettingService settingService;

    @Override
    public void run(Dao dao) {
        settingService.put(Setting.Tag.Redis.name(), "system.domain", "http://localhost:8080/", "This is host domain;");
        settingService.put(Setting.Tag.Redis.name(), "system.debug", String.valueOf(DEBUG), "This is Debug;");
        
        settingService.put(Setting.Tag.Redis.name(), RedisService.Service.SHORT_URL_DOMAIN, "http://localhost:8080/s/", "This is Short Url domain;");
    }
}
