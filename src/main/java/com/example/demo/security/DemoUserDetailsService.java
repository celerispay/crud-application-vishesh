package com.example.demo.security;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2(topic = "LogStep")
@Service
public class DemoUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public DemoUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Supplier<UsernameNotFoundException> supplier = () -> {
			log.debug("Authentication failed !!");
			return new UsernameNotFoundException("User not found.");
		};
		
		User user = userRepository.findByUsername(username)
				.orElseThrow(supplier);
		
		return new DemoUserDetails(user);
	}

}
