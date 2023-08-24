package com.masai.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> exceptionHandler(Exception ex,WebRequest we){
		ErrorDetails er= new ErrorDetails();
		er.setMessage(ex.getMessage());
		er.setDescription(we.getDescription(false));
		return new ResponseEntity<ErrorDetails>(er,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AdminException.class)
	public ResponseEntity<ErrorDetails> adminExceptionHandler(AdminException ex,WebRequest we){
		ErrorDetails er= new ErrorDetails();
		er.setMessage(ex.getMessage());
		er.setDescription(we.getDescription(false));
		return new ResponseEntity<ErrorDetails>(er,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ActivityException.class)
	public ResponseEntity<ErrorDetails> adminExceptionHandler(ActivityException ex,WebRequest we){
		ErrorDetails er= new ErrorDetails();
		er.setMessage(ex.getMessage());
		er.setDescription(we.getDescription(false));
		return new ResponseEntity<ErrorDetails>(er,HttpStatus.NOT_FOUND);
	}
}
