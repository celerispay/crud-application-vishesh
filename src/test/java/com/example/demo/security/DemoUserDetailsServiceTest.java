package com.example.demo.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.utility.Message;

@ExtendWith(MockitoExtension.class)
class DemoUserDetailsServiceTest {

	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private DemoUserDetailsService demoUserDetailsService;
	
	private User user;

	@BeforeEach
	public void initalize() {
		user = new User();
		user.setId("foo");
		user.setUsername("bar");
		user.setEmail("bar@gmail.com");
		user.setPassword("xyz");
		user.setAuthorities(new ArrayList<>());
	}

	@Test
	public void loadUserByUsername_whenUsernameExists() {
		String username = "foo";
		Mockito
			.when(userRepository.findByUsername(username))
			.thenReturn(Optional.of(user));
		assertThat(demoUserDetailsService.loadUserByUsername(username))
		.usingRecursiveComparison()
		.isEqualTo(new DemoUserDetails(user));
	}

	@Test
	public void loadUserByUsername_whenUsernameDoesNotExists() {
		String username = "foo";
		Mockito
			.when(userRepository.findByUsername(username))
			.thenThrow(new UsernameNotFoundException(Message.USER_NOT_FOUND));
		assertThatThrownBy(() -> demoUserDetailsService.loadUserByUsername(username))
		.isInstanceOf(UsernameNotFoundException.class)
		.hasMessage(Message.USER_NOT_FOUND);
	}
}