package in.hocg.database.seeders;

import in.hocg.app.plugins.shiro.bean.Role;
import in.hocg.app.plugins.shiro.bean.User;
import in.hocg.app.services.UserService;
import in.hocg.database.Seeder;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.Arrays;

/**
 * Created by hocgin on 16-12-6.
 */
@IocBean
public class UserSeeder implements Seeder {
	
	@Inject
	UserService service;
	
	@Override
	public void run(Dao dao) {
		// permission
		
		// role admin
		Role role = new Role();
		role.setName(Role.Name.Admin.name());
		role.setAlias("管理员");
		role.setDescription("系统管理员;Default;");
		
		// hocgin
		User user = new User();
		user.setName("hocgin");
		user.setSalt("hocg.in");
		user.setPassword(service._password("hocgin", user.getSalt()));
		user.setRoles(Arrays.asList(role));
		dao.insertWith(user, "roles");
	}
}
