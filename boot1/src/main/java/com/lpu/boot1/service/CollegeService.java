package com.lpu.boot1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lpu.boot1.entity.College;
import com.lpu.boot1.repository.CollegeRepository;

@Service
public class CollegeService {
	
	private CollegeRepository repository;
	
	@Autowired //Its not mandatory if there is only one constructor
	public CollegeService(CollegeRepository repository) {
		this.repository = repository;
	}

	public CollegeService() {
		super();
	}

	public College saveCollege(College college) {
		return repository.save(college);
	}
	
	public List<College> saveMultipleColleges(List<College> colleges){
		return repository.saveAll(colleges);
	}
	
	public College getCollege(int id) {
//		return repository.findById(id).get();
		return repository.findById(id).orElse(null);  // with that no internal server error if student with this id not present
	}
	
	public List<College> getAllColleges(){
		return repository.findAll();
	}
	
	public String deleteCollege(int id) {
		College c = repository.findById(id).get();
		repository.deleteById(id);
		return "Deleted" + c;
	}
	
	public String deleteCollege2(College college) {
//		College c = repository.findById(id).get();
		repository.delete(college);
		return "Deleted" + college;
	}
	
	
	public List<College> getCollByName(String name){
		return repository.getCollegeByName(name);
	}
	
	public List<College> findCollByName(String name){
		return repository.findByName(name);
	}
	
	public List<College> findCollByLocation(String location){
		return repository.findByLocation(location);
	}
	
	public List<College> getAllColl(){
		return repository.getAllColleges();
	}
	
}
