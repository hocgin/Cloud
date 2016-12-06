package in.hocg.def.base.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;

import java.util.Date;

/**
 * (๑`灬´๑)
 * Created by hocgin on 十一月28  028.
 * 安全删除接口
 */
public abstract class SoftDeleted extends BaseTable {
    @Column("delete_at")
    @Comment("删除时间, 默认为NULL")
    private Date deleteAt;

    public Date getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(Date deleteAt) {
        this.deleteAt = deleteAt;
    }

    public boolean hasDeleted() {
        return deleteAt != null;
    }
}
