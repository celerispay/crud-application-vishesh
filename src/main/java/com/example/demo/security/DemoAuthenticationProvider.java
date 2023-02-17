package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class DemoAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private DemoUserDetailsService demoUserDetailsService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	@Transactional(readOnly = true)
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		log.trace("Authenticating username and password.");
		String username = authentication.getName();
		String rawPassword = authentication.getCredentials().toString();
	
		DemoUserDetails user = demoUserDetailsService.loadUserByUsername(username);

		if (bCryptPasswordEncoder.matches(rawPassword, user.getPassword())) {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,
					rawPassword, 
					user.getAuthorities());
			log.info("Authentication successful.");
			return token;
		} else {
			log.error("Authentication failed.");
			throw new BadCredentialsException("Invalid credentials.");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
