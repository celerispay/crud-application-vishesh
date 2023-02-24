package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;
	
	@Test
	void giveUserEntity_whenUserDoesNotExistsAndSave_thenReturnUserEntity() {
		String username = "abc";
		given(userRepository.findByUsername(username)).willReturn(Optional.of(generateUser()));
		User expected = generateUser();
		User actual = userRepository.findByUsername(username).get();
		assertEquals(actual, expected);
	}

	@Test
	void testGetUser() {
		fail("Not yet implemented");
	}
	
	private User generateUser() {
		User user = new User();
		user.setUsername("abc");
		user.setPassword("12345");
		user.setEmail("abc@gmail.com");
	
		return user;
	}
}