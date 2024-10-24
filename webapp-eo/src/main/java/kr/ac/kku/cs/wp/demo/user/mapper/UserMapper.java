package kr.ac.kku.cs.wp.demo.user.mapper;
import java.util.List;  
import java.util.Map;  
import kr.ac.kku.cs.wp.demo.user.entity.User;
public interface UserMapper{ 
	public User getUserById(String id);  
	public List<User>getUsers(User user);  
	public void insertUser(User user);  
	public List<Map> getUserRole(String id); 
	}