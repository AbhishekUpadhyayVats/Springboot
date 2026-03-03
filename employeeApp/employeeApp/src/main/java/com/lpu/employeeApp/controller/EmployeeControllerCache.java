package com.lpu.employeeApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lpu.employeeApp.entity.Employee;
import com.lpu.employeeApp.service.EmployeeServiceCache;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employeeCache")
public class EmployeeControllerCache {

	@Autowired
	private EmployeeServiceCache cacheService;
	
	@PostMapping("/addEmployee")
	public ResponseEntity<Employee> addingEmployee(@Valid @RequestBody Employee employee){
		Employee emp = cacheService.addEmployee(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(emp);
	}
	
	@GetMapping("/getEmployee/{id}")
	public ResponseEntity<Employee> gettingEmployee(@PathVariable int id){
		Employee emp = cacheService.findEmployee(id);
		return ResponseEntity.ok(emp);
	}
	
	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<Employee> deletingEmployee(@PathVariable int id){
		cacheService.deleteEmployee(id);
		
		return ResponseEntity.noContent().build();
	}
}
