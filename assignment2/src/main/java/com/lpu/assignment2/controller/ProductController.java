package com.lpu.assignment2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lpu.assignment2.service.ProductService;
import com.lpu.assignment2.entity.Product;

@RestController

public class ProductController {
	@Autowired
	private ProductService productService;
	
	
	@PostMapping("/saveAllPro")
	public List<Product> saveAllPro(@RequestBody List<Product> ls){
		return  productService.saveAllProduct(ls);
	}
	
	@GetMapping("/page/{pageNumber}/{size}")
	private List<Product> productPage( @PathVariable int pageNumber, @PathVariable int  size){
		return productService.productPagination(pageNumber, size);
	}
	
	@GetMapping("/page/sorting/{pageNumber}/{size}/{field}")
	private List<Product> productPageWithSorting(@PathVariable int pageNumber, @PathVariable int size, @PathVariable String field){
		return productService.productPaginationWithSorting(pageNumber, size, field);
	}
	
	@GetMapping("page/descSorting/{field}")
	private List<Product> productPageDesc(@PathVariable String field){
		return productService.sortProductByFieldInDesc(field);
	}

	@GetMapping("page/ascSorting/{field}")
	private List<Product> productPageAsc(@PathVariable String field){
		return productService.sortProductByFieldInAsc(field);
	}
}