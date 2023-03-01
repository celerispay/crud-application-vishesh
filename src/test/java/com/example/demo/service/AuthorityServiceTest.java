package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.entity.Authority;
import com.example.demo.exception.AuthorityException;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.utility.Message;

@ExtendWith(MockitoExtension.class)
class AuthorityServiceTest {

	@Mock
	private AuthorityRepository authorityRepository;

	@InjectMocks
	private AuthorityService authorityService;
	
	private Authority authority;
	
	@BeforeEach
	public void initializeAuthority() {
		Authority a = new Authority();
		a.setName("alpha");
		authority = a;
	}
	
	@Test
	public void addAuthority_whenAuthorityAlreadyExists() {
		Mockito.when(authorityRepository.existsByName("alpha")).thenReturn(true);
		assertThatThrownBy(() -> authorityService.addAuthority(authority))
		.isInstanceOf(AuthorityException.class)
		.hasMessage(Message.AUTHORITY_ALREADY_EXISTS);
	}
	
	@Test
	public void addAuthority_checkIfAuthorityIdIsGenerated() throws AuthorityException {
		Mockito.when(authorityRepository.save(authority)).thenReturn(authority);
		Authority a = authorityService.addAuthority(authority);
		assertThat(a.getId())
		.isNotNull()
		.hasSize(36);
	}

	@Test
	public void getUser_whenUserAlreadyExists() {
		Mockito.when(authorityRepository.findByName("beta")).thenReturn(Optional.empty());
		assertThatThrownBy(() -> authorityService.getAuthority("beta"))
		.isInstanceOf(AuthorityException.class)
		.hasMessage(Message.AUTHORITY_NOT_FOUND);
	}
}