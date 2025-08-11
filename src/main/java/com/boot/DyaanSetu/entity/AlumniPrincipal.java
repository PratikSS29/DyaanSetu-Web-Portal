package com.boot.DyaanSetu.entity;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AlumniPrincipal implements UserDetails{

	private AlumniLogin login;
	
	public AlumniPrincipal(AlumniLogin login) {
		this.login=login;
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singleton(new SimpleGrantedAuthority("ROLE_ALUMNI"));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return login.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return login.getEmail();
	}

}
