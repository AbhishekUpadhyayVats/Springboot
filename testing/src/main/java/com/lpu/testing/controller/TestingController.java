package com.lpu.testing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.lpu.testing.entity.Testing;
import com.lpu.testing.repository.TestingRepository;

@RestController
@RequestMapping("/testing")
public class TestingController {
	
	@Autowired
	private TestingRepository testingRepo;
	@Autowired
	private PasswordEncoder encoder;

	@PostMapping("/add")
	public Testing addingTest(@RequestPart("testing") Testing testing) {
		String password = testing.getPassword();
		testing.setPassword(encoder.encode(password));
		return testingRepo.save(testing);
	}
	
	@GetMapping("/getTest")
	public String gettingTest() {
		return "Students";
	}
	
	@GetMapping("/hello")
	public String gettingHello() {
		return "Hello";
	}
}
