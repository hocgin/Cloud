package in.hocg.app.plugins.shiro.bean;

import com.sun.istack.internal.NotNull;
import in.hocg.def.base.bean.BaseTable;
import org.nutz.dao.entity.annotation.*;

/**
 * Created by hocgin on 16-12-6.
 */
@Table("t_permissions")
public class Permission  extends BaseTable {
    @Column("name")
    @Comment("权限命名;英文;唯一")
    @NotNull
    protected String name;
    @Column("alias")
    @Comment("别名")
    protected String alias;
    @Column("description")
    @Comment("描述")
    @ColDefine(type = ColType.VARCHAR, width = 500)
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
