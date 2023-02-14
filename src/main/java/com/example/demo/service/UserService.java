package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.Authority;
import com.example.demo.entity.User;
import com.example.demo.exception.EmailExistException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.exception.UsernameTakenException;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utility.Util;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;

	public User addUser(User user) throws UsernameTakenException, EmailExistException {
		boolean usernameExist = userRepository.existsByUsername(user.getUsername());
		boolean emailExist = userRepository.existsByEmail(user.getEmail());
		
		if (usernameExist) throw new UsernameTakenException();
		if (emailExist) throw new EmailExistException();
		
		String encodedPassword = Util.encryptPassword(user.getPassword());
		
		user.setId(Util.getId());
		user.setPassword(encodedPassword);
	
		Authority a1 = authorityRepository.findByName("READ");
		a1.setUsers(null);
		Authority a2 = authorityRepository.findByName("WRITE");
		a2.setUsers(null);
		
		user.setAuthorities(List.of(a1, a2));
		
		authorityRepository.save(a1);
		userRepository.save(user);
		return user;
	}
	
	public UserDto getUser(String id) throws UserNotFoundException {
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			return new UserDto(optionalUser.get());
		} else {
			throw new UserNotFoundException();
		}
	}
}
