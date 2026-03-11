package com.lpu.demo_security2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lpu.demo_security2.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{
	Customer findByName(String email);
}
