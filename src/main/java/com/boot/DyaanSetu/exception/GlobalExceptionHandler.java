package com.boot.DyaanSetu.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException ex) {
		
		Map<String, Object> errorResponse = new HashMap<>();
		errorResponse.put("timestamp", LocalDateTime.now());
		errorResponse.put("error ", ex.getMessage());
		errorResponse.put("status" , HttpStatus.BAD_REQUEST.value());
		
		return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
		
	}
	
}
