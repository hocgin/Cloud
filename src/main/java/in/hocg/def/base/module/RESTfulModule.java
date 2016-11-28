package in.hocg.def.base.module;

import org.nutz.mvc.annotation.*;

/**
 * Created by hocgin on 十一月28  028.
 */
public abstract class RESTfulModule extends BaseModule {

    /**
     * 索引行为
     * @return VIEW
     */
    @GET
    @At("/")
    public abstract String index();

    /**
     * 创建行为
     * @return VIEW
     */
    @GET
    @At("/create")
    public abstract String create();

    /**
     * 保存行为
     * @return VIEW
     */
    @POST
    @At("/")
    public abstract String store();


    /**
     * 显示行为
     * @return VIEW
     */
    @GET
    @At("/?")
    public abstract String show(String id);

    /**
     * 编辑行为
     * @return
     */
    @GET
    @At("/?")
    public abstract String edit(String id);


    /**
     * 更新行为
     * @return
     */
    @PUT
    @At("/?")
    public abstract String update(String id);


    /**
     * 删除行为
     * @return
     */
    @DELETE
    @At("/?")
    public abstract String destroy(String id);
}
