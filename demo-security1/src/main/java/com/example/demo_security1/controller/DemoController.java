package com.example.demo_security1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	
	@GetMapping("/reg")
	public String getReg() {
		return "Registeration Page";
	}
	
	@GetMapping("/hiii")
	public String getHii() {
		return "<h1>HIIIII<h1>";
	}
	
	@GetMapping("/home")
	public String getHome() {
		return "Home";
	}
}
