package com.lpu.assignment.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lpu.assignment.entity.Employee;

import jakarta.transaction.Transactional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

	// find by name
	List<Employee> findByName(String name);
	
	// find by department
	List<Employee> findByDepartment(String department);
	
	// find by phone
	@Query("Select e from Employee e where e.phone=:phone")
	Employee findByPhone(long phone);
	
	//find by email
	@Query(nativeQuery = true, value = "select * from Employee where email = :email")
	Employee findByEmail(String email);

	@Modifying
	@Transactional
	@Query("Update Employee e Set e.name = :name where e.id = :id")
	int updateById(String name, int id);
	
	@Query("Select e from Employee e where e.salary between :salary1 and :salary2")
	List<Employee> getEmpBySalary(double salary1, double salary2);
}
