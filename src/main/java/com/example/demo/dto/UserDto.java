package com.example.demo.dto;

import java.util.List;
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
	private List<AuthorityDto> authorities;
	
	public UserDto() {
		
	}
	
	public UserDto(User user) {
		username = user.getUsername();
		password = user.getPassword();
		email = user.getEmail();
		authorities = user.getAuthorities()
				.stream()
				.map(Util::toAuthorityDto)
				.collect(Collectors.toUnmodifiableList());
	}
	

}
