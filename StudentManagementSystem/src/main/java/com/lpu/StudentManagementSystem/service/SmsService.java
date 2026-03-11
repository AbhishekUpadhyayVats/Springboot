package com.lpu.StudentManagementSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.lpu.StudentManagementSystem.entity.Sms;
import com.lpu.StudentManagementSystem.exception.SmsNotFoundException;
import com.lpu.StudentManagementSystem.repository.SmsRepository;
import org.springframework.data.domain.Pageable;


@Service
public class SmsService {

	@Autowired
	private SmsRepository smsRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@CachePut(value = "sms", key = "#result.id")   //To get id from returned object then use #result.id
	public Sms addStudent(Sms sms) {
		String encodedPassword = passwordEncoder.encode(sms.getPassword());
		sms.setPassword(encodedPassword);
		return smsRepo.save(sms);
	}
	
	@Cacheable(value = "sms", key = "#id")
	public Sms findStudent(int id) {
		return smsRepo.findById(id).orElseThrow(() -> new SmsNotFoundException("Student with ID:" + id + " does not exists"));
	}
	
	@Cacheable(value = "sms", key = "#pageNumber" + '-' + "#size")
	public List<Sms> findAllStudent(int pageNumber, int size){
		Pageable pageable = PageRequest.of(pageNumber, size);
		return smsRepo.findAll(pageable).getContent();
	}
	
	@CachePut(value = "sms", key = "#id")
	public Sms updateStudent(int id, Sms sms) {
		Sms sms1 = smsRepo.findById(id).orElseThrow(() -> new SmsNotFoundException("Student with ID:" + id + " does not exists"));
		sms1.setAssignmentFile(sms.getAssignmentFile());
		sms1.setEmail(sms.getEmail());
		sms1.setMarks(sms.getMarks());
		sms1.setName(sms.getName());
		sms1.setPassword(passwordEncoder.encode(sms.getPassword()));
		sms1.setProfileImages(sms.getProfileImages());
		sms1.setRole(sms.getRole());
		
		return smsRepo.save(sms1);
	}
	
	@CacheEvict(value = "sms", key = "#id")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteStudent(int id) {
		smsRepo.deleteById(id);
	}
	
	@CacheEvict(value = "sms", allEntries = true)
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteAllStudent() {
		smsRepo.deleteAll();;
	}
	
	@CacheEvict(value = "sms", key = "#sms.id")    //To get id from parameter passed object then use #Object.id
//	@PostAuthorize("returnObject.name == authentication.name")     //The issue is.......returnObject only works when the method returns something........this method returns nothing thats why we cannot use it....but their use returnObject.email
	@PostAuthorize("#sms.email == authentication.name")     //This automatically returns whatever getUsername() returns....which in our code returns sms.getEmail()
	public void deleteItself(Sms sms) {
		smsRepo.delete(sms);
	}
}
