package com.lpu.StudentManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lpu.StudentManagementSystem.entity.Sms;

public interface SmsRepository extends JpaRepository<Sms, Integer>{
//	@Query("SELECT s FROM Sms s WHERE s.email = :email")
	Sms findByEmail(String name);
}
