package in.hocg.app.plugins.shiro.bean;

import in.hocg.def.base.bean.BaseTable;
import org.nutz.dao.entity.annotation.*;

import java.util.List;

/**
 * Created by hocgin on 16-12-6.
 */
@Table("t_roles")
public class Role  extends BaseTable {
    @Column("name")
    @Comment("角色命名")
    protected String name;
    @Column("alias")
    @Comment("别名")
    protected String alias;
    @Column("description")
    @Comment("描述")
    @ColDefine(type = ColType.VARCHAR, width = 500)
    private String description;
    @ManyMany(from="role_id", relation="t_role_permission", target=Permission.class, to="permission_id")
    protected List<Permission> permissions;

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

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
