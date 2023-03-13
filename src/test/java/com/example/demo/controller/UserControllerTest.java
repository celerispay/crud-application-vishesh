package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
import com.example.demo.security.DemoAuthenticationProvider;
import com.example.demo.service.UserService;
import com.example.demo.utility.Message;

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
	public void addUser_whenBlankFields_returnCorrectResponse() throws Exception {
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
	public void addUser_whenInvalidEamil_returnCorrectResponse() throws Exception {
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
	public void addUser_whenValidUser_returnCorrectResponse() throws Exception {
		Mockito.when(userService.addUser(any())).thenReturn(user);
		String s = "{ \"username\": \"Sam\", \"password\":\"1234\", \"email\": \"sam@gmail.com\"}";
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/demo/addUser")
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8")
				.content(s);
		
		mockMvc.perform(request)
		.andExpect(MockMvcResultMatchers.jsonPath("$.username").value(user.getUsername()));
	}
}