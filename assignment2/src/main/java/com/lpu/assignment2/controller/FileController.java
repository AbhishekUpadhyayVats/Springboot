package com.lpu.assignment2.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lpu.assignment2.entity.FileData;
import com.lpu.assignment2.repository.FileRepository;

@RestController
@RequestMapping("/api")
public class FileController {
	
	@Autowired
	private FileRepository repository;
	
	@PostMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file) throws IOException{
		FileData f = new FileData();
		f.setFileName(file.getOriginalFilename()); //Data
		f.setFileType(file.getContentType()); //Pdf
		f.setData(file.getBytes());
		
		FileData saved = repository.save(f);
		
		return "File Saved in DB with ID: " + saved.getId();		
	}
	
	@GetMapping("/download/{id}")
	public ResponseEntity<byte[]> download(@PathVariable Long id){
		FileData file = repository.findById(id).orElseThrow(() -> new RuntimeException("File not found with ID: " + id));
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + file.getFileName())   //inline -> for display,    attachment -> for download
				.header(HttpHeaders.CONTENT_TYPE, file.getFileType()).body(file.getData());
	}
}
