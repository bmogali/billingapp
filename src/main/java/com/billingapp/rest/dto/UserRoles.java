package com.billingapp.rest.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity(name = "useroles")
@Table(name = "userroles")
public class UserRoles {
	
	@Id
	@GeneratedValue
	@Column(name = "userRoleId")
	private int userRoleId;
	
	@OneToOne
	@JoinColumn(name = "roleId")
	@Cascade(CascadeType.DELETE)
	private Role role;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;
	

	public int getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
