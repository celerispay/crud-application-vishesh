package com.example.demo.exception;

public class EmailExistException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public EmailExistException() {
		super("Email already exist");
	}
}
