package in.hocg.def.base.bean;

import org.nutz.dao.entity.annotation.*;

import java.util.Date;

/**
 * Created by hocgin on 十一月28  028.
 */
public abstract class BaseTable extends BaseBean {
    @Name
    @Prev(els=@EL("$me.genID()"))
    private String id;

    @Column("create_at")
    @Prev(els = @EL("$me.now()"))
    @Comment("创建时间, 默认为当前时间")
    private Date createAt;

    @Column("update_at")
    @Comment("更新时间, 默认为NULL")
    private Date updateAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Date now() {
        return new Date();
    }

    public String genID(){
        return org.nutz.lang.random.R.UU16();
    }
}
