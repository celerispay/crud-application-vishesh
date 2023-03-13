package com.example.demo.utility;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.entity.Authority;
import com.example.demo.entity.User;

public class Util {
	public static String getId() {
		return UUID.randomUUID().toString();
	}
	
	public static String encryptPassword(String rawPassword) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return  encoder.encode(rawPassword);
	}
	
	public static void setValues(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setId(getId());
		user.setPassword(encoder.encode(user.getPassword()));
		
		Authority a = new Authority();
		a.setId("5");
		a.setName("ROLE_USER");
		
		user.setAuthorities(List.of(a));
	}
}