package com.example.demo.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralException {
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<String> handleValidationException(MethodArgumentNotValidException ex) {
		return ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(error -> error.getDefaultMessage())
				.collect(Collectors.toUnmodifiableList());
	}

	@ResponseStatus(code =HttpStatus.BAD_REQUEST)	
	@ExceptionHandler(UserException.class)
	public String handleInvalidUserException(UserException ex) {
		return ex.getMessage();
	}
}