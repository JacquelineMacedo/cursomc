package com.cursomc.springboot.resources.exception;

import static java.lang.System.currentTimeMillis;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.status;

import javax.servlet.http.HttpServlet;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cursomc.springboot.services.exceptions.DataIntegrityException;
import com.cursomc.springboot.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourseExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServlet request) {

		StandardError error = new StandardError(NOT_FOUND.value(), e.getMessage(),
				System.currentTimeMillis());
		return ResponseEntity.status(NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException exception, HttpServlet request) {

		StandardError error = new StandardError(BAD_REQUEST.value(), exception.getMessage(),
				currentTimeMillis());
		return status(BAD_REQUEST).body(error);
	}
}
