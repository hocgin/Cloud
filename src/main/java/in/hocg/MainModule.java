package in.hocg;

import in.hocg.app.modules.HomeModule;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

/**
 * (๑`灬´๑)
 * Created by hocgin on 十一月28  028.
 */
@Modules(value = {HomeModule.class}, scanPackage = true)
@IocBy(type = ComboIocProvider.class,
        args = {
                "*js", "ioc/",
                "*anno", "in.hocg",
                "*tx",
                "*async",
                "*org.nutz.integration.quartz.QuartzIocLoader" // 任务队列
        })
@SetupBy(value = MainSetup.class) // 启动初始化数据库
public class MainModule {
}
