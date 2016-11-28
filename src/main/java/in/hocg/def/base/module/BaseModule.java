package in.hocg.def.base.module;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;

/**
 * Created by hocgin on 十一月28  028.
 */
@IocBean
public abstract class BaseModule {

    public NutMap json() {
        return NutMap.NEW();
    }

    /**
     * json 格式 成功
     * @return
     */
    public NutMap success(String message, Object data) {
        return json().setv("code", "200")
                .setv("message", message)
                .setv("data", data);
    }

    public NutMap success(String message) {
        return success(message, null);
    }

    public NutMap success() {
        return success("成功", null);
    }

    /**
     * json 格式 失败
     * @return
     */
    public NutMap fail(String message, Object data) {
        return json().setv("code", "200")
                .setv("message", message)
                .setv("data", data);
    }

    public NutMap fail(String message) {
        return fail(message, null);
    }

    public NutMap fail() {
        return fail("失败", null);
    }
}
