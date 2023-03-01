package com.example.demo.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.example.demo.entity.Authority;
import com.example.demo.entity.User;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class AuthorityDto {
	private String name;
	private Set<UserDto> users;
	
	public AuthorityDto() {
		users = new HashSet<>();
	}
	
	public AuthorityDto(Authority authority) {
	
		Function<User, UserDto> toUserDto = (user) -> {
			UserDto userDto = new UserDto();
			userDto.setUsername(user.getUsername());
			userDto.setPassword(user.getPassword());
			userDto.setEmail(user.getEmail());
			return userDto;
		};
		
		name = authority.getName();
		users = authority.getUsers()
				.stream()
				.map(toUserDto)
				.collect(Collectors.toUnmodifiableSet());
	}
	
	
}
