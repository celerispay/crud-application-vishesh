package com.example.demo.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
	
	public static String encryptPassword(String rawPassword) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return  encoder.encode(rawPassword);
	}
	
	public static User setValues(User user) {
		user.setId(getId());
		user.setPassword(encryptPassword(user.getPassword()));
		
		Authority a1 = new Authority();
		a1.setId("1");
		a1.setName("READ");
		
		Authority a2 = new Authority();
		a2.setId("5");
		a2.setName("ROLE_USER");
		
		user.setAuthorities(List.of(a1, a2));
		
		return user;
	}
	
	public static Authority setValues(Authority authority) {
		authority.setId(getId());
		List<User> list = new ArrayList<>();
		authority.setUsers(list);
		return authority;
	}
}
