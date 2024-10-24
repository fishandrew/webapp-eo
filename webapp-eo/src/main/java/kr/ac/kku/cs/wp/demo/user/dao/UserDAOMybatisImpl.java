package kr.ac.kku.cs.wp.demo.user.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kr.ac.kku.cs.wp.demo.support.sql.MyBatisUtil;
import kr.ac.kku.cs.wp.demo.user.entity.Role;
import kr.ac.kku.cs.wp.demo.user.entity.User;
import kr.ac.kku.cs.wp.demo.user.entity.UserRole;
import kr.ac.kku.cs.wp.demo.user.entity.UserRoleId;
import kr.ac.kku.cs.wp.demo.user.mapper.UserMapper;

public class UserDAOMybatisImpl implements UserDAO {
	private static final Logger logger = LogManager.getLogger(UserDAOMybatisImpl.class);

	// UserDAOmethod구현
	@Override
	public User getUserById(String userId) {
		User user = null;
		SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
		try (SqlSession session = sqlSessionFactory.openSession()) {
			UserMapper mapper = session.getMapper(UserMapper.class);
			
			user = mapper.getUserById(userId);
			List<Map> userRoles = mapper.getUserRole(user.getId());
			List<UserRole> urList = new ArrayList<UserRole>();
			for (Map userRole : userRoles) {
				Role role = new Role();
				role.setId((String) userRole.get("roleId"));
				role.setRole((String) userRole.get("role"));
				UserRoleId uri = new UserRoleId();
				uri.setRoleId(role.getId());
				uri.setUserId(user.getId());
				UserRole ur = new UserRole();
				ur.setUser(user);
				ur.setRole(role);
				ur.setId(uri);
				urList.add(ur);
			}
			user.setUserRoles(urList);
		}
		return user;
	}
	
	@Override
	public List<User> getUsers(User pUser){  
		List<User>users = null; 
		SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory(); 
		
		try(SqlSession session = sqlSessionFactory.openSession()){ 
			UserMapper mapper=session.getMapper(UserMapper.class); 
			users=mapper.getUsers(pUser); 
			for(User user:users){ 
				List<Map> userRoles=mapper.getUserRole(user.getId());  
				List<UserRole> urList=new ArrayList<UserRole>(); 
				
				for(Map userRole:userRoles){  
					Role role=new Role(); 
					role.setId((String)userRole.get("roleId"));  
					role.setRole((String)userRole.get("role"));  
					UserRoleId uri=new UserRoleId(); 
					uri.setRoleId(role.getId()); 
					uri.setUserId(user.getId());  
					UserRole ur=new UserRole(); 
					ur.setUser(user);  
					ur.setRole(role);  
					ur.setId(uri); 
					urList.add(ur); 
					}  
				user.setUserRoles(urList); 
				} 
			} 
		return users;  
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
	public User createUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}