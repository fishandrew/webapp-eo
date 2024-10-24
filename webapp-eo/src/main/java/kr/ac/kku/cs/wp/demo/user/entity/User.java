package kr.ac.kku.cs.wp.demo.user.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {
	@Id
	@Column(name = "id", length = 200, nullable = false)
	private String id;
	@Column(name = "name", length = 200, nullable = false)
	private String name;
	@Column(name = "email", length = 200, nullable = false)
	private String email;
	@Column(name = "password", length = 200, nullable = false)
	private String password;
	@Column(name = "status", length = 200, nullable = false)
	private String status;
	@Column(name = "photo", length = 200)
	private String photo;
	@Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private List<UserRole> userRoles;
	
	private List<String> roles;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	
	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public User(String id, String name, String email, String password, String status, String photo, Date createdAt,
			Date updatedAt, List<UserRole> userRoles) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.status = status;
		this.photo = photo;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.userRoles = userRoles;
		this.roles= roles;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	// Override equals method
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		User user = (User) o;
		return Objects.equals(id, user.id);
	}

	// Override hashCode method
	@Override
	public int hashCode() {
		return Objects.hash(id, name, email, password, status, photo, createdAt, updatedAt, userRoles);
	}

	// Constructors–default,fields
	// GettersandSetters
	// overrideobjectmethodequals,hashcode ...
}