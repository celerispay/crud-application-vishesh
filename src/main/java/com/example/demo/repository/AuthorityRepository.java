package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Authority;


public interface AuthorityRepository extends CrudRepository<Authority, String> {
	Optional<Authority> findByName(String name);
}
