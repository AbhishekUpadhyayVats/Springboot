package com.lpu.boot1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lpu.boot1.entity.College;
import com.lpu.boot1.service.CollegeService;

@RequestMapping("/college")
@RestController
public class CollegeController {

	@Autowired
	private CollegeService collegeService;
	
	
	@PostMapping("/save")
	public College save(@RequestBody College college) {
		return collegeService.saveCollege(college);
	}
	
	
	@PostMapping("/saveAll")
	public List<College> save(@RequestBody List<College> colleges) {
		return collegeService.saveMultipleColleges(colleges);
	}
	
	
	@GetMapping("/find/id/{id}")
	public College fetchCollegeById(@PathVariable int id) {
		return collegeService.getCollege(id);
	}
	
	@GetMapping("/findAll")
	public List<College> fetchAllColleges(){
		return collegeService.getAllColleges();
	}
	
	@DeleteMapping("/delete")
	public String deleteCollegeById(@RequestParam int id) 
	{
		return collegeService.deleteCollege(id);
	}
	
	@DeleteMapping("/deleteCollege")
	public String deleteCollegeById(@RequestBody College college) 
	{
		return collegeService.deleteCollege2(college);
	}
	
	@GetMapping("/find/name/{name}")
	public List<College> CollByName(@PathVariable String name){
		return collegeService.getCollByName(name);
	}
	
	@GetMapping("/find/name2/{name}")
	public List<College> CollByName2(@PathVariable String name){
		return collegeService.getCollByName(name);
	}
	
	@GetMapping("find/location/{loc}")
	public List<College> CollByLocation(@PathVariable String loc){
		return collegeService.findCollByLocation(loc);
	}
	
	@GetMapping("/findAll/Colleges")
	public List<College> AllColl(){
		return collegeService.getAllColl();
	}
}
