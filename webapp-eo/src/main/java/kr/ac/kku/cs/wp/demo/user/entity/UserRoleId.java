package kr.ac.kku.cs.wp.demo.user.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import jakarta.persistence.*;

@Embeddable
public class UserRoleId implements Serializable {
	@Column(name = "user_id", length = 200)
	private String userId;

	@Column(name = "role_id", length = 200)
	private String roleId;

	// Constructors–default,fields
	public UserRoleId() {
	}

	public UserRoleId(String userId, String roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	// GettersandSetters
	// overrideobjectmethodequals,hashcode ..

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserRoleId that = (UserRoleId) o;
		return Objects.equals(userId, that.userId) && Objects.equals(roleId, that.roleId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, roleId);
	}
}
