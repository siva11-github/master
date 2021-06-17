package com.learn.ecommerce.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request)
	{
		List<String> details =new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error =new ErrorResponse(400L, details);
		return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);

	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> details =new ArrayList<>();
		
			for(ObjectError error : ex.getBindingResult().getAllErrors()) {
				
			details.add(error.getDefaultMessage());
		}
		ErrorResponse error =new ErrorResponse(400L, details);
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleStudentNotFound(UserNotFoundException ex, WebRequest request)
	{
		List<String> details =new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error =new ErrorResponse(400L, details);
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request)
	{
		List<String> details =new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error =new ErrorResponse(400L, details);
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);

	}
	
	
}
