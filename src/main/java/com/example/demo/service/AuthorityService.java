package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AuthorityDto;
import com.example.demo.entity.Authority;
import com.example.demo.exception.authority.AuthorityNotFoundException;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.utility.Util;

@Service
public class AuthorityService {
	
	@Autowired
	private AuthorityRepository authorityRepository;

	public AuthorityDto getAuthority(String name) throws AuthorityNotFoundException {
		System.out.println("\n" + name + "\n");
		Optional<Authority> optionalAuthority = authorityRepository.findByName(name);
		if (optionalAuthority.isPresent()) {
			return new AuthorityDto(optionalAuthority.get());
		} else {
			throw new AuthorityNotFoundException();
		}
	}
	
	public Authority addAuthority(Authority authority) {
		Util.setValues(authority);
		return authorityRepository.save(authority);
	}
}
