package com.demos.demo.comments.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ErrorInfo> methodArgumentNotValidException(ApiException e) {
		ErrorInfo errorInfo = new ErrorInfo(e.getCode(), HttpStatus.SERVICE_UNAVAILABLE.value(), e.getMessage());
		return new ResponseEntity<>(errorInfo, HttpStatus.SERVICE_UNAVAILABLE);
	}
}
