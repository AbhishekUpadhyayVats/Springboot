package com.lpu.StudentManagementSystem.exception;

public class SmsNotFoundException extends RuntimeException{
	public SmsNotFoundException(String message) {
		super(message);
	}
}
