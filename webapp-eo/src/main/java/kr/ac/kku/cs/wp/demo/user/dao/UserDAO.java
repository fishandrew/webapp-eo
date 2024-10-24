package kr.ac.kku.cs.wp.demo.user.dao;

import java.util.List;
import kr.ac.kku.cs.wp.demo.user.entity.User;

public interface UserDAO {
	public User getUserById(String userId);

	public User getUser(User user);

	public User updateUser(User user);

	public void deleteUser(User user);

	public User createUser(User user);

	public List<User> getUsers(User user);

}
