package com.example.demo.controller;


import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DemoApplication;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.exception.UserException;
import com.example.demo.service.UserService;

@RestController
public class UserController {
	
	static Logger logger = LogManager.getLogger(DemoApplication.class);

	@Autowired
	private UserService userService;
	
	@GetMapping("/test")
	public String test() {
		logger.fatal("The is an fatal message");
		return "TEST";
	}
	
	@GetMapping("/user/{username}")
	public UserDto getUser(@PathVariable String username) throws UserException {
		System.out.println("Controller - getUser() triggered");
		return userService.getUser(username);
	}

	@PostMapping("/addUser")
	public User addUser(@Valid @RequestBody User user) throws UserException {
		System.out.println("addUser() triggered");
		System.out.println("User: " + user);
		return userService.addUser(user);
	}
}