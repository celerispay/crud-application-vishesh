package com.example.demo.utility;

import java.util.HashSet;
import java.util.Set;
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
	
	public static void setValues(User user) {
		System.out.println("Generating user id...");
		user.setId(getId());
		user.setUsername(user.getUsername().toLowerCase());
		System.out.println("Encrypting password...");
		user.setPassword(encryptPassword(user.getPassword()));
		
		Authority a1 = new Authority();
		a1.setId("1");
		a1.setName("READ");
		
		Authority a2 = new Authority();
		a2.setId("5");
		a2.setName("ROLE_USER");
		
		System.out.println("Setting READ authority and USER role...");
		user.setAuthorities(Set.of(a1, a2));
	}
	
	public static void setValues(Authority authority) {
		authority.setId(getId());
		Set<User> s = new HashSet<>();
		authority.setUsers(s);
	}
}
