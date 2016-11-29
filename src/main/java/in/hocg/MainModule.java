package in.hocg;

import org.nutz.mvc.annotation.*;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

/**
 * (๑`灬´๑)
 * Created by hocgin on 十一月28  028.
 */
@Modules(value = {}, scanPackage = true)
@IocBy(type = ComboIocProvider.class,
        args = {
                "*js",
                "ioc/dao.js",
                "*anno",
                "*tx"
        })
@SetupBy(value = MainSetup.class) // 启动初始化数据库
public class MainModule {
}
