package in.hocg.def.base.service;

import in.hocg.def.base.bean.BaseTable;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdNameEntityService;

/**
 * Created by hocgin on 十一月28  028.
 */
@IocBean(fields = "dao")
public abstract class TableService<T extends BaseTable> extends IdNameEntityService<T> {

    /**
     * 获取Cnd
     *
     * @return
     */
    public Cnd all() {
        return Cnd.NEW();
    }


    /**
     * 保存 || 更新
     *
     * @param t
     */
    public void save(T t) {
        T t1;
        if (t.getId() != null && (t1 = _fetch(t)) != null) {
            _update(t1);
        } else {
            _insert(t);
        }
    }
}
