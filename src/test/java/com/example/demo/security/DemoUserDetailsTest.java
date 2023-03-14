package com.example.demo.security;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.example.demo.entity.User;

class DemoUserDetailsTest {

	@Test
	void userDetailsTest() {
		User user = new User();
		user.setId("foo");
		user.setUsername("bar");
		user.setPassword("xyz");
		user.setEmail("bar@gmail.com");
		user.setAuthorities(new ArrayList<>());
		
		DemoUserDetails userDetails = new DemoUserDetails(user);
		DemoUserDetails temp = new DemoUserDetails(user);
		
		assertThat(userDetails)
		.usingRecursiveComparison()
		.isEqualTo(temp);
	}

}
