package com.example.demo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
public class User {
	@Id
	private String id;

	@Size(min=2, max=45, message="Invalid username - Username cannot contain less than 2 or more than 45 characters")
	private String username;
	
	private String password;

	@ManyToMany()
	@JoinTable(
			  name = "user_authority", 
			  joinColumns = @JoinColumn(name = "user_id"), 
			  inverseJoinColumns = @JoinColumn(name = "authority_id"))
	private List<Authority> authorities;
}