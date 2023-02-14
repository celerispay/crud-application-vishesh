package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.exception.user.EmailExistException;
import com.example.demo.exception.user.UserNotFoundException;
import com.example.demo.exception.user.UsernameTakenException;
import com.example.demo.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/user/{username}")
	public UserDto getUser(@PathVariable String username) throws UserNotFoundException {
		System.out.println("\n\ngetUser() triggered\n\n");
		return userService.getUser(username);
	}
	
	@PostMapping("/addUser")
	public User addUser(@Valid @RequestBody User user) throws UsernameTakenException, EmailExistException {
		return userService.addUser(user);
	}
}
