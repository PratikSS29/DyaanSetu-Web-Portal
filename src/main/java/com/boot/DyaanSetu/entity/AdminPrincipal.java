package com.boot.DyaanSetu.entity;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AdminPrincipal implements UserDetails {

	AdminLogin adminLogin;
	
	public AdminPrincipal(AdminLogin adminLogin) {
		this.adminLogin=adminLogin;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	@Override
	public String getPassword() {
		return adminLogin.getPassword();
	}

	@Override
	public String getUsername() {
		return adminLogin.getEmail();
	}

}
