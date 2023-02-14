package com.example.demo.exception.authority;

public class AuthorityNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public AuthorityNotFoundException() {
		super("Authority not found");
	}
}
