package com.douglas.readvista.services.exceptions;

public class ValidationException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationException(String message) {
		super(message);
	}
}
