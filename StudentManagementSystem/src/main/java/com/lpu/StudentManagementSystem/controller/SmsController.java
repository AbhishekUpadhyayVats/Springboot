package com.lpu.StudentManagementSystem.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lpu.StudentManagementSystem.entity.Sms;
import com.lpu.StudentManagementSystem.service.SmsService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
//@CrossOrigin(origins = "http://localhost:5173")   //Not needed here because already configured in SecurityConfig
@RequestMapping("/student")
public class SmsController {

	@Autowired
	private SmsService service;
	
	@PostMapping("/add")
	public ResponseEntity<Sms> addingStudent(@Valid @RequestPart("sms") Sms sms, @RequestPart("profileImage") MultipartFile image, @RequestPart("assignmentFile") MultipartFile file) throws IOException{
		sms.setAssignmentFile(file.getBytes());
		sms.setProfileImages(image.getBytes());
		Sms sms1 = service.addStudent(sms);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(sms1);
	}
	
	@GetMapping("/getAllStudents/{pageNumber}/{size}")
	public ResponseEntity<List<Sms>> getAllStudents(@PathVariable int pageNumber, @PathVariable int size){
		List<Sms> sms = service.findAllStudent(pageNumber, size);
		
		return ResponseEntity.status(HttpStatus.OK).body(sms);
	}
	
	@GetMapping("/getStudent/{id}")
	public ResponseEntity<Sms> getStudent(@PathVariable int id){
		return ResponseEntity.ok(service.findStudent(id));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Sms> updateStudent(
	        @PathVariable int id,
	        @RequestPart("sms") Sms sms,
	        @RequestPart("profileImage") MultipartFile image,
	        @RequestPart("assignmentFile") MultipartFile file) throws IOException {

	    sms.setProfileImages(image.getBytes());
	    sms.setAssignmentFile(file.getBytes());

	    Sms updatedStudent = service.updateStudent(id, sms);

	    return ResponseEntity.ok(updatedStudent);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable int id){
		service.deleteStudent(id);
		return ResponseEntity.ok("Student Deleted");
	}
	
	@DeleteMapping("/deleteAll")
	public ResponseEntity<String> deleteAllStudent(){
		service.deleteAllStudent();
		return ResponseEntity.status(HttpStatus.OK).body("All Students Deleted");
	}
	
	@DeleteMapping("/deletingItself")
	public ResponseEntity<String> deletingItself(@RequestBody Sms sms){
		service.deleteItself(sms);
		return ResponseEntity.status(HttpStatus.OK).body("Deleted Myself");
	}
}
