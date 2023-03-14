package com.example.demo.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.Authority;
import com.example.demo.entity.User;

public class DemoUserDetails implements UserDetails {
	private String username;
	private String password;
	private List<Authority> authorities;
	private boolean isAccountNonExpired;
	private boolean isAccountNonLocked;
	private boolean isCredentialNonExpired;
	private boolean isEnable;
	
	public DemoUserDetails(User user) {
		username = user.getUsername();
		password = user.getPassword();
		authorities = user.getAuthorities();
		isAccountNonExpired = true;
		isAccountNonLocked = true;
		isCredentialNonExpired = true;
		isEnable = true;
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities
		.stream()
		.map(authority -> new SimpleGrantedAuthority(authority.getName()))
		.collect(Collectors.toUnmodifiableList());
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return isEnable;
	}
}
