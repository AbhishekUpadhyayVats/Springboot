package com.lpu.employeeApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lpu.employeeApp.entity.Company;
import com.lpu.employeeApp.entity.Employee;
import com.lpu.employeeApp.exception.CompanyNotFoundException;
import com.lpu.employeeApp.repository.CompanyRepository;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepository repository;
	
	public Company saveCompany(Company company) {
		return repository.save(company);
	}
	
	public Company saveCompanyAndMapWithEmp(Company company) {
		company.getEmployees().forEach(n -> n.setCompany(company));
		return repository.save(company);

	}
	
	public Company saveEmployeeToExistingCompany(int comId, List<Employee> newEmployees) {
		Company company = repository.findById(comId).orElse(null);
		company.getEmployees().addAll(newEmployees);
		
//		return saveCompany(company); // set null to company_id because for new object of parent and child only internall synchronization happens not for existing parent
		return saveCompanyAndMapWithEmp(company);
	}

	public Company findCompanyById(int id) {
		return repository.findById(id).orElseThrow(() -> new CompanyNotFoundException("Company with ID:" + id + " not exist"));
	}
}
