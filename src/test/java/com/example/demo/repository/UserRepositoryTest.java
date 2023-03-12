package com.example.demo.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.*;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
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

	// Authority a = new Authority();
	// a.setId("1");
	// a.setName("random");
	//
	// user.setAuthorities(List.of(a));
	//
	// Transaction t = new Transaction();
	// t.setId("101");
	// t.setAmount(BigDecimal.valueOf(123123));
 //        t.setTransactionReference("jfaspfidj");
	// t.setUser(user);
    }
	
        @Test
        public void defaultUserTableSize() {
	    assertThat(userRepository.count()).isEqualTo(1L);
	}

    @Test
    public void addUser_whenUserWithIdExists() {
	user.setId("b39dd5b7-f3ae-4115-82c8-87a1fbbe3ee1");
	userRepository.save(user);
        assertThat(userRepository.count()).isEqualTo(1L);
    }

    @Test
    public void addUser_whenUserWithIdDoesNotExists() {
	user.setId("b39dd5b7-f3ae-4115-82c8-87a1fbbe3ee2");
	userRepository.save(user);
        assertThat(userRepository.count()).isEqualTo(2L);
    }
}
