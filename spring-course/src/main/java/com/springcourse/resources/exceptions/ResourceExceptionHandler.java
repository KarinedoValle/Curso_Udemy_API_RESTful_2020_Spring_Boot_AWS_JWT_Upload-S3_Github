package com.springcourse.resources.exceptions;

import java.util.Date;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.springcourse.exceptions.NotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ApiError> handleNotFoundException(NotFoundException ex){
		ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage(), new Date());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ApiError> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
		ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), new Date());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ApiError> handleNullPointerException(NullPointerException ex){
		ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage(), new Date());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ApiError> handleDataIntegrityViolationException(DataIntegrityViolationException ex){
		ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), new Date());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ApiError> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex){
		ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), new Date());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ApiError> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex){
		ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), new Date());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
		ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), new Date());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ApiError> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex){
		ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), new Date());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ApiError> handleBadCredentialsException(BadCredentialsException ex){
		ApiError error = new ApiError(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), new Date());
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}
	
	
}
