package com.douglas.readvista.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.douglas.readvista.services.exceptions.DataIntegrityViolationException;
import com.douglas.readvista.services.exceptions.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	private ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException ex,
			HttpServletRequest request) {

		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				"Object not found", ex.getMessage(), request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex,
			HttpServletRequest request){
		
		StandardError error = new StandardError(
				System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value(),
				"Data breach",
				ex.getMessage(),
				request.getRequestURI()
				);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

		}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validationErrors(MethodArgumentNotValidException ex,
			HttpServletRequest request){
		
		ValidationError errors = new ValidationError(
				System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value(),
				"Validation error",
				"Error validating fields",
				request.getRequestURI()
				);
		
		for(FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.addError(error.getField(), error.getDefaultMessage());
		}
	
	 	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}	
}
