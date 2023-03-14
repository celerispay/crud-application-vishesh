package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;

import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.entity.User;
import com.example.demo.exception.UserException;
import com.example.demo.security.DemoAuthenticationProvider;
import com.example.demo.service.UserService;
import com.example.demo.utility.Message;
import com.example.demo.utility.Util;

@WebMvcTest
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@MockBean
	private DemoAuthenticationProvider authenticationProvider;
	
	private User user;
	
	@BeforeEach
	public void initalize() {
		user = new User();
		user.setUsername("Sam");
		user.setPassword("1234");
		user.setEmail("sam@gmail.com");
	}
	
	@Test
	public void getUser_whenUsernameExists_thenReturnUser() throws Exception {
		Mockito.when(userService.getUser(Mockito.anyString())).thenReturn(user);
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/demo/user/jack");
		
		mockMvc.perform(request)
		.andExpect(MockMvcResultMatchers.jsonPath("$.username").value(user.getUsername()));
		
	}
	
	@Test
	public void getUser_whenUsernameDoesNotExists_thenThrowUserException() throws Exception {
		Mockito.when(userService.getUser(Mockito.anyString())).thenThrow(new UserException(Message.USER_NOT_FOUND));
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/demo/user/jack");
		
		mockMvc.perform(request)
		.andExpect(MockMvcResultMatchers.jsonPath("$.exception").value(Message.USER_NOT_FOUND));
	}
	
	@Test
	public void addUser_whenBlankFields_thenThrowMethodArgumentNotValidException() throws Exception {
		String s = "{ \"username\": \"\", \"password\":\"\", \"email\": \"\"}";
		
		MockHttpServletRequestBuilder request =  MockMvcRequestBuilders.post("/demo/addUser")
		.contentType(MediaType.APPLICATION_JSON)
		.characterEncoding("utf-8")
		.content(s);
		
		mockMvc.perform(request)
		.andExpect(MockMvcResultMatchers.status().isBadRequest())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.jsonPath("$.username").value(Message.USERNAME_NOT_BLANK))
		.andExpect(MockMvcResultMatchers.jsonPath("$.password").value(Message.PASSWORD_NOT_BLANK))
		.andExpect(MockMvcResultMatchers.jsonPath("$.email").value(Message.EMAIL_NOT_BLANK));
	}

	@Test
	public void addUser_whenInvalidEmail_thenThrowMethodArgumentNotValidException() throws Exception {
		String s = "{ \"username\": \"Sam\", \"password\":\"1234\", \"email\": \"samgmail.com\"}";
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/demo/addUser")
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8")
				.content(s);
		
		mockMvc.perform(request)
		.andExpect(MockMvcResultMatchers.status().isBadRequest())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.jsonPath("$.email").value(Message.INVALID_EMAIL));
	}

	@Test
	public void addUser_whenAllFieldsAndValidEmail_returnCorrectResponse() throws Exception {
		Mockito.when(userService.addUser(any())).thenReturn(user);
		String s = "{ \"username\": \"Sam\", \"password\":\"1234\", \"email\": \"sam@gmail.com\"}";
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/demo/addUser")
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8")
				.content(s);
		
		mockMvc.perform(request)
		.andExpect(MockMvcResultMatchers.jsonPath("$.username").value(user.getUsername()));
	}

	@Test
	public void updateUser_whenIdDoesNotExists_thenThrowUserException() throws Exception {
		Mockito.when(userService.updateUser(Mockito.any())).thenThrow(new UserException(Message.USER_NOT_FOUND));
		String s = "{\"id\": \"foo\", \"username\": \"Sam\", \"password\":\"1234\", \"email\": \"sam@gmail.com\"}";
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/demo/updateUser")
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8")
				.content(s);
		
		mockMvc.perform(request)
		.andExpect(MockMvcResultMatchers.jsonPath("$.exception").value(Message.USER_NOT_FOUND));
	}
	
	@Test
	public void updateUser_whenIdExists_thenReturnUpdatedUser() throws Exception {
		Mockito.when(userService.updateUser(Mockito.any())).thenReturn(user);
		String s = "{\"id\": \"foo\", \"username\": \"Sam\", \"password\":\"1234\", \"email\": \"sam@gmail.com\"}";
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/demo/updateUser")
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8")
				.content(s);
	
		mockMvc.perform(request)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.jsonPath("$.username").value("Sam"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.password").value("1234"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("sam@gmail.com"));
	}
	
	@Test
	public void deleteUser_whenWhenIdExists_thenThrowUserException() throws Exception {
		Mockito.when(userService.deleteUser(Mockito.anyString())).thenReturn(Map.of("msg", Message.USER_DELETED));
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/demo/deleteUser/foo");
		
		mockMvc.perform(request)
		.andExpect(MockMvcResultMatchers.jsonPath("$.msg").value(Message.USER_DELETED));
	}
	
	@Test
	public void deleteUser_whenWhenIdDoesNotExists_thenThrowUserException() throws Exception {
		Mockito.when(userService.deleteUser(Mockito.anyString())).thenThrow(new UserException(Message.USER_NOT_FOUND));
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/demo/deleteUser/foo");
		
		mockMvc.perform(request)
		.andExpect(MockMvcResultMatchers.jsonPath("$.exception").value(Message.USER_NOT_FOUND));
	}
}