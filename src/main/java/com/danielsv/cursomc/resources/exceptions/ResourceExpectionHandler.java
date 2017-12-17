package com.danielsv.cursomc.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.danielsv.cursomc.services.exceptions.ObjectNotFoundExpection;

@ControllerAdvice
public class ResourceExpectionHandler {
	
	@ExceptionHandler(ObjectNotFoundExpection.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundExpection e, 
															HttpServletRequest request) {
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), 
				e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
}
