package kr.ac.kku.cs.wp.demo.user;

import java.util.List;

public class User {
	private String photoSRC= null;
	private String name =null;
	private String email =null;
	private List<String> userRole=null;
	private String id =null;
	private String password="null";
	private String status = null;
	private String role=null;
	
	
	public String getPhotoSRC() {
		return photoSRC;
	}
	public void setPhotoSRC(String photoSRC) {
		this.photoSRC = photoSRC;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getRoles() {
		return userRole;
	}
	public void setRoles(List<String> roles) {
		this.userRole = roles;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setRole(String role) {
		// TODO Auto-generated method stub
		this.role= role;
	}
	public String getRole() {
		// TODO Auto-generated method stub
		return role;
	}
	
	
}
