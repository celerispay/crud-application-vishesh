package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AuthorityDto;
import com.example.demo.entity.Authority;
import com.example.demo.exception.authority.AuthorityExistException;
import com.example.demo.exception.authority.AuthorityNotFoundException;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.utility.Util;

@Service
public class AuthorityService {
	
	@Autowired
	private AuthorityRepository authorityRepository;

	public AuthorityDto getAuthority(String name) throws AuthorityNotFoundException {
		Optional<Authority> optionalAuthority = authorityRepository.findByName(name);
		if (optionalAuthority.isPresent()) {
			return new AuthorityDto(optionalAuthority.get());
		} else {
			throw new AuthorityNotFoundException();
		}
	}
	
	public Authority addAuthority(Authority authority) throws AuthorityExistException {
		Util.setValues(authority);
		
		boolean authorityExist = authorityRepository.existsByName(authority.getName());
		if (authorityExist) throw new AuthorityExistException();
		
		return authorityRepository.save(authority);
	}
}
