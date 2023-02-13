package com.example.demo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

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

	private String name;

	@ManyToMany(mappedBy = "authorities")
	private List<User> users;
}
