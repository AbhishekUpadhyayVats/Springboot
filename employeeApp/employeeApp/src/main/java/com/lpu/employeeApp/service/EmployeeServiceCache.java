package com.lpu.employeeApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.lpu.employeeApp.entity.Employee;
import com.lpu.employeeApp.exception.EmployeeNotFoundException;
import com.lpu.employeeApp.repository.EmployeeRepository;

@Service
public class EmployeeServiceCache {
	
	@Autowired
	private EmployeeRepository repository;

	//When finding an Employee
	@Cacheable(value = "employees", key = "#id")
	public Employee findEmployee(int id) {
		System.err.println("Fetching from DB....");
		return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee with id: " + id + " does not exist"));
	}
	
	
	// When adding and updating then need to update cache as well
	@CachePut(value = "employees", key = "#result.id")
	public Employee addEmployee(Employee employee) {
		return repository.save(employee);
	}
	
	
	// Remove cache when deleting
	@CacheEvict(value = "employees", key = "#id")
	public void deleteEmployee(int id) {
		repository.deleteById(id);
	}
}
