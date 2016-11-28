package in.hocg.database;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * (๑`灬´๑)
 * Created by hocgin on 十一月28  028.
 */
@IocBean
public class MainSeeder {
    @Inject
    private Dao dao;

    /**
     * 装载数据库预执行数据器
     * @return
     */
    public List<Seeder> getSeeders() {
        return new ArrayList<>(Arrays.asList(

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
