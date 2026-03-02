package com.lpu.boot1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lpu.boot1.entity.College;
import com.lpu.boot1.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>{
//CRUD methods for student
//	🔥 That’s it. No implementation needed.
//
//	Spring automatically gives:
//
//	save()
//
//	findById()
//
//	findAll()
//
//	deleteById()
//
//	update (via save)
	
	
	
	//CUSTOM METHODS:-
	//By name
	List<Student> findByName(String name);
	//By phone
	List<Student> findByPhone(long phone);
	
	
	//CUSTOM QUERY
	//By name
	@Query("select s from Student s where s.name=:name")
	List<Student> getStudentByName(String name);
	
	
	@Query(nativeQuery = true, value = "select * from Student")
	List<Student> getAllStudents();
}
