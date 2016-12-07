package in.hocg.app.services;

import in.hocg.app.plugins.shiro.bean.User;
import in.hocg.def.base.service.SoftDeletedService;
import org.nutz.dao.Cnd;
import org.nutz.lang.Lang;

import java.util.Objects;

/**
 * Created by hocgin on 16-12-6.
 */
public class UserService extends SoftDeletedService<User> {
	
	/**
	 * 检查密码
	 * @param name 用户名
	 * @param inputPassword 登录密码
	 * @return
	 */
	public boolean verifyPassword(String name, String inputPassword) {
		User user = fetchForName(name);
		return verifyPassword(user, inputPassword);
	}
	
	/**
	 * 检查密码
	 * @param user 用户对象
	 * @param inputPassword 登录密码
	 * @return
	 */
	public boolean verifyPassword(User user, String inputPassword) {
		return user != null
				&& _checkPassword(user.getPassword(), user.getSalt(), inputPassword);
	}
	
	/**
	 * 登陆专用
	 * @param user
	 * @param inputPassword
	 * @return
	 */
	public boolean login(User user, String inputPassword) {
		if (verifyPassword(user, inputPassword)) {
			_updateNow(user, "update_at");
			return true;
		}
		return false;
	}
	
	/**
	 * 使用name查找用户
	 * @param name
	 * @return
	 */
	public User fetchForName(String name) {
		Cnd cnd = all().and("name", "=", name);
		return fetch(cnd);
	}
	
	
	/**
	 * 密码加盐算法
	 * @param password 密码
	 * @param salt 盐
	 * @return
	 */
	public String _password(String password, String salt) {
		return Lang.md5(String.format("%s@%s", password, salt));
	}
	
	/**
	 * 校验密码
	 * @param password 密码
	 * @param salt 盐
	 * @param inputPassword 待校验密码
	 * @return
	 */
	public boolean _checkPassword(String password, String salt, String inputPassword) {
		return Objects.equals(password, _password(inputPassword, salt));
	}
}
