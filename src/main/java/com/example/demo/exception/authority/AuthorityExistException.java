package com.example.demo.exception.authority;

public class AuthorityExistException extends Exception {
	private static final long serialVersionUID = 1L;

	public AuthorityExistException() {
		super("Authority already exist");
	}
}
