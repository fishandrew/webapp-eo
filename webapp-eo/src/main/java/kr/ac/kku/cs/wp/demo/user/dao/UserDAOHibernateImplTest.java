package kr.ac.kku.cs.wp.demo.user.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.junit.jupiter.api.Test;
import org.junit.Test;

import kr.ac.kku.cs.wp.demo.tools.message.MessageException;
import kr.ac.kku.cs.wp.demo.user.entity.User;
import kr.ac.kku.cs.wp.demo.user.entity.UserRole;

public class UserDAOHibernateImplTest {
	private static final Logger logger = LogManager.getLogger(UserDAOHibernateImplTest.class);

	@Test
	public void testGetUser() {
		UserDAO dao = new UserDAOHiberanteImpl();
		String userId = "kku_1001";
		User user = dao.getUserById(userId);
		assertEquals(userId, user.getId());
		List<UserRole> url = user.getUserRoles();
		for (UserRole userRole : url) {
			logger.debug("userid{},roleid{}", userRole.getUser().getId(), userRole.getRole().getRole());
		}
		logger.debug(user.getName());
	}

	@Test
	public void testGetUsers() {
		UserDAO dao = new UserDAOHiberanteImpl();
		User user = null;
		List<User> users = dao.getUsers(user);
		logger.debug(users.size());
		assertNotNull(users);
	}

	@Test
	public void testCreateUserUpdateDelete() {
		UserDAO dao = new UserDAOHiberanteImpl();
		User user = new User();
		// user.setName("홍길동");
		user.setEmail("test_1@kku.ac.kr");
		user.setStatus("재직중");
		Date now = new Date();
		user.setCreatedAt(now);
		user.setUpdatedAt(now);

		User createdUser = null;
		// validationchecknameisnull

		Exception excetpion = assertThrows(MessageException.class, () -> {
			dao.createUser(user);
		});
		logger.debug(excetpion.getMessage());
		assertEquals(
				"not-null property references a null or transient value : kr.ac.kku.cs.wp.demo.user.entity.User.name",
				excetpion.getMessage());

		user.setName("홍길동");
		createdUser = dao.createUser(user);
		assertNotNull(createdUser);

		logger.debug("createduser{},id{}", createdUser.getName(), createdUser.getId());
		createdUser.setName("심청이");
		User updatedUser = dao.updateUser(createdUser);
		assertEquals(updatedUser, createdUser);

		logger.debug("updatedusername{},id{}", updatedUser.getName(), updatedUser.getId());

		dao.deleteUser(updatedUser);
	}

}