package com.example.demo.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.entity.*;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthorityRepository authorityRepository; 

    private User user;

    @BeforeEach
    public void initailize() {
        user = new User();
	user.setUsername("Sam");
	user.setPassword("1234");
	user.setEmail("sam@gmail.com");
    }
	
	@Test
	public void defaultUserTableSize() {
	    assertThat(userRepository.count()).isEqualTo(1L);
	}
   
	@Test
	public void addUser_whenUserWithIdExists_countUser() {
    	String userId = "b39dd5b7-f3ae-4115-82c8-87a1fbbe3ee1";
    	user.setId(userId);
    	userRepository.save(user);
        assertThat(userRepository.count()).isEqualTo(1L);
	}

    @Test
    public void addUser_whenUserWithIdExists_checkUserDetails() {
    	String userId = "b39dd5b7-f3ae-4115-82c8-87a1fbbe3ee1";
		user.setId(userId);
		userRepository.save(user);
		Optional<User> u = userRepository.findById(userId);
		assertThat(u).isPresent()
		.get()
		.hasFieldOrPropertyWithValue("id", userId)
		.hasFieldOrPropertyWithValue("username", user.getUsername())
		.hasFieldOrPropertyWithValue("password", user.getPassword())
		.hasFieldOrPropertyWithValue("authorities", user.getAuthorities());
    }
   
    @Test
    public void addUser_whenUserWithIdDoesNotExists_countUser() {
		String userId = UUID.randomUUID().toString();
    	user.setId(userId);
    	userRepository.save(user);
        assertThat(userRepository.count()).isEqualTo(2L);
     
    }
    
    @Test
    public void addUser_whenUserWithIdDoesNotExists_checkUserDetails() {
		String userId = UUID.randomUUID().toString();
		user.setId(userId);
		userRepository.save(user);
		Optional<User> u = userRepository.findById(userId);
		assertThat(u).isPresent()
		.get()
		.hasFieldOrPropertyWithValue("id", userId)
		.hasFieldOrPropertyWithValue("username", user.getUsername())
		.hasFieldOrPropertyWithValue("password", user.getPassword())
		.hasFieldOrPropertyWithValue("authorities", user.getAuthorities());
    }
   
    @Test
    public void addUser_whenUserWithIdDoesNotExists_checkAuthorities() {
    	String userId = UUID.randomUUID().toString();
		user.setId(userId);
		
		Authority a = new Authority();
		String authorityId = UUID.randomUUID().toString();
		a.setId(authorityId);
		a.setName("write");
		
		
		List<Authority> authorities = List.of(a);
		
		authorityRepository.saveAll(authorities);
		
		user.setAuthorities(authorities);
		
		userRepository.save(user);
		Optional<User> u = userRepository.findById(userId);
		
		assertThat(u).isPresent()
		.get()
		.extracting(user -> user.getAuthorities())
		.usingRecursiveComparison()
		.ignoringCollectionOrder()
		.isEqualTo(authorities);
    }
}