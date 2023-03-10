package com.example.demo.exceptionhandler;

import com.example.demo.exceptionhandler.response.*;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exception.UserException;

@RestControllerAdvice
public class Handler {

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
		return ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
	}

	@ResponseStatus(code =HttpStatus.BAD_REQUEST)	
	@ExceptionHandler(UserException.class)
	public InvalidUserExceptionResponse handleInvalidUserException(UserException ex) {
		String data = ex.getMessage();
		return new InvalidUserExceptionResponse(data);
	}
}