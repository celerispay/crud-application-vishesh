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
	
	public static User getDummyUser() {
		User u = new User();
		
		u.setId("b37452a3-0d77-4460-b571-d10b9f78b6a7");
		u.setUsername("foo");
		u.setEmail("foo@abc.com");
		u.setPassword("12345");
		
		Authority a1 = new Authority();
		a1.setId("900c351c-b0c0-455b-917e-531563c9ffae");
		a1.setName("alpha");
		a1.setUsers(new HashSet<User>());
		
		Authority a2 = new Authority();
		a2.setId("6b52c70e-2865-4d8a-b6fd-2fd8b6a2dd9b");
		a2.setName("beta");
		a2.setUsers(new HashSet<User>());
		
		Set<Authority> authorities = Set.of(a1, a2);
	
		u.setAuthorities(authorities);
		
		return u;
	}
	
	public static Authority getDummyAuthority() {
		User u1 = new User();
		u1.setId("b37452a3-0d77-4460-b571-d10b9f78b6a7");
		u1.setUsername("foo");
		u1.setEmail("foo@abc.com");
		u1.setPassword("12345");
		
		User u2 = new User();
		u2.setId("900c351c-b0c0-455b-917e-531563c9ffae");
		u2.setUsername("bar");
		u2.setEmail("bar@xyz.com");
		u2.setPassword("12345");
		
		Authority a = new Authority();
		a.setId("6b52c70e-2865-4d8a-b6fd-2fd8b6a2dd9b");
		a.setName("alpha");
		a.setUsers(Set.of(u1, u2));
		
		return a;
	}
}
