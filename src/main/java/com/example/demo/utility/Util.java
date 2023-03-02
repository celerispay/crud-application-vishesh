package com.example.demo.utility;

import java.util.HashSet;
import java.util.Set;
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
		user.setId(getId());
		user.setUsername(user.getUsername().toLowerCase());
		user.setPassword(encryptPassword(user.getPassword()));
		
		Authority a1 = new Authority();
		a1.setId(getId());
		a1.setName("USER");
		a1.setUsers(Set.of(user));
		
		Authority a2 = new Authority();
		a2.setId(getId());
		a2.setName("ROLE_USER");
		a2.setUsers(Set.of(user));
		
		user.setAuthorities(Set.of(a1, a2));
	}
	
	public static void setValues(Authority authority) {
		authority.setId(getId());
		Set<User> s = new HashSet<>();
		authority.setUsers(s);
	}
}
