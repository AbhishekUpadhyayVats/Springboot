package com.lpu.demo_security2.service;

import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lpu.demo_security2.entity.Customer;
import com.lpu.demo_security2.repository.CustomerRepo;

@Service
public class CustomerService {

	private final CustomerRepo customerRepo;
	private final PasswordEncoder passwordEncoder;
	
	public CustomerService(CustomerRepo customerRepo, PasswordEncoder passwordEncoder) {
		super();
		this.customerRepo = customerRepo;
		this.passwordEncoder = passwordEncoder;
	}
	
	public Customer register(Customer customer) {
		String encodedPass = passwordEncoder.encode(customer.getPassword());
		customer.setPassword(encodedPass);
		return customerRepo.save(customer);
	}
	
	@PostAuthorize("returnObject.name == authentication.name")
	public Customer findCustomerById(int id) {
		return customerRepo.findById(id).get();
	}
	
//	@PostAuthorize("returnObject.name == authentication.name")
	public void deleteCustomerById(int id) {
		customerRepo.deleteById(id);
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	public List<Customer> findAllCustomer(){
		return customerRepo.findAll();
	}
}
