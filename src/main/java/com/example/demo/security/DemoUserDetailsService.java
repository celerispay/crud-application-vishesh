package com.example.demo.security;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class DemoUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public DemoUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Supplier<UsernameNotFoundException> supplier = () -> {
		System.out.println("User does not exists - Retrivial unsuccessful...");
			return new UsernameNotFoundException("User not found");
		};
		System.out.println("Retriving user from database for authentication...");
		User user = userRepository.findByUsername(username)
				.orElseThrow(supplier);
		System.out.println("User exists - Retrivial successful...");
		return new DemoUserDetails(user);
	}

}
