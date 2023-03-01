package com.example.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;

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

import com.example.demo.dto.UserDto;
import com.example.demo.exception.UserException;
import com.example.demo.security.DemoAuthenticationProvider;
import com.example.demo.service.UserService;
import com.example.demo.utility.Message;
import com.example.demo.utility.Util;
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

	@Test
	public void getUserByUsername_whenUserExists_checkStatus() throws Exception {
		Mockito.when(userService.getUser("foo")).thenReturn(new UserDto(Util.getDummyUser()));
		MockHttpServletResponse response = mockMvc.perform(
					MockMvcRequestBuilders.get("/demo/user/foo").accept(MediaType.APPLICATION_JSON)
				).andReturn().getResponse();
	}

	@Test
	public void getUserByUsername_whenUserExists_checkBody() throws Exception {
		UserDto userDto = new UserDto(Util.getDummyUser());
		Mockito.when(userService.getUser("foo")).thenReturn(userDto);
		MockHttpServletResponse response = mockMvc.perform(
					MockMvcRequestBuilders.get("/demo/user/foo").accept(MediaType.APPLICATION_JSON)
				).andReturn().getResponse();
		assertThat(response.getContentAsString()).isEqualTo(
					objectMapper.writeValueAsString(userDto)
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
		Mockito.when(userService.getUser("bar")).thenThrow(new UserException(Message.USER_NOT_FOUND));
		MockHttpServletResponse response = mockMvc.perform(
					MockMvcRequestBuilders.get("/demo/user/bar").accept(MediaType.APPLICATION_JSON)
				).andReturn().getResponse();
		assertThat(response.getContentAsString()).isEqualTo(
					//objectMapper.writeValueAsString(Message.USER_NOT_FOUND)
					Message.USER_NOT_FOUND
				);
	}
}