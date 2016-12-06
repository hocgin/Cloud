package in.hocg.app.modules;

import in.hocg.Custom;
import in.hocg.app.plugins.apidoc.ApiDocResponse;
import in.hocg.app.plugins.shiro.bean.User;
import in.hocg.app.service.UserService;
import in.hocg.def.base.module.BaseModule;
import org.apache.shiro.SecurityUtils;
import org.nutz.integration.shiro.SimpleShiroToken;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.mvc.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by hocgin on 16-12-6.
 */
@At("/user")
@Ok("json:full")
public class UserModule extends BaseModule implements ApiDocResponse {
	
	@Inject
	UserService service;
	
	
	@POST
	@At("/sign-in")
	public Object signIn(String name, String password, HttpSession session) {
		User user = service.fetchForName(name);
		if (service.login(user, password)) {
			SecurityUtils.getSubject().login(new SimpleShiroToken(user.getId()));
			session.setAttribute(Custom.Session.User, user);
			return true;
		}
		return false;
	}
	
	@GET
	@At("/sign-out")
	public void signOut(HttpSession session) {
		SecurityUtils.getSubject().logout();
		session.removeAttribute(Custom.Session.User);
	}
	
	@GET
	@At("/sign-up")
	public Object signUp(@Param("..") User user) {
		if (user.getId() != null
				|| service.fetchForName(user.getName()) != null) { // 用户已存在
			return fail("用户已存在");
		} else {
			service._insert(user);
		}
		return success("注册成功");
	}
	
}
