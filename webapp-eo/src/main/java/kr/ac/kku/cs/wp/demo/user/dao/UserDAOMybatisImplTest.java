package kr.ac.kku.cs.wp.demo.user.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import kr.ac.kku.cs.wp.demo.user.entity.Role;
import kr.ac.kku.cs.wp.demo.user.entity.User;
import kr.ac.kku.cs.wp.demo.user.entity.UserRole;

public class UserDAOMybatisImplTest {
	private static final Logger logger = LogManager.getLogger(UserDAOMybatisImplTest.class);

	@Test
	void testGetUserById() {
		UserDAO dao = new UserDAOMybatisImpl();
		User user = dao.getUserById("kku_1001");
		assertNotNull(user);
		List<UserRole> urList = user.getUserRoles();
		for (UserRole userRole : urList) {
			User urUser = userRole.getUser();
			Role role = userRole.getRole();
			logger.debug("userid {},username {},rolename {}", urUser.getId(), urUser.getName(), role.getRole());
		}
	}

	@Test
	void testGetUsers() {
		UserDAO userDao = new UserDAOMybatisImpl();
		List<User> users = userDao.getUsers(null);
		assertNotNull(users);
		for (User user : users) {
			List<UserRole> urList = user.getUserRoles();
			for (UserRole userRole : urList) {
				User urUser = userRole.getUser();
				Role role = userRole.getRole();
				logger.debug("userid {},username {},rolename {}", urUser.getId(), urUser.getName(), role.getRole());
			}
		}
	}
}