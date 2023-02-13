package com.example.demo.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, String> {
}
