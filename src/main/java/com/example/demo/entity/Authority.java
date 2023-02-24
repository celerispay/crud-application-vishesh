package com.example.demo.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Authority {
	@Id
	private String id;

	@Size(min=2, max=45, message="Authority must contains character between 2 and 45 characters (Inclusive)")
	@NotBlank(message="Name cannot be blank")
	private String name;

	@ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
	@EqualsAndHashCode.Exclude
	private Set<User> users;
}
