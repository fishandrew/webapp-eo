package kr.ac.kku.cs.wp.demo.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kr.ac.kku.cs.wp.demo.support.sql.ConnectionPoolUtil;
import kr.ac.kku.cs.wp.demo.user.entity.User;
import kr.ac.kku.cs.wp.demo.user.entity.UserRole;
import kr.ac.kku.cs.wp.demo.user.entity.Role;
import kr.ac.kku.cs.wp.demo.user.entity.UserRoleId;

public class UserDAOJdbcImpl implements UserDAO {
    private static final Logger logger = LogManager.getLogger(UserDAOJdbcImpl.class);
    private final String querySelectById = "select * from user where id = ? ";
    private final String querySelectUserRole = "select * from user_role where user_id = ? ";
    private final String querySelectList = "select * from user ";

    // UserDAO method 구현 ...
    @Override  
    public User getUserById(String userId) {
        User user = new User();
        try (Connection conn = ConnectionPoolUtil.getConnection()) { 
            PreparedStatement pstmt = conn.prepareStatement(querySelectById); 
            PreparedStatement sPstmt = conn.prepareStatement(querySelectUserRole); 
            pstmt.setString(1, userId); 
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {  
                user.setId(rs.getString(1)); 
                user.setName(rs.getString(2)); 
                user.setEmail(rs.getString(3));  
                user.setPassword(rs.getString(4)); 
                user.setStatus(rs.getString(5));  
                
                sPstmt.setString(1, user.getId());  
                ResultSet rsur = sPstmt.executeQuery(); 
                List<UserRole> urList = new ArrayList<UserRole>(); 
                
                while (rsur.next()) {  
                    Role role = new Role(); 
                    role.setId(rsur.getString(2)); 
                    role.setRole(rsur.getString(3));  
                    
                    UserRoleId uri = new UserRoleId(); 
                    uri.setRoleId(role.getId());  
                    uri.setUserId(user.getId());  
                    
                    UserRole ur = new UserRole(); 
                    ur.setUser(user);  
                    ur.setRole(role); 
                    ur.setId(uri);  
                    urList.add(ur);  
                }  
                rsur.close(); 
                user.setUserRoles(urList);  
            }  
            rs.close();  
            sPstmt.close(); 
            pstmt.close(); 
        } catch(SQLException e) {  
            e.printStackTrace(); 
        }  
        return user;  
    }

    @Override  
    public List<User> getUsers(User pUser) { 
        List<User> users = new ArrayList<User>();  
        try (Connection conn = ConnectionPoolUtil.getConnection()) {  
            PreparedStatement pstmt = conn.prepareStatement(querySelectList); 
            PreparedStatement sPstmt = conn.prepareStatement(querySelectUserRole);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) { 
                User user = new User();  
                user.setId(rs.getString(1)); 
                user.setName(rs.getString(2)); 
                user.setEmail(rs.getString(3)); 
                user.setPassword(rs.getString(4));
                user.setStatus(rs.getString(5));  
                
                sPstmt.setString(1, user.getId());  
                ResultSet rsur = sPstmt.executeQuery();
                
                List<UserRole> urList = new ArrayList<UserRole>();
                
                while (rsur.next()) { 
                    Role role = new Role();
                    role.setId(rsur.getString(2)); 
                    role.setRole(rsur.getString(3)); 
                    
                    UserRoleId uri = new UserRoleId();
                    uri.setRoleId(role.getId());
                    uri.setUserId(user.getId());  
                    
                    UserRole ur = new UserRole();
                    ur.setUser(user); 
                    ur.setRole(role);
                    ur.setId(uri);  
                    urList.add(ur);
                }  
                rsur.close(); 
                user.setUserRoles(urList);
                users.add(user); 
            }
            rs.close(); 
            sPstmt.close();
            pstmt.close();
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return users;
    }
    @Override
    public User createUser(User user) {
       // TODO Auto-generated method stub
       return null;
    }
    public void deleteUser(User user) {
        // TODO Auto-generated method stub
     }
    public User updateUser(User user){
    	return null;
    }
    public User getUser(User user) {
    	return null;
    }
}
