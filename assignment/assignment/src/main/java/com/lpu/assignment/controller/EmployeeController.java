package com.lpu.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lpu.assignment.entity.Employee;
import com.lpu.assignment.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	
	@PostMapping
	public Employee addEmp(@RequestBody Employee e) {
		return service.saveEmployee(e);
	}
	
	@GetMapping
	public List<Employee> getAllEmp(){
		return service.getAll();
	}
	
	@GetMapping("/find")
	public Employee getEmp(@RequestParam int id) {
		return service.getById(id);
	}
	
	@DeleteMapping("/delete")
	public String deleteEmp(@RequestParam int id) {
		return service.deleteById(id);
	}
	
	@GetMapping("/search/name")
	public List<Employee> getEmpByName(@RequestParam String name){
		return service.empByNames(name);
	}
	
	@GetMapping("/search/department")
	public List<Employee> getEmpByDepartment(@RequestParam String department){
		return service.empByDept(department);
	}
	
	@GetMapping("/search/phone")
	public Employee getEmpByPhone(@RequestParam long phone){
		return service.empByPhone(phone);
	}
	
	@GetMapping("/search/email")
	public Employee getEmpByEmail(@RequestParam String email){
		return service.empByEmail(email);
	}
	
	@PatchMapping("/search/update/{name}/{id}")
	public int updEmpById(@PathVariable String name, @PathVariable int id) {
		return service.updateEmpById(name, id);
	}
	
	@GetMapping("/search/salary/{salary1}/{salary2}")
	public List<Employee> getEmpBySal(@PathVariable double salary1, @PathVariable double salary2){
		return service.empBySal(salary1, salary2);
	}
}
