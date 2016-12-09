package in.hocg;

import in.hocg.app.beans.Setting;
import in.hocg.app.plugins.redis.RedisService;
import in.hocg.app.plugins.shiro.authority.AuthorityService;
import in.hocg.app.plugins.shiro.bean.Role;
import in.hocg.app.plugins.shiro.bean.User;
import in.hocg.app.services.SettingService;
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
 * Tomcat 初始化时执行..
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
        
        
	    log.info(String.format("[%s] 运行结束, Bye.", "MainSetup 初始化"));
    }
	
	/**
	 * - 启用 [@RequiresPermissions, @RequiresRoles]
	 * - admin权限检查
	 * @param dao
	 * @param ioc
	 */
	private void _initAuthority(Dao dao, Ioc ioc) {
		log.info(String.format("正在初始化[%s]..", "权限检查模块"));
		
	    Role role = dao.fetch(Role.class, Cnd.where("name", "=", Role.Name.Admin.name()));
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
     * 初始化数据库数据
     * @param ioc
     */
    private void _loadSenders(Ioc ioc) {
	    log.info(String.format("正在初始化[%s]..", "数据库数据"));
	    
        ioc.get(MainSeeder.class).handler();
    }

    /**
     * 提前Setting，初始化Redis
     * @param ioc
     */
    private void _initRedis(Ioc ioc) {
	    log.info(String.format("正在初始化[%s]..", "Redis"));
	    
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
	    log.info(String.format("正在初始化[%s]..", "定时任务列表"));
	    
        ioc.get(NutQuartzCronJobFactory.class);
    }
}
