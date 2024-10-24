package kr.ac.kku.cs.wp.demo.user.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import kr.ac.kku.cs.wp.demo.user.dao.UserDAOHiberanteImpl;
import kr.ac.kku.cs.wp.demo.user.entity.User;

public class UserServiceImpleTest {
    private static final Logger logger = LogManager.getLogger(UserServiceImpleTest.class);

    @Test
    void testGetUsers() {
        UserServiceImple userService = new UserServiceImple();
        userService.setUserDAO(new UserDAOHiberanteImpl()); // 실제 구현체 사용
        
        List<User> users = userService.getUsers(null);
        assertNotNull(users); // Null이 아닌지 확인
        for (User user : users) {
            assertNotNull(user); // 각 User 객체가 Null이 아닌지 확인
            logger.debug("User ID: {}", user.getId()); // User ID 출력
        }
    }

}
