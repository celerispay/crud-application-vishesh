package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AuthorityDto;
import com.example.demo.entity.Authority;
import com.example.demo.exception.authority.AuthorityNotFoundException;
import com.example.demo.service.AuthorityService;

@RestController
public class AuthorityController {

	@Autowired
	private AuthorityService authorityService;
	
	@GetMapping("/authority/{name}")
	public AuthorityDto getAuthority(@PathVariable String name) throws AuthorityNotFoundException {
		return authorityService.getAuthority(name);
	}
	
	@PostMapping("/addAuthority")
	public Authority addAuthority(@Valid @RequestBody Authority authority) {
		return authorityService.addAuthority(authority);
	}
}
