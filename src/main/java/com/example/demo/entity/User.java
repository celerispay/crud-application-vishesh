package com.example.demo.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

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

	@NotBlank(message="Please provide a username")
	@Length(min=2, max=45, message="Invalid username - Username must contain more than 1 and less than 46 characters")
	private String username;

	@NotBlank(message="Please provide a password")
	@Length(min=5, message="Invalid password - Password atleast be 5 characters long")
	private String password;

	@NotBlank(message="Please provide an email")
	@Email(message = "Invalid email")
	private String email;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			  name = "user_authority", 
			  joinColumns = @JoinColumn(name = "user_id"), 
			  inverseJoinColumns = @JoinColumn(name = "authority_id"))
	private Set<Authority> authorities;
}