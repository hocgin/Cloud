package in.hocg.def.base.service;

import in.hocg.def.base.bean.SoftDeleted;
import org.nutz.dao.Cnd;

/**
 * Created by hocgin on 16-12-1.
 */
public abstract class SoftDeletedService<T extends SoftDeleted> extends TableService<T> {


    /**
     * 不包含被软删除的
     * @return
     */
    @Override
    public Cnd all() {
        return Cnd.NEW().and("delete_at", "==", null);
    }

    /**
     * 包含被软删除的
     * @return
     */
    public Cnd withTrashed() {
        return Cnd.NEW();
    }

    /**
     * 只查询被软删除的
     * @return
     */
    public Cnd onlyTrashed() {
        return Cnd.NEW().and("delete_at", "!=", null);
    }
    
    
    /**
     * 软删除
     * @param obj
     */
    public void softDelete(T obj) {
        obj.setDeleteAt(now());
        _update(obj);
    }
    
    /**
     * 软删除
     * @param id 主键
     */
    public void softDelete(long id) {
        softDelete(fetch(id));
    }
    
    /**
     * 软删除
     * @param name 主键
     */
    public void softDelete(String name) {
        softDelete(fetch(name));
    }
    
    
    /**
     * 撤销软删除
     * @param obj
     * @return
     */
    public void restore(T obj) {
        obj.setDeleteAt(null);
        _update(obj);
    }
    
    /**
     * 撤销软删除
     * @param id 主键
     */
    public void restore(long id) {
        restore(fetch(id));
    }
    
    /**
     * 撤销软删除
     * @param name 主键
     */
    public void restore(String name) {
        restore(fetch(name));
    }
    
}
