package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.security.DemoAuthenticationProvider;

@Configuration
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DemoAuthenticationProvider demoAuthenticationProvider;
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
		authenticationManagerBuilder.authenticationProvider(demoAuthenticationProvider);
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.httpBasic();
		http.authorizeRequests()
			.mvcMatchers("/addAuthority").hasRole("ADMIN")
			.mvcMatchers("/user/*").hasAnyRole("ADMIN", "MANAGER")
			.anyRequest().permitAll();
		http.csrf().disable();
		http.cors().disable();
	}
}
