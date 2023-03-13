package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.example.demo.entity.User;
import com.example.demo.exception.UserException;
import com.example.demo.repository.UserRepository;
import com.example.demo.utility.Message;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace=Replace.NONE)
class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;
	
	private User user;

	@BeforeEach
	public void initailize() {
		user = new User();
		user.setUsername("Sam");
		user.setPassword("1234");
		user.setEmail("sam@gmail.com");
	}

	@Test
	public void addUser_whenUsernameExists() {
		Mockito
		.when(userRepository.existsByUsername(Mockito.any()))
		.thenReturn(true);
		assertThatThrownBy(() -> userService.addUser(user))
		.isInstanceOf(UserException.class)
		.hasMessage(Message.USERNAME_TAKEN);
	}

	@Test
	public void addUser_whenEmailExists() {
		Mockito
		.when(userRepository.existsByEmail(Mockito.any()))
		.thenReturn(true);
		assertThatThrownBy(() -> userService.addUser(user))
		.isInstanceOf(UserException.class)
		.hasMessage(Message.EMAIL_ALREADY_IN_USE);
	}

	@Test
	public void addUser_whenValidUser_checkUserIdLength() throws UserException {
		Mockito
		.when(userRepository.save(Mockito.any()))
		.then(AdditionalAnswers.returnsFirstArg());
		User u = userService.addUser(user);
		assertThat(u.getId()).hasSize(36);
	}

	@Test
	public void addUser_whenValidUser_checkPasswordEncrption() throws UserException {
		User u = userService.addUser(user);
		assertThat(u.getPassword()).isNotEqualTo("1234");
	}

	@Test
	public void getUser_whenUserDoesNotExists() {
		Mockito
		.when(userRepository.findByUsername(Mockito.any()))
		.thenReturn(Optional.empty());
		assertThatThrownBy(() -> userService.getUser(user.getUsername()))
		.isInstanceOf(UserException.class)
		.hasMessage(Message.USER_NOT_FOUND);
	}
	
	public void getUser_whenUserExists() throws UserException {
		Mockito
		.when(userRepository.findByUsername(user.getUsername()))
		.thenReturn(Optional.of(user));
		assertThat(userService.getUser(user.getUsername())).isNotNull();
	}
}