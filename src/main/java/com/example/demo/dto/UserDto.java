package com.example.demo.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.example.demo.entity.Authority;
import com.example.demo.entity.User;
import com.example.demo.utility.Util;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class UserDto {
	private String username;
	private String password;
	private String email;
	private Set<AuthorityDto> authorities;
	
	public UserDto() {
		authorities = new HashSet<>();
	}
	
	public UserDto(User user) {
		username = user.getUsername();
		password = user.getPassword();
		email = user.getEmail();
	
		Function<Authority, AuthorityDto> toAuthorityDto = (authority) -> {
			System.out.println("Hit");
			AuthorityDto authorityDto = new AuthorityDto();
			authorityDto.setName(authority.getName());
			return authorityDto;
		};
	
		System.out.println("\nStart\n");
		
//		authorities = user.getAuthorities()
//						  .stream()
//						  .map(toAuthorityDto)
//						  .collect(Collectors.toUnmodifiableSet());
		
		//Set<Authority> temp = user.getAuthorities();
		//temp.size();
		
		user.getAuthorities().forEach(authority -> authority.setUsers(null));
		
		//temp.stream()
		//.map(toAuthorityDto)
		//.collect(Collectors.toUnmodifiableSet());
		
		System.out.println("\nEnd\n");
		
	}
	

}
