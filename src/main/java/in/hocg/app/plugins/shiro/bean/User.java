package in.hocg.app.plugins.shiro.bean;

import com.sun.istack.internal.NotNull;
import in.hocg.def.base.bean.SoftDeleted;
import org.nutz.dao.entity.annotation.*;

import java.util.List;

/**
 * Created by hocgin on 16-12-6.
 */
@Table("t_users")
public class User extends SoftDeleted {

    @Column("name")
    @Comment("用户名")
    @NotNull
    protected String name;
    @Column("password")
    @NotNull
    @ColDefine(width=128)
    @Comment("密码")
    protected String password;
    @Column("salt")
    @NotNull
    @Comment("盐")
    protected String salt;

    @ManyMany(from="u_id", relation="t_user_role", target=Role.class, to="role_id")
    protected List<Role> roles;
    @ManyMany(from="u_id", relation="t_user_permission", target=Permission.class, to="permission_id")
    protected List<Permission> permissions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
