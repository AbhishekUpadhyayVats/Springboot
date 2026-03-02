package com.lpu.assignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.lpu.assignment.entity.Employee;
import com.lpu.assignment.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;
	
	public Employee saveEmployee(@RequestBody Employee e) {
		return repository.save(e);
	}
	
	public List<Employee> getAll() {
		return repository.findAll();
	}
	
	public Employee getById(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public String deleteById(int id) {
		Employee e = repository.findById(id).get();
		repository.deleteById(id);
		return "Deleted: " + e;
	}
	
	public List<Employee> empByNames(String name){
		return repository.findByName(name);
	}
	
	public List<Employee> empByDept(String department){
		return repository.findByDepartment(department);
	}
	
	public Employee empByPhone(long phone) {
		return repository.findByPhone(phone);
	}
	
	public Employee empByEmail(String email) {
		return repository.findByEmail(email);
	}
	
	public int updateEmpById(String name, int id) {
		return repository.updateById(name, id);
	}
	
	public List<Employee> empBySal(double salary1, double salary2){
		return repository.getEmpBySalary(salary1, salary2);
	}
}
