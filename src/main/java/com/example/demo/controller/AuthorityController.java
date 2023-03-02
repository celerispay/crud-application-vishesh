package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Authority;
import com.example.demo.exception.AuthorityException;
import com.example.demo.service.AuthorityService;

@RestController
@RequestMapping("/demo")
public class AuthorityController {

	@Autowired
	private AuthorityService authorityService;
	
	@GetMapping("/authority/{name}")
	public Authority getAuthority(@PathVariable String name) throws AuthorityException {
		return authorityService.getAuthority(name);
	}
	
	@PostMapping("/addAuthority")
	public Authority addAuthority(@Valid @RequestBody Authority authority) throws AuthorityException {
		return authorityService.addAuthority(authority);
	}
}
