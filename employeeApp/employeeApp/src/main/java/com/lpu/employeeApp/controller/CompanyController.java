package com.lpu.employeeApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lpu.employeeApp.entity.Company;
import com.lpu.employeeApp.entity.Employee;
import com.lpu.employeeApp.service.CompanyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyService service;
	
	@PostMapping("/saveCompany")
	public ResponseEntity<Company> addComp(@Valid @RequestBody Company c) {
		Company comp = service.saveCompany(c);
		return ResponseEntity.status(HttpStatus.CREATED).body(comp);
	}
	
	@PostMapping("/saveCompany2")
	public ResponseEntity<Company> addComp2(@Valid @RequestBody Company c) {
		Company comp = service.saveCompanyAndMapWithEmp(c);
		return new ResponseEntity<Company>(comp,HttpStatus.CREATED);
	}
	
	@PostMapping("/{id}/employees")
	public Company addEmpToComp(@PathVariable int id, @RequestBody List<Employee> employees) {
		return service.saveEmployeeToExistingCompany(id, employees);
	}
	
	@GetMapping("/divide/{n1}/{n2}")
	public String divide(@PathVariable int n1, @PathVariable int n2) {
		return "result=" + n1/n2;
	}
	
	@GetMapping("/{id}/customException")
	public ResponseEntity<Company> findCompanyById(@PathVariable int id) {
		Company comp = service.findCompanyById(id);
		return ResponseEntity.ok(comp);
	}
	
}
