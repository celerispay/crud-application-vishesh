package com.example.demo.exceptionhandler;

import com.example.demo.exceptionhandler.response.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exception.UserException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ValidationExceptionResponse handleValidationException(MethodArgumentNotValidException ex) {
		List<String> data = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(error -> error.getDefaultMessage())
				.collect(Collectors.toUnmodifiableList());
		return new ValidationExceptionResponse(data);
	}

	@ResponseStatus(code =HttpStatus.BAD_REQUEST)	
	@ExceptionHandler(UserException.class)
	public InvalidUserExceptionResponse handleInvalidUserException(UserException ex) {
		String data = ex.getMessage();
		return new InvalidUserExceptionResponse(data);
	}
}



