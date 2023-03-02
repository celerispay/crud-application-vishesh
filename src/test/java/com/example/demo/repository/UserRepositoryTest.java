package com.example.demo.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.example.demo.entity.Authority;
import com.example.demo.entity.User;

import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

@DataJpaTest
@Sql(scripts = "classpath:insert.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:delete.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	private User user;

	@BeforeEach
	public void initalizeUser() {
		User user = new User();
		user.setId("e983d0a6-d9fe-43bc-a494-8f103087760b");
		user.setUsername("foo");
		user.setPassword("$2a$10$aPu1RcOTV8NrLsmQD0FpUeILLjnOg6vJ4ZudatCpPmud7TFS3CD9G");
		user.setEmail("foo@abc.com");
		
		Authority a1 = new Authority();
		a1.setId("ccaf4000-e8c1-4435-aa8b-f0bd98aa60f3");
		a1.setName("alpha");
		a1.setUsers(new HashSet<>());
		
		Authority a2 = new Authority();
		a2.setId("dd18101d-241f-43d1-bb2a-0116a7f5a548");
		a2.setName("beta");
		a2.setUsers(new HashSet<>());
		
		user.setAuthorities(Set.of(a1, a2));
		
		this.user = user;
	}
	
	@Test
	public void existsByUsername() {
		boolean res = userRepository.existsByUsername("foo");
		assertThat(res).isTrue();
	}
	
	@Test
	public void findByUsername_checkEverythinExceptAuthorities() {
		User user = userRepository.findByUsername("foo").get();
		user.setAuthorities(new HashSet<>());
		this.user.setAuthorities(new HashSet<>());
		assertThat(user.equals(this.user)).isTrue();
		//assertThat(user).usingRecursiveComparison().isEqualTo(this.user);
	}
	
	@Test
	public void findByUsername_checkAuthorities() {
		User user = userRepository.findByUsername("foo").get();
		assertThat(user.getAuthorities().equals(this.user.getAuthorities())).isTrue();
		//assertThat(user.getAuthorities()).containsExactlyInAnyOrderElementsOf(this.user.getAuthorities());
	}
	
	@Test
	public void existsByEmail() {
		boolean res = userRepository.existsByEmail("foo@abc.com");
		assertThat(res).isTrue();
	}
}