package in.hocg;

import in.hocg.app.bean.Setting;
import in.hocg.app.plugins.redis.RedisService;
import in.hocg.app.plugins.shiro.authority.AuthorityService;
import in.hocg.app.plugins.shiro.bean.Role;
import in.hocg.app.plugins.shiro.bean.User;
import in.hocg.app.service.SettingService;
import in.hocg.database.MainSeeder;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.entity.Record;
import org.nutz.dao.util.Daos;
import org.nutz.integration.quartz.NutQuartzCronJobFactory;
import org.nutz.ioc.Ioc;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

import static in.hocg.MainModule.FORCE_CREATE_TABLE;

/**
 * (๑`灬´๑)
 * Created by hocgin on 十一月28  028.
 */
public class MainSetup implements Setup {
    private static final Log log = Logs.get();

    @Override
    public void init(NutConfig nc) {
        Ioc ioc = nc.getIoc();
        Dao dao = ioc.get(Dao.class);
        Daos.createTablesInPackage(dao, "in.hocg", FORCE_CREATE_TABLE);
        _loadSenders(ioc);
        _initRedis(ioc);
        _initQuartz(ioc);
        _initAuthority(dao, ioc);
    }
	
	/**
	 * 初始化权限
	 * @param dao
	 * @param ioc
	 */
	private void _initAuthority(Dao dao, Ioc ioc) {
	    Role role = dao.fetch(Role.class, Cnd.where("name", "=", "admin"));
        Record fetch = dao.fetch("t_user_role", Cnd.where("role_id", "=", role.getId()));
        if (!fetch.isEmpty()) {
            User admin = dao.fetch(User.class, fetch.getString("u_id"));
	        if (admin != null) {
		        AuthorityService as = ioc.get(AuthorityService.class);
		        as.initFormPackage("in.hocg.app");
		        as.checkBasicRoles(admin);
	        } else {
		        log.warnf("[hocgin] admin user not found !!!");
	        }
        }
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
        for (Setting setting : service.query(Setting.Tag.Redis.name())) {
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
