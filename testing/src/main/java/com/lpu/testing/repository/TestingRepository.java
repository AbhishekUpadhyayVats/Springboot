package com.lpu.testing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lpu.testing.entity.Testing;

public interface TestingRepository extends JpaRepository<Testing, Integer>{

	Testing findByEmail(String email);
}
