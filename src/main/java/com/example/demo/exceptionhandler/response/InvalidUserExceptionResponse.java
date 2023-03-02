package com.example.demo.exceptionhandler.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InvalidUserExceptionResponse {
	private String error;
}