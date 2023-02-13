package com.example.demo.utility;

import java.util.UUID;

import com.example.demo.dto.AuthorityDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Authority;
import com.example.demo.entity.User;

public class Util {
	public static String getId() {
		return UUID.randomUUID().toString();
	}
	
	public static AuthorityDto toAuthorityDto(Authority authority) {
		AuthorityDto authorityDto = new AuthorityDto();
		authorityDto.setName(authority.getName());
		return authorityDto;
	}
	
	public static UserDto toUserDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setUsername(user.getUsername());
		userDto.setPassword(user.getPassword());
		userDto.setEmail(user.getEmail());
		return userDto;
	}
}
