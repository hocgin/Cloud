package in.hocg.def.base.service;

import in.hocg.def.base.bean.BaseTable;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdNameEntityService;

import java.util.Date;

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
            this._update(t1);
        } else {
            _insert(t);
        }
    }
    
    /**
     * 含更新时间
     * @param t
     * @return
     */
    public int _updateNow(T t) {
        t.setUpdateAt(now());
        return super._update(t);
    }
    
    /**
     * 含更新时间
     * @param obj
     * @param regex
     * @return
     */
    public int _updateNow(T obj, String regex) {
        obj.setUpdateAt(now());
        return super._update(obj, regex);
    }
    
    /**
     * 含更新时间
     * @param obj
     * @param regex
     * @return
     */
    @Override
    public T _updateWith(T obj, String regex) {
        obj.setUpdateAt(now());
        return super._updateWith(obj, regex);
    }
    
    /**
     * 含更新时间
     * @param obj
     * @param regex
     * @return
     */
    @Override
    public T _updateLinks(T obj, String regex) {
        obj.setUpdateAt(now());
        return super._updateLinks(obj, regex);
    }
    
    /**
     * 当前时间
     * @return
     */
    protected Date now() {
        return new Date();
    }
}
