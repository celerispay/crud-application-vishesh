package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.utility.Message;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class DemoAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private DemoUserDetailsService demoUserDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional(readOnly = true)
	public Authentication authenticate(Authentication authentication){
		log.debug("Authenticating...");
		
		String username = authentication.getName();
		String rawPassword = authentication.getCredentials().toString();
	
		DemoUserDetails user = demoUserDetailsService.loadUserByUsername(username);
		log.trace("Requet Username: " + username);
		log.trace("Requet Password: " + rawPassword);
		log.trace("Database Username: " + user.getUsername());
		log.trace("Database Password: " + user.getPassword());
		
		if (passwordEncoder.matches(rawPassword, user.getPassword())) {
			log.debug("Authentication successful !!");
			return new UsernamePasswordAuthenticationToken(username,
					rawPassword,
					user.getAuthorities()
					);
		} else {
			log.debug("Authentication failed !!");
			throw new BadCredentialsException(Message.BAD_CREDENTIAL);
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
