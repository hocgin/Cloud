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
     * 只查询被删除的
     * @return
     */
    public Cnd onlyTrashed() {
        return Cnd.NEW().and("delete_at", "!=", null);
    }


}
