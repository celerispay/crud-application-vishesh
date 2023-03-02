package com.example.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.example.demo.entity.Authority;
import com.example.demo.entity.User;
import com.example.demo.exception.UserException;
import com.example.demo.exceptionhandler.response.InvalidUserExceptionResponse;
import com.example.demo.exceptionhandler.response.ValidationExceptionResponse;
import com.example.demo.security.DemoAuthenticationProvider;
import com.example.demo.service.UserService;
import com.example.demo.utility.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerTest {
	@MockBean
	private UserService userService;

	@MockBean
	private DemoAuthenticationProvider demoAuthenticationProvider;

	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	private User user;

	@BeforeEach
	public void initalizeUser() {
		User user = new User();
		user.setId("e983d0a6-d9fe-43bc-a494-8f103087760b");
		user.setUsername("foo");
		user.setPassword("$2a$10$aPu1RcOTV8NrLsmQD0FpUeILLjnOg6vJ4ZudatCpPmud7TFS3CD9G");
		user.setEmail("foo@abc.com");
		
		Authority a1 = new Authority();
		a1.setId("ccaf4000-e8c1-4435-aa8b-f0bd98aa60f3");
		a1.setName("alpha");
		
		Authority a2 = new Authority();
		a2.setId("dd18101d-241f-43d1-bb2a-0116a7f5a548");
		a2.setName("beta");
		
		user.setAuthorities(List.of(a1, a2));
		
		this.user = user;
	}

	@Test
	public void getUserByUsername_whenUserExists_checkStatus() throws Exception {
		Mockito.when(userService.getUser("foo")).thenReturn(user);
		MockHttpServletResponse response = mockMvc.perform(
					MockMvcRequestBuilders.get("/demo/user/foo").accept(MediaType.APPLICATION_JSON)
				).andReturn().getResponse();
	}

	@Test
	public void getUserByUsername_whenUserExists_checkBody() throws Exception {
		Mockito.when(userService.getUser("foo")).thenReturn(user);
		MockHttpServletResponse response = mockMvc.perform(
					MockMvcRequestBuilders.get("/demo/user/foo").accept(MediaType.APPLICATION_JSON)
				).andReturn().getResponse();
		assertThat(response.getContentAsString()).isEqualTo(
			objectMapper.writeValueAsString(user)
		);
	}
	
	@Test
	public void getUserByUsername_whenUserDoesnotExists_checkStatus() throws Exception {
		Mockito.when(userService.getUser("bar")).thenThrow(new UserException(Message.USER_NOT_FOUND));
		MockHttpServletResponse response = mockMvc.perform(
					MockMvcRequestBuilders.get("/demo/user/bar").accept(MediaType.APPLICATION_JSON)
				).andReturn().getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	public void getUserByUsername_whenUserDoesnotExists_checkBody() throws Exception {
		InvalidUserExceptionResponse invalidUserExceptionResponse = new InvalidUserExceptionResponse(Message.USER_NOT_FOUND);
		Mockito.when(userService.getUser("bar")).thenThrow(new UserException(Message.USER_NOT_FOUND));
		MockHttpServletResponse response = mockMvc.perform(
					MockMvcRequestBuilders.get("/demo/user/bar").accept(MediaType.APPLICATION_JSON)
				).andReturn().getResponse();
		assertThat(response.getContentAsString()).isEqualTo(
					objectMapper.writeValueAsString(invalidUserExceptionResponse)
				);
	}
	
//	@Test
//	public void addUser_invalideUser() throws Exception {
//		List<String> violations = List.of(Message.VALIDATE_USERNAME_NOT_BLANK, Message.VALIDATE_USERNAME_LENGTH);
//		ValidationExceptionResponse validationExceptionResponse = new ValidationExceptionResponse(violations);
//		user.setUsername("");
//		Mockito.when(userService.addUser(user)).thenThrow(MethodArgumentNotValidException.class);
//		MockHttpServletResponse response = mockMvc.perform(
//					MockMvcRequestBuilders.post("/demo/adduser", user)
//				).andReturn().getResponse();
//		assertThat(response.getContentAsString()).isEqualTo(
//				objectMapper.writeValueAsString(validationExceptionResponse)
//				);
//	}
}