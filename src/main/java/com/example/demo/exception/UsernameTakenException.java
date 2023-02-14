package com.example.demo.exception;

public class UsernameTakenException extends Exception {
	private static final long serialVersionUID = 1L;

	public UsernameTakenException() {
		super("Username is already taken");
	}
}
