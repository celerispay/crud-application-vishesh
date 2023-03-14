package com.example.demo.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.entity.User;
import com.example.demo.utility.Message;

@ExtendWith(MockitoExtension.class)
class DemoAuthenticationProviderTest {
	
	@Mock
	private DemoUserDetailsService userDetailsService;
	
	@Mock
	private PasswordEncoder passwordEncoder;

	@InjectMocks
	private DemoAuthenticationProvider authenticationProvider;

	@Test
	public void authenticate_whenUserExistsAndPasswordMatches_thenReturnAuthentication() {
		Authentication auth = getAuth();
		Mockito
			.when(userDetailsService.loadUserByUsername("foo"))
			.thenReturn(getUserDetails());
		Mockito
			.when(passwordEncoder.matches("bar", "bar"))
			.thenReturn(true);
		assertThat(authenticationProvider.authenticate(auth))
		.isInstanceOf(UsernamePasswordAuthenticationToken.class);
	}

	@Test
	public void authenticate_whenUserExistsButPasswordDoesNotMatch_thenThrowBadCredentialException() {
		Authentication auth = getAuth();
		Mockito
			.when(userDetailsService.loadUserByUsername("foo"))
			.thenReturn(getUserDetails());
		Mockito
			.when(passwordEncoder.matches("bar", "bar"))
			.thenReturn(false);
		assertThatThrownBy(() -> authenticationProvider.authenticate(auth))
		.isInstanceOf(BadCredentialsException.class)
		.hasMessage(Message.BAD_CREDENTIAL);
	}
	
	@Test
	public void authenticate_whenUserDoesNotExists_thenThrowUsernameNotFoundException() {
		Authentication auth = getAuth();
		Mockito
			.when(userDetailsService.loadUserByUsername("foo"))
			.thenThrow(new UsernameNotFoundException(Message.USER_NOT_FOUND));
		assertThatThrownBy(() -> authenticationProvider.authenticate(auth))
		.isInstanceOf(UsernameNotFoundException.class)
		.hasMessage(Message.USER_NOT_FOUND);
	}
	
	private Authentication getAuth() {
		return new Authentication() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getName() {
				return "foo";
			}
			
			@Override
			public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
			}
			
			@Override
			public boolean isAuthenticated() {
				return false;
			}
			
			@Override
			public Object getPrincipal() {
				return null;
			}
			
			@Override
			public Object getDetails() {
				return null;
			}
			
			@Override
			public Object getCredentials() {
				return "bar";
			}
			
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				return null;
			}
		};
	}
	
	public DemoUserDetails getUserDetails() {
		User user = new User();
		user.setUsername("foo");
		user.setPassword("bar");
		user.setAuthorities(new ArrayList<>());
		return new DemoUserDetails(user);
	}

}
