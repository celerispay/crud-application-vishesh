package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.entity.Authority;
import com.example.demo.entity.User;
import com.example.demo.utility.Util;

@DataJpaTest
class AuthorityRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	private Authority dummyAuthority;
	
	@BeforeEach
	public void addUser() {
		dummyAuthority = Util.getDummyAuthority();
		userRepository.saveAll(dummyAuthority.getUsers());
		authorityRepository.save(dummyAuthority);
	}
	
	@AfterEach
	public void deleteUser() {
		userRepository.deleteAll();
		authorityRepository.deleteAll();
	}	
	
	@Test
	public void existsByName() {
		boolean res = authorityRepository.existsByName("alpha");
		assertThat(res).isTrue();
	}
	
	@Test
	public void notExistsByName() {
		boolean res = authorityRepository.existsById("beta");
		assertThat(res).isFalse();
	}
	
	@Test
	public void countUser() {
		Set<User> users = authorityRepository.findByName("alpha").get().getUsers();
		assertThat(users.size()).isEqualTo(2);
	}
	
	@Test
	public void findByName() {
		Authority authority = authorityRepository.findByName("alpha").get();
		assertThat(authority).isEqualTo(Util.getDummyAuthority());
	}
}