package com.example.demo.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.exception.UserException;
import com.example.demo.repository.UserRepository;
import com.example.demo.utility.Message;
import com.example.demo.utility.Util;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User addUser(User user) throws UserException {
		boolean usernameExist = userRepository.existsByUsername(user.getUsername());
		if (usernameExist) throw new UserException(Message.USERNAME_TAKEN);
		
		boolean emailExist = userRepository.existsByEmail(user.getEmail());
		if (emailExist) throw new UserException(Message.EMAIL_ALREADY_IN_USE);
		
		Util.setValues(user);
		
		userRepository.save(user);
		
		return user;
	}
		
	public User getUser(String username) throws UserException {
		Optional<User> optionalUser = userRepository.findByUsername(username);
		if (optionalUser.isPresent()) {
			return optionalUser.get();
		} else {
			throw new UserException(Message.USER_NOT_FOUND);
		}
	}
	
	public User updateUser(User user) throws UserException {
		if (!userRepository.existsById(user.getId())) {
			throw new UserException(Message.USER_NOT_FOUND);
		}
		return userRepository.save(user);
	}
	
	public Map<String, String> deleteUser(String userId) throws UserException {
		if (!userRepository.existsById(userId)) {
			throw new UserException(Message.USER_NOT_FOUND);
		}
		userRepository.deleteById(userId);
		return Map.of("msg", Message.USER_DELETED);
	}
}