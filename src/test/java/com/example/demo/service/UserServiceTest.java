package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.entity.User;
import com.example.demo.exception.AuthorityException;
import com.example.demo.exception.UserException;
import com.example.demo.repository.UserRepository;
import com.example.demo.utility.Message;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;
	
	private User user;
	
	@BeforeEach
	public void initializeUser() {
		User u = new User();
		u.setUsername("foo");
		u.setEmail("foo@abc.com");
		u.setPassword("12345");
		user = u;
	}

	@Test
	public void addUser_whenUsernameIsTaken() {
		Mockito.when(userRepository.existsByUsername("foo")).thenReturn(true);
		assertThatThrownBy(() -> {
			userService.addUser(user);
		}).isInstanceOf(UserException.class)
		.hasMessage(Message.USERNAME_TAKEN);
	}
	
	@Test
	public void addUser_whenEmailyIsInUse() {
		Mockito.when(userRepository.existsByEmail("foo@abc.com")).thenReturn(true);
		assertThatThrownBy(() ->{ userService.addUser(user);})
		.isInstanceOf(UserException.class)
		.hasMessage(Message.EMAIL_ALREADY_IN_USE);
	}

	@Test
	public void addUser_checkIfUserIdIsGenerated() throws UserException, AuthorityException {
		User u = userService.addUser(user);
		assertThat(u.getId())
		.isNotNull()
		.hasSize(36);
	}

	@Test
	public void addUser_checkIfPasswordIsEncrypted() throws UserException, AuthorityException {
		User u = userService.addUser(user);
		assertThat(u.getPassword())
		.isNotEqualTo("12345");
	}
	
	@Test
	public void getUser_whenUserDoesNotExists() {
		Mockito.when(userRepository.findByUsername("bar")).thenReturn(Optional.empty());
		assertThatThrownBy(() -> userService.getUser("bar"))
		.isInstanceOf(UserException.class)
		.hasMessage(Message.USER_NOT_FOUND);
	}
}