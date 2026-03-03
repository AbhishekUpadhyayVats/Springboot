package com.lpu.employeeApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lpu.employeeApp.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer>{

}
