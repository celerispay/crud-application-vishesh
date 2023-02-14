package com.example.demo.exception.user;

public class EmailExistException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public EmailExistException() {
		super("Email already exist");
	}
}
