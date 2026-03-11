package com.lpu.StudentManagementSystem.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(SmsNotFoundException.class)
	public ResponseEntity<Map<String,String>> handleSmsNotFoundException(SmsNotFoundException ex){
		Map<String,String> map = new HashMap<String,String>();
		map.put("error", ex.getMessage());
		
		return new ResponseEntity<Map<String,String>>(map, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)   //It is for Validation....like if you did not pass proper inputs according to Validation like @NotNull or @NotBlank or @Email
	public ResponseEntity<Map<String,String>> handleMethodArgumentInvalidException(MethodArgumentNotValidException ex){
		Map<String, String> map = new HashMap<String, String>();
		
		ex.getBindingResult().getFieldErrors().forEach(n -> map.put(n.getField(), n.getDefaultMessage()));
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String,String>> handleException(Exception ex){
		Map<String,String> map = new HashMap<String,String>();
		map.put("error:", ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
	}
}
