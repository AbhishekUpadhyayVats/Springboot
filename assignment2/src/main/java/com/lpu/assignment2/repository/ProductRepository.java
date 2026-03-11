package com.lpu.assignment2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lpu.assignment2.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}