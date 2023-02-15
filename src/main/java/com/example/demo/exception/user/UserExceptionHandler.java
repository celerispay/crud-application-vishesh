package com.example.demo.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler  {

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UserNotFoundException.class)
	public String handleUserNotFoundException(UserNotFoundException ex) {
		return ex.getMessage();
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UsernameTakenException.class)
	public String handleUsernameTakenException(UsernameTakenException ex) {
		return ex.getMessage();
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(EmailExistException.class)
	public String handleEmailExistException(EmailExistException ex) {
		return ex.getMessage();
	}
	
	
}