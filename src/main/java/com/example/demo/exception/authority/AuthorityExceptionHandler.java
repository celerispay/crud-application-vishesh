package com.example.demo.exception.authority;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthorityExceptionHandler {

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(AuthorityNotFoundException.class)
	public String handleAuthorityNotFoundException(AuthorityNotFoundException ex) {
		return ex.getMessage();
	}
}
