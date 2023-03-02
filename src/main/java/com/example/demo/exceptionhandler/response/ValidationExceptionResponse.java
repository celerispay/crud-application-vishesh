package com.example.demo.exceptionhandler.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ValidationExceptionResponse {
	private List<String> voilation;
}