package com.app.ems.exceptions;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> noSuchElemetFoundExceptionHandler(NoSuchElementException noSuchElementException){
		return new ResponseEntity<String>("No such employee found with the given Id, Please check the request", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<String> maxUploadSizeExceededExceptionHandler(MaxUploadSizeExceededException maxUploadSizeExceededException){
		return new ResponseEntity<String>("File size exceeded more than 15 MB", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MaxUploadLimitException.class)
	public ResponseEntity<String> maxUploadLimitExceptionHandler(MaxUploadLimitException maxUploadLimitException){
		return new ResponseEntity<String>("Maximum 15 files can be uploaded", HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>("Please check the request methd type, Invalid type provided", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>("Something went wrong, please contact Admin. ", HttpStatus.BAD_GATEWAY);
	}
	
}
