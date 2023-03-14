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
    public void save_whenIdExists_thenUpdateUser() {
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
    public void save_whenIdDoesNotExists_thenAddUser() {
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
    public void deleteById_whenIdExists_thenDeleteUser() {
    	String userId = "b39dd5b7-f3ae-4115-82c8-87a1fbbe3ee1";
    	userRepository.deleteById(userId);
    	assertThat(userRepository.count()).isEqualTo(0);
    }
   
    @Test
    public void findByUsername_whenUsernameExistsAndUppercaseUsername_thenReturnUser() {
    	Optional<User> user = userRepository.findByUsername("JACK");
    	assertThat(user).isPresent();
    }
   
    @Test
    public void findByUsername_whenUsernameExistsAndLowercaseUsername_thenReturnUser() {
    	Optional<User> user = userRepository.findByUsername("jack");
    	assertThat(user).isPresent();
    }
   
    @Test
    public void existsByUsername_whenUsernameExists_thenReturnTrue() {
    	boolean res = userRepository.existsByUsername("Jack");
    	assertThat(res).isTrue();
    }
   
    @Test
    public void existsByUsername_whenUsernameDoesNotExists_thenReturnFalse() {
    	boolean res = userRepository.existsByUsername("James");
    	assertThat(res).isFalse();
    }
    
    @Test
    public void existsByEmail_whenEmailExists_thenReturnTrue() {
    	boolean res = userRepository.existsByEmail("jack@gmail.com");
    	assertThat(res).isTrue();
    }
   
    @Test
    public void existsByEmail_whenEmailDoesNotExists_thenReturnFalse() {
    	boolean res = userRepository.existsByEmail("james@gmail.com");
    	assertThat(res).isFalse();
    }
}