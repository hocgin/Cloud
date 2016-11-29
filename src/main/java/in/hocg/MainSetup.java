package in.hocg;

import in.hocg.def.service.RedisService;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
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
        Daos.createTablesInPackage(dao, "in.hocg", false);
        _initRedis(ioc);
    }

    @Override
    public void destroy(NutConfig nc) {

    }

    private void _initRedis(Ioc ioc) {
        // redis 的加载
        RedisService redis = ioc.get(RedisService.class);
    }
}
