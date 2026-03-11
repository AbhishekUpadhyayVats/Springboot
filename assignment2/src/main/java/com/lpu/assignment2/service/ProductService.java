package com.lpu.assignment2.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import  com.lpu.assignment2.entity.Product;
import  com.lpu.assignment2.repository.ProductRepository;

@Service
public class ProductService {
	private  ProductRepository productRepository;
	@Autowired
	
	public ProductService(ProductRepository productRepository) {
		super();
		this.productRepository=productRepository;
	}
	
	public List<Product> saveAllProduct(List<Product> p) {
		return productRepository.saveAll(p);
		 
	}
	
	public List<Product> productPagination(int pageNumber,int size) {
		Pageable pageable=PageRequest.of(pageNumber, size);
		
		return productRepository.findAll(pageable).getContent();
	}
	
	//field -> id, name, price
	public List<Product> sortProductByFieldInDesc(String field){
		return productRepository.findAll(Sort.by(field).descending());
	}
	
	//field -> id, name, price
	public List<Product> sortProductByFieldInAsc(String field){
		return productRepository.findAll(Sort.by(field).ascending());
	}
	
	//Pagination with sorting
	public List<Product> productPaginationWithSorting(int pageNumber,int size, String field) {
		Pageable pageable=PageRequest.of(pageNumber, size, Sort.by(field).ascending());
		
		return productRepository.findAll(pageable).getContent();
	}
}