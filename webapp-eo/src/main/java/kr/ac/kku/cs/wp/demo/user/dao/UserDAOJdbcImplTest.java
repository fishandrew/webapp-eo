package kr.ac.kku.cs.wp.demo.user.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import kr.ac.kku.cs.wp.demo.user.entity.Role;
import kr.ac.kku.cs.wp.demo.user.entity.User;
import kr.ac.kku.cs.wp.demo.user.entity.UserRole;

public class UserDAOJdbcImplTest {
	private static final Logger logger = LogManager.getLogger(UserDAOJdbcImplTest.class);

	@Test
	void testGetUserById() {
		UserDAO userDao = new UserDAOJdbcImpl();
		String id = "kku_1001";
		User user = userDao.getUserById(id);
		assertNotNull(user);
		List<UserRole> urList = user.getUserRoles();
		for (UserRole userRole : urList) {
			User urUser = userRole.getUser();
			Role role = userRole.getRole();
			logger.debug("userid{},username{},rolename{}", urUser.getId(), urUser.getName(), role.getRole());
		}
	}

	@Test
	void testGetUsers() {
		UserDAO userDao = new UserDAOJdbcImpl();
		List<User> users = userDao.getUsers(null);
		assertNotNull(users);
		for (User user : users) {
			List<UserRole> urList = user.getUserRoles();
			for (UserRole userRole : urList) {
				User urUser = userRole.getUser();
				Role role = userRole.getRole();
				logger.debug("userid{},username{},rolename{}", urUser.getId(), urUser.getName(), role.getRole());
			}
		}
	}

}