package com.example.demo.controller;


import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.exception.AuthorityException;
import com.example.demo.exception.UserException;
import com.example.demo.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;

@Log4j2(topic = "LogStep")
@RestController
@RequestMapping("/demo")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user/{username}")
	@ApiOperation(value = "Find user by username", // summary
			notes = "Provide a username to look up specific user from users database", // description
			response = User.class)
	public User getUser( @ApiParam(value = "Username value for the User you need to retrieve", required = true)
			@PathVariable String username) throws UserException {
		log.debug("UserController triggered");
		return userService.getUser(username);
	}

	@PostMapping("/addUser")
	public User addUser(@Valid @RequestBody User user) throws UserException, AuthorityException {
		log.debug("UserController triggered");
		return userService.addUser(user);
	}

	@PutMapping("/updateUser")
	public User updateUser(@Valid @RequestBody User user) throws UserException {
		return userService.updateUser(user);
	}

	@DeleteMapping("/deleteUser/{id}")
	public Map<String, String> deleteUser(@PathVariable String id) throws UserException {
		return userService.deleteUser(id);
	}
}