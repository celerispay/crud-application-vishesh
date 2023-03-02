package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.example.demo.entity.Authority;
import com.example.demo.entity.User;

@DataJpaTest
@Sql(scripts = "classpath:insert.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:delete.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
class AuthorityRepositoryTest {
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
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
		List<User> users = authorityRepository.findByName("alpha").get().getUsers();
		assertThat(users.size()).isEqualTo(1);
	}
	
	@Test
	public void findByName() {
		Authority authority = authorityRepository.findByName("alpha").get();
		assertThat(authority.getName()).isEqualTo("alpha");
	}
}