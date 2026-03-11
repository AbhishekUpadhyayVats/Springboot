package com.lpu.assignment2.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lpu.assignment2.entity.FileData;
import com.lpu.assignment2.entity.MobileImage;
import com.lpu.assignment2.repository.MobileImageRepository;

@RestController
@RequestMapping("/mobileImage")
public class MobileImageController {

	@Autowired
	private MobileImageRepository repository;
	
	@PostMapping("/addImage")
	public ResponseEntity<MobileImage> addMobileImage(@RequestParam("file") MultipartFile file)throws IOException{
		MobileImage m = new MobileImage();
		m.setData(file.getBytes());
		m.setFieldType(file.getContentType());
		m.setFileName(file.getOriginalFilename());
		
		repository.save(m);
		return ResponseEntity.status(HttpStatus.OK).body(m);
	}
	
	@GetMapping("/download/{id}")
	public ResponseEntity<byte[]> download(@PathVariable Long id){
		MobileImage file = repository.findById(id).orElseThrow(() -> new RuntimeException("File not found with ID: " + id));
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + file.getFileName())   //inline -> for display,    attachment -> for download
				.header(HttpHeaders.CONTENT_TYPE, file.getFieldType()).body(file.getData());
	}
}
