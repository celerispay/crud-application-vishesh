package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.UserRepository;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.utility.Util;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User addUser(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
		
		user.setId(Util.getId());
		user.setPassword(encodedPassword);
		
		return userRepository.save(user);
	}
	
	public UserDto getUser(String id) {
		User user = userRepository.findById(id).get();
		UserDto userDto = new UserDto(user);
		return userDto;
	}
}
