package in.hocg.database;

import in.hocg.database.seeders.SettingsSeeder;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * (๑`灬´๑)
 * Created by hocgin on 十一月28  028.
 */
@IocBean
public class MainSeeder {
    @Inject
    private Dao dao;

    @Inject
    private SettingsSeeder settingsSeeder;

    /**
     * 装载数据库预执行数据器
     * @return
     */
    public List<Seeder> getSeeders() {
        return new ArrayList<>(Collections.<Seeder>singletonList(
                settingsSeeder
        ));
    }

    /**
     * 批量加载数据库数据
     */
    public void handler() {
        for (Seeder seeder : getSeeders()) {
            seeder.run(dao);
        }
    }

}
