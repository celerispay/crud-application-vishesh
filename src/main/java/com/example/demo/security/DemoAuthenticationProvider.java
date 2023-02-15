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

@Service
public class DemoAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private DemoUserDetailsService demoUserDetailsService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	@Transactional(readOnly = true)
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String rawPassword = authentication.getCredentials().toString();
		
		DemoUserDetails user = demoUserDetailsService.loadUserByUsername(username);
	
		System.out.println("Authenticating passsword...");
		if (bCryptPasswordEncoder.matches(rawPassword, user.getPassword())) {
			System.out.println("Password matched - Authentication successful...");
			System.out.println("Lazily retriving user's authorities to create UsernamePasswordAuthenticationToken...");
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,
					rawPassword, 
					user.getAuthorities());
			System.out.println("Authorities retrived and UsernamePasswordAuthenticationToken created...");
			System.out.println("Saving token in Spring Context...");
			return token;
		} else {
			System.out.println("Password does not matched - Authentication unsuccessful...");
			throw new BadCredentialsException("Invalid credentials");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
