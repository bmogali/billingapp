package com.billingapp.rest.dto;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity(name = "user")
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue
	@Column(name = "userId")
	private int userId;

	@Column(name = "Name", unique = true, length = 16, nullable = false)
	private String name;

	@Column(name = "Password", length = 80, nullable = false)
	private String password;

	@OneToMany(mappedBy="user")
	@Cascade(CascadeType.ALL)
	private Set<UserRoles> userRoles;

	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public Set<UserRoles> getUserRoles() {
		return userRoles;
	}


	public void setUserRoles(Set<UserRoles> userRoles) {
		this.userRoles = userRoles;
	}


	public String getName()
	{
		return this.name;
	}


	public void setName(String name)
	{
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

}