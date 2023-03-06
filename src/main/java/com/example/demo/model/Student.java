package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Student {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
}