package com.example.demo.repository;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.User;

public interface UserRepository extends CrudRepository<User, String> {
	Optional<User> findByUsername(String username);
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
}
