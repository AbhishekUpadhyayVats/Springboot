package com.lpu.boot1.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/lpu")
@RestController
public class LpuController {
	
	// http://localhost:8080/lpu/m1
	@RequestMapping("/m1")
	public String m1() {
		return "m1 saved";
	}
	
	
	// http://localhost:8080/lpu/student
	@PostMapping("/student")
	public String saveStudent() {
		return "student saved";
	}
	
	
	// http://localhost:8080/lpu/trainer
	@PostMapping("/trainer")
	public String saveTrainer() {
		return "trainer saved";
	}
	
	
	// http://localhost:8080/lpu/security
	@PostMapping("/security")
	public String saveSecurity() {
		return "security saved";
	}
}
