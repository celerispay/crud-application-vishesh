package com.example.demo.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserException extends Exception {
    private static final long serialVersionUID = 1L;
    
    private final String exception;

	public UserException(String exception) {
        super(exception);
        this.exception = exception;
    } 
}
