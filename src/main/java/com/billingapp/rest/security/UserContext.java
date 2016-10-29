package com.billingapp.rest.security;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import com.billingapp.rest.dto.Role;
import com.billingapp.rest.dto.User;
import com.billingapp.rest.dto.UserRoles;

/** This object wraps {@link User} and makes it {@link UserDetails} so that Spring Security can use it. */
public class UserContext implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8494680453579220381L;
	private User user;

	public UserContext(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		Set<UserRoles> userRolesSet = user.getUserRoles(); 
		if (!CollectionUtils.isEmpty(userRolesSet)) {
			for (UserRoles userRoles : userRolesSet) {
				Role role = userRoles.getRole();
				if (null != role && StringUtils.isNotBlank(role.getName())) {
					authorities.add(new SimpleGrantedAuthority(role.getName()));
				}
			}
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		return this == o
			|| o != null && o instanceof UserContext
			&& Objects.equals(user, ((UserContext) o).user);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(user);
	}

	@Override
	public String toString() {
		return "UserContext{" +
			"user=" + user +
			'}';
	}
}
