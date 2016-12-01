package in.hocg.def.base.bean;

import org.nutz.json.Json;
import org.nutz.json.JsonFormat;
import org.nutz.lang.Mirror;

import java.io.Serializable;

/**
 * Created by hocgin on 十一月28  028.
 */
public abstract class BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 获取指定属性的值
     * @param attr
     * @return
     */
    public Object get(String attr) {
        return Mirror.me(this).getValue(this, attr);
    }

    /**
     * 设置指定属性的值
     * @param attr
     * @param value
     */
    public void set(String attr, Object value) {
        Mirror.me(this).setValue(this, attr, value);
    }

    public String toString() {
        return Json.toJson(this, JsonFormat.compact().setQuoteName(true).setIgnoreNull(false));
    }

}
