package com.lpu.employeeApp.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<Map<String,String>> handleArithmeticEx(ArithmeticException ex) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("error:", ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
	}
	
	@ExceptionHandler(CompanyNotFoundException.class)
	public ResponseEntity<Map<String,String>> handleCompanyNotFoundException(CompanyNotFoundException ex){
		Map<String, String> map = new HashMap<String,String>();
		map.put("error:", ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		Map<String, String> map = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> map.put(error.getField(), error.getDefaultMessage()));
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
	}
	
	//It will handle all other Exception which is not mentioned above or except method you have written
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> handleAllException(Exception ex){
		Map<String, String> map = new HashMap<>();
		map.put("error", ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
	}
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleEmployeeNotFoundException(EmployeeNotFoundException ex){
		Map<String, String> map = new HashMap<>();
		map.put("error:", ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
	}
}

/*
 * ❓ What do we call this in one term?

👉 THIS IS CALLED:

✅ Global Exception Handling
or
✅ Centralized Exception Handling
 */
