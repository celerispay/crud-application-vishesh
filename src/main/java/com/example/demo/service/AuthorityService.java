package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AuthorityDto;
import com.example.demo.entity.Authority;
import com.example.demo.exception.AuthorityException;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.utility.Util;

@Service
public class AuthorityService {
	
	@Autowired
	private AuthorityRepository authorityRepository;

	public AuthorityDto getAuthority(String name) throws AuthorityException {
		Optional<Authority> optionalAuthority = authorityRepository.findByName(name);
		if (optionalAuthority.isPresent()) {
			return new AuthorityDto(optionalAuthority.get());
		} else {
			throw new AuthorityException("Authority not found exception");
		}
	}
	
	public Authority addAuthority(Authority authority) throws AuthorityException {
		Util.setValues(authority);
		boolean authorityExist = authorityRepository.existsByName(authority.getName());
		if (authorityExist) throw new AuthorityException("Authority already exists");
		
		return authorityRepository.save(authority);
	}
}