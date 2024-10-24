package kr.ac.kku.cs.wp.demo.user.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kr.ac.kku.cs.wp.demo.user.entity.User;
import kr.ac.kku.cs.wp.demo.user.dao.UserDAO;
import kr.ac.kku.cs.wp.demo.user.dao.UserDAOHiberanteImpl;

public class UserServiceImple implements UserService {
	private static final Logger logger = LogManager.getLogger(UserServiceImple.class);

	private UserDAO dao = new UserDAOHiberanteImpl();

    // 추가: UserDAO를 설정할 수 있는 setter 메서드
    public void setUserDAO(UserDAO dao) {
        this.dao = dao;
    }

	@Override
	public List<User> getUsers(User user) {
		// TODOAuto-generatedmethodstub
		return dao.getUsers(user);
	}

	@Override
	public User getUserById(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createUser(User user) {
		// TODO Auto-generated method stub
		
	}

}