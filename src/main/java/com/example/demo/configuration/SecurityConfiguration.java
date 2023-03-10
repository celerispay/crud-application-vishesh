package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import com.example.demo.security.DemoAuthenticationProvider;

@Configuration
@SuppressWarnings("deprecation")
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DemoAuthenticationProvider demoAuthenticationProvider;
	
	@Bean
	@Profile(value = { "test", "prod", "qa", "batch", "temp"})
	public PasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Profile("dev")
	public PasswordEncoder noOpPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
		authenticationManagerBuilder.authenticationProvider(demoAuthenticationProvider);
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.httpBasic();
		http.authorizeRequests()
			.mvcMatchers("/addAuthority", "/authority/*").hasRole("ADMIN")
			.mvcMatchers("/user/*").hasAnyRole("ADMIN", "MANAGER")
			.anyRequest().permitAll();
		http.csrf().disable();
		http.cors().disable();
	}
	
	 @Bean
	    public CommonsRequestLoggingFilter logFilter() {
	        CommonsRequestLoggingFilter filter
	          = new CommonsRequestLoggingFilter();
	        filter.setIncludeQueryString(true);
	        filter.setIncludePayload(true);
	        filter.setMaxPayloadLength(10000);
	        filter.setIncludeHeaders(false);
	        filter.setAfterMessagePrefix("REQUEST DATA: ");
	        return filter;
	    }
}
