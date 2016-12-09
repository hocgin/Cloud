package in.hocg.app.plugins.shiro.authority;

import in.hocg.app.plugins.shiro.bean.Permission;
import in.hocg.app.plugins.shiro.bean.Role;
import in.hocg.app.plugins.shiro.bean.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.entity.Record;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.ContinueLoop;
import org.nutz.lang.Each;
import org.nutz.lang.ExitLoop;
import org.nutz.lang.LoopException;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.resource.Scans;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by hocgin on 16-12-6.
 */
@IocBean(name = "authorityService")
public class AuthorityServiceImpl implements AuthorityService {
	private static final Log log = Logs.get();
	
	@Inject
	protected Dao dao;
	
	public void initFormPackage(String pkg) {
		// 搜索@RequiresPermissions注解, 初始化权限表
		// 搜索@RequiresRoles注解, 初始化角色表
		final Set<String> permissions = new HashSet<>();
		final Set<String> roles = new HashSet<>();
		for (Class<?> clazz : Scans.me().scanPackage(pkg)) {
			for (Method method : clazz.getMethods()) {
				RequiresPermissions rp = method.getAnnotation(RequiresPermissions.class);
				if (rp != null && rp.value() != null) {
					for (String permission : rp.value()) {
						if (permission != null && !permission.endsWith("*"))
							permissions.add(permission);
					}
				}
				RequiresRoles rr = method.getAnnotation(RequiresRoles.class);
				if (rr != null && rr.value() != null) {
					Collections.addAll(roles, rr.value());
				}
			}
		}
		log.debugf("found %d permission", permissions.size());
		log.debugf("found %d role", roles.size());
		
		// 把全部权限查出来一一检查
		dao.each(Permission.class, null, new Each<Permission>() {
			public void invoke(int index, Permission ele, int length) throws ExitLoop, ContinueLoop, LoopException {
				permissions.remove(ele.getName());
			}
		});
		dao.each(Role.class, null, new Each<Role>() {
			public void invoke(int index, Role ele, int length) throws ExitLoop, ContinueLoop, LoopException {
				roles.remove(ele.getName());
			}
		});
		for (String permission : permissions) {
			addPermission(permission);
		}
		for (String role : roles) {
			addRole(role);
		}
	}
	
	public void checkBasicRoles(User admin) {
		// 检查一下admin的权限
		Role adminRole = dao.fetch(Role.class, Cnd.where("name", "=", Role.Name.Admin.name()));
		if (adminRole == null) {
			adminRole = addRole(Role.Name.Admin.name());
		}
		// admin账号必须存在与admin组
		if (0 == dao.count("t_user_role", Cnd.where("u_id", "=", admin.getId()).and("role_id", "=", adminRole.getId()))) {
			dao.insert("t_user_role", Chain.make("u_id", admin.getId()).add("role_id", adminRole.getId()));
		}
		// admin组必须有authority:* 也就是权限管理相关的权限
		List<Record> res = dao.query("t_role_permission", Cnd.where("role_id", "=", adminRole.getId()));
		OUT:
		for (Permission permission : dao.query(Permission.class, Cnd.where("name", "like", "authority:%").or("name", "like", "user:%").or("name", "like", "topic:%"), null)) {
			for (Record re : res) {
				if (Objects.equals(re.getString("permission_id"), permission.getId()))
					continue OUT;
			}
			dao.insert("t_role_permission", Chain.make("role_id", adminRole.getId()).add("permission_id", permission.getId()));
		}
	}
	
	public void addPermission(String permission) {
		Permission p = new Permission();
		p.setName(permission);
		p.setUpdateAt(new Date());
		p.setCreateAt(new Date());
		dao.insert(p);
	}
	
	public Role addRole(String role) {
		Role r = new Role();
		r.setName(role);
		r.setUpdateAt(new Date());
		r.setCreateAt(new Date());
		return dao.insert(r);
	}
}
