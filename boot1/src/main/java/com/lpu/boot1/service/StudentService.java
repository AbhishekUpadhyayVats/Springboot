package com.lpu.boot1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lpu.boot1.entity.Student;
import com.lpu.boot1.repository.StudentRepository;

@Service
public class StudentService {
	
	private StudentRepository repository;
	
	@Autowired //Its not mandatory if there is only one constructor
	public StudentService(StudentRepository repository) {
		this.repository = repository;
	}

	public StudentService() {
		super();
	}


	public Student saveStudent(Student student) {
		return repository.save(student);
	}
	
	public List<Student> saveMultipleStudents(List<Student> students){
		return repository.saveAll(students);
	}
	
	public Student getStudent(int id) {
//		return repository.findById(id).get();
		return repository.findById(id).orElse(null);  // with that no internal server error if student with this id not present
	}
	
	public List<Student> getAllStudents(){
		return repository.findAll();
	}
	
	public String deleteStudent(int id) {
		Student s = repository.findById(id).get();
		repository.deleteById(id);
		return "Deleted" + s;
	}
	
	public String deleteStudent2(Student student) {
//		Student s = repository.findById(id).get();
		repository.delete(student);
		return "Deleted" + student;
	}
	
	
	public List<Student> getStudByName(String name){
		return repository.getStudentByName(name);
	}
	
	public List<Student> findStudByName(String name){
		return repository.findByName(name);
	}
	
	public List<Student> findStudByPhone(long phone){
		return repository.findByPhone(phone);
	}
	
	public List<Student> getAllStuds(){
		return repository.getAllStudents();
	}
}
