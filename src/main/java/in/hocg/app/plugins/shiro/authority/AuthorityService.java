package in.hocg.app.plugins.shiro.authority;

import in.hocg.app.plugins.shiro.bean.Role;
import in.hocg.app.plugins.shiro.bean.User;

/**
 * Created by hocgin on 16-12-6.
 */
public interface AuthorityService {
	/**
	 * 扫描RequiresPermissions和RequiresRoles注解
	 *
	 * @param pkg 需要扫描的package
	 */
	void initFormPackage(String pkg);
	
	/**
	 * 检查最基础的权限,确保admin用户-admin角色-(用户增删改查-权限增删改查)这一基础权限设置
	 *
	 * @param admin
	 */
	void checkBasicRoles(User admin);
	
	/**
	 * 添加一个权限
	 */
	void addPermission(String permission);
	
	/**
	 * 添加一个角色
	 */
	Role addRole(String role);
}
