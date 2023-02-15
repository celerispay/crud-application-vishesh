package com.example.demo.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Authority {
	@Id
	private String id;

	@NotBlank(message="Name cannot be blank")
	private String name;

	@ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
	private Set<User> users;
}
