package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.entity.Authority;
import com.example.demo.utility.Util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorityDto {
	private String name;
	private List<UserDto> users = new ArrayList<UserDto>();
	
	public AuthorityDto() {
		
	}
	
	public AuthorityDto(Authority authority) {
		name = authority.getName();
		users = authority.getUsers()
				.stream()
				.map(Util::toUserDto)
				.collect(Collectors.toUnmodifiableList());
	}
	
	
}
