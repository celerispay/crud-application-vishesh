package com.example.demo.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import com.example.demo.entity.Authority;
import com.example.demo.entity.User;
import com.example.demo.utility.Util;

import static org.assertj.core.api.Assertions.*;

import java.util.Set;

@DataJpaTest
class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@BeforeEach
	public void addUser() {
		User dummyUser = Util.getDummyUser();
		authorityRepository.saveAll(dummyUser.getAuthorities());
		userRepository.save(dummyUser);
	}
	
	@AfterEach
	public void deleteUser() {
		userRepository.deleteAll();
		authorityRepository.deleteAll();
	}
	
	@Test
	public void existsByUsername() {
		boolean res = userRepository.existsByUsername("foo");
		assertThat(res).isTrue();
	}
	
	@Test
	public void notExistsByUsername() {
		boolean res = userRepository.existsByUsername("bar");
		assertThat(res).isFalse();
	}
	
	@Test
	public void existsByEmail() {
		boolean res = userRepository.existsByEmail("foo@abc.com");
		assertThat(res).isTrue();
	}
	
	@Test
	public void notExistsByEmail() {
		boolean res = userRepository.existsByEmail("bar@abc.com");
		assertThat(res).isFalse();
	}
	
	@Test
	public void findByUsername() {
		User user = userRepository.findByUsername("foo").get();
		assertThat(user).isEqualTo(Util.getDummyUser());
	}
	
	@Test
	public void countAuthorities() {
		Set<Authority> authorities = userRepository.findByUsername("foo").get().getAuthorities();
		assertThat(authorities.size()).isEqualTo(2);
	}
}
