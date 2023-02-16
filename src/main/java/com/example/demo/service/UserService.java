package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.exception.UserException;
import com.example.demo.repository.UserRepository;
import com.example.demo.utility.Util;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User addUser(User user) throws UserException {
		System.out.println("Service - addUser() triggered");
		
		System.out.println("Checking if username already exists...");
		boolean usernameExist = userRepository.existsByUsername(user.getUsername());
		if (usernameExist) throw new UserException("Username already taken");
		
		System.out.println("checking if user email algready exists...");
		boolean emailExist = userRepository.existsByEmail(user.getEmail());
		if (emailExist) throw new UserException("Email already exists");
		
		Util.setValues(user);
		
		userRepository.save(user);
		
		return user;
	}

	public UserDto getUser(String username) throws UserException {
		System.out.println("Service - getUser() triggered");
		Optional<User> optionalUser = userRepository.findByUsername(username);
		if (optionalUser.isPresent()) {
			return new UserDto(optionalUser.get());
		} else {
			throw new UserException("User not found");
		}
	}
}