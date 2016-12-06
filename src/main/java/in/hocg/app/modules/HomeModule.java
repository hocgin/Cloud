package in.hocg.app.modules;

import in.hocg.def.base.module.BaseModule;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.plugins.apidoc.annotation.Api;
import org.nutz.plugins.apidoc.annotation.ApiMatchMode;

/**
 * (๑`灬´๑)
 * Created by hocgin on 十一月28  028.
 */
@Api(name = "in.hocg.app.modules.HomeModule", description = "顶级路径", match= ApiMatchMode.NONE)
public class HomeModule extends BaseModule {
    private static final Log log = Logs.get();

    /**
     * @api {GET} /index 测试apidoc
     * @apiGroup propertyManage
     * @apiVersion 1.0.0
     *
     * @apiParam {PropertyManagementMessage}   propertyManagementMessage           消息对象
     */
    @GET
    @At("/index")
    public void index() {
    }


    @GET
    @At("/permission")
    @RequiresUser
    public void permission() {
        System.out.println("[permission OK]");
    }

}
