package com.example.demo.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.demo.entity.User;
import com.example.demo.utility.Util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	private String username;
	private String password;
	private String email;
	private Set<AuthorityDto> authorities;
	
	public UserDto() {
		authorities = new HashSet<>();
	}
	
	public UserDto(User user) {
		username = user.getUsername();
		password = user.getPassword();
		email = user.getEmail();
		authorities = user.getAuthorities()
				.stream()
				.map(Util::toAuthorityDto)
				.collect(Collectors.toUnmodifiableSet());
	}
	

}
