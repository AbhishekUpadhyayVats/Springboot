package com.lpu.boot1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lpu.boot1.entity.College;

@Repository
public interface CollegeRepository extends JpaRepository<College,Integer>{

	//CUSTOM METHODS:-
	//By name
	List<College> findByName(String name);
	//By location
	List<College> findByLocation(String location);
	
	
	//CUSTOM QUERY
	//By name
	@Query("select c from College c where c.name=:name")
	List<College> getCollegeByName(String name);
	
	
	@Query(nativeQuery = true, value = "select * from College")
	List<College> getAllColleges();
}

//🔥 VERY VERY IMPORTANT
//
//👉 The property name after findBy must match the entity field name
//👉 The parameter variable name does NOT matter