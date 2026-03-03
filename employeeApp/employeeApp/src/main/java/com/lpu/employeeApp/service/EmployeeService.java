package com.lpu.employeeApp.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.lpu.employeeApp.dto.EmployeeDTO;
import com.lpu.employeeApp.entity.Company;
import com.lpu.employeeApp.entity.Employee;
import com.lpu.employeeApp.exception.CompanyNotFoundException;
import com.lpu.employeeApp.exception.EmployeeNotFoundException;
import com.lpu.employeeApp.repository.CompanyRepository;
import com.lpu.employeeApp.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository repository;
	
	@Autowired
	private CompanyRepository repository2;

	
//	public Employee saveEmployee(Employee employee) {
////		employee.getCompany().getEmployees().add(employee);  // No need to do that
//		return repository.save(employee);
//	}
	
//	public Employee saveEmployeeAndMapWithComp(Employee employee) {
//		int cId = employee.getCompany().getId(); 
//		Company comp = repository2.findById(cId).orElseThrow(() -> new CompanyNotFoundException("No company with ID:" + cId + " Exists"));
//		employee.setCompany(comp);
//		return saveEmployee(employee);
//		
//	}
	
	public Employee findEmployeeById(int id) {
		return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee with ID:" + id + " not exist"));
	}
	
	public EmployeeDTO saveEmployeeDTO(EmployeeDTO dto) {
		Employee employee = DTOtoEntity(dto);
		Employee emp = repository.save(employee);
		return entityToDTO(emp);
	}
	
	public Employee DTOtoEntity(EmployeeDTO empDTO) {
		Employee emp = new Employee();
		emp.setAge(empDTO.getAge());
		emp.setEmail(empDTO.getEmail());
		emp.setName(empDTO.getName());
		emp.setPhone(empDTO.getPhone());
//		emp.setCompany(empDTO.getCompany());
		return emp;
	}
	
	public EmployeeDTO entityToDTO(Employee e) {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setAge(e.getAge());
		dto.setEmail(e.getEmail());
		dto.setName(e.getName());
		dto.setPhone(e.getPhone());
		return dto;
	}
	
	
	//When finding an Employee
	@Cacheable(value = "employees", key = "#id")
	public Employee findEmployee(int id) {
		return repository.findById(id).get();
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
