package com.lpu.employeeApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lpu.employeeApp.dto.EmployeeDTO;
import com.lpu.employeeApp.entity.Company;
import com.lpu.employeeApp.entity.Employee;
import com.lpu.employeeApp.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	
	@PostMapping("/saveEmployee")
	public ResponseEntity<EmployeeDTO> addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO){
		EmployeeDTO empDTO = service.saveEmployeeDTO(employeeDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(empDTO);
	}
	
	@PostMapping("/saveEmployee2")
	public ResponseEntity<EmployeeDTO> addEmployee2(@Valid @RequestBody EmployeeDTO employeeDTO){
		EmployeeDTO empDTO = service.saveEmployeeDTO(employeeDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(empDTO);
	}
	
	@GetMapping("/{id}/customException")
	public ResponseEntity<Employee> findEmployeeById(@PathVariable int id) {
		Employee emp = service.findEmployeeById(id);
		return ResponseEntity.ok(emp);
	}
}
