package com.lpu.assignment2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lpu.assignment2.dto.MobileDTO;
import com.lpu.assignment2.entity.MobileImage;
import com.lpu.assignment2.repository.MobileImageRepository;
import com.lpu.assignment2.service.MobileServiceImpl;

@RestController
@RequestMapping("/app/mobiles")
public class MobileController {

	@Autowired
	private MobileServiceImpl service;
	
	@Autowired
	private MobileImageRepository repo;
	
	@PostMapping("/addMobile")
	public ResponseEntity<MobileDTO> addingMobile(@RequestBody MobileDTO mobileDTO, @RequestParam Long id){
		MobileImage mobImage = repo.findById(id).get();
		mobileDTO.setMobileImage(mobImage);
		MobileDTO mDTO = service.addMobile(mobileDTO);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(mDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<MobileDTO>> gettingAllMobiles(){
		return ResponseEntity.status(HttpStatus.OK).body(service.getAllMobile());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MobileDTO> gettingMobileById(@PathVariable int id){
		MobileDTO mobileDTO = service.getMobileById(id);
		return ResponseEntity.status(HttpStatus.OK).body(mobileDTO);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MobileDTO> updatingMobile(@PathVariable int id, @RequestBody MobileDTO mobileDTO){
		MobileDTO mobDTO = service.updateMobile(id, mobileDTO);
		return ResponseEntity.status(HttpStatus.OK).body(mobDTO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletingMobileById(@PathVariable int id){
		service.deleteMobileById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Mobile deleted Successfully");
	}
	
	@GetMapping("/byNames/{brandName}")
	public ResponseEntity<List<MobileDTO>> gettingMobileByNames(@PathVariable String brandName){
		List<MobileDTO> mobileDTO = service.getMobileByName(brandName);
		return ResponseEntity.status(HttpStatus.OK).body(mobileDTO);
	}
	
	@GetMapping("/getAllMob/{pageNumber}/{size}")
	public ResponseEntity<List<MobileDTO>> gettingAllMobiles2(@PathVariable int pageNumber, @PathVariable int size){
		List<MobileDTO> mobileDTO = service.getAllMobiles(pageNumber,size);
		return ResponseEntity.status(HttpStatus.OK).body(mobileDTO);
	}
	
//	@GetMapping("/getAllMobAsc/{pageNumber}/{size}/field")
//	public Re
}
