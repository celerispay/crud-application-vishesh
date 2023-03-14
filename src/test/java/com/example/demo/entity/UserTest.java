package com.example.demo.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class UserTest {

	@Test
	public void getterAndSetterTest() {
		User user = new User();
		user.setId("bar");
		user.setUsername("foo");
		user.setPassword("abc");
		user.setEmail("foo@xyz.com");
		user.setAuthorities(new ArrayList<>());
		
		User temp = new User();
		temp.setId("bar");
		temp.setUsername("foo");
		temp.setPassword("abc");
		temp.setEmail("foo@xyz.com");
		temp.setAuthorities(new ArrayList<>());
		
		assertThat(user).usingRecursiveComparison().isEqualTo(temp);
	}
}
