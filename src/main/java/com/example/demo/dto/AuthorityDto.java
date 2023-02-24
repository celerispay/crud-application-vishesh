package com.example.demo.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.demo.entity.Authority;
import com.example.demo.utility.Util;

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
		name = authority.getName();
		users = authority.getUsers()
				.stream()
				.map(Util::toUserDto)
				.collect(Collectors.toUnmodifiableSet());
	}
	
	
}
