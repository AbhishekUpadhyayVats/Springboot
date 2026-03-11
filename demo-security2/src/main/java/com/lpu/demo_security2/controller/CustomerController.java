package com.lpu.demo_security2.controller;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lpu.demo_security2.entity.Customer;
import com.lpu.demo_security2.service.CustomerService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class CustomerController {

	private final CustomerService service;
	public CustomerController(CustomerService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/register")
	public Customer registerCustomer(@RequestBody Customer customer) {
		return service.register(customer);
	}
	
	@GetMapping("/")
	public String homePage() {
		return "Public Home Page";
	}
	
	@GetMapping("/account")
	public String accountPage() {
		return "Account Page";
	}
	
	@GetMapping("/delete")   //->ADMIN
	public String deletePage() {
		return "Account Page";
	}
	
	@GetMapping("/update")    //->ADMIN,USER
	public String updatePage() {
		return "Account Page";
	}
	
	@DeleteMapping("/deleteById/{id}")
	public String deleteById(@PathVariable int id) {
		service.deleteCustomerById(id);
		return "Deleted";
	}
	
	@GetMapping("/findById/{id}")
	public Customer findById(@PathVariable int id) {
		return service.findCustomerById(id);
	}
	
	@PostMapping("/save")
	public String save() {
		return "saved";
	}
	
	@GetMapping("/register2")
	public String frontEnd() {
		return "registered";
	}
	
	@GetMapping("/csrf")
	public CsrfToken csrf(CsrfToken token) {
		return token;
	}
}
