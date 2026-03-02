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
import com.lpu.boot1.entity.Student;
import com.lpu.boot1.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	
	@PostMapping("/save")
	public Student save(@RequestBody Student student) {
		return studentService.saveStudent(student);
	}
	
	
	@PostMapping("/saveAll")
	public List<Student> save(@RequestBody List<Student> students) {
		return studentService.saveMultipleStudents(students);
	}
	
	
	@GetMapping("/find/id/{id}")
	public Student fetchStudentById(@PathVariable int id) {
		return studentService.getStudent(id);
	}
	
	@GetMapping("/findAll")
	public List<Student> fetchAllStudents(){
		return studentService.getAllStudents();
	}
	
	@DeleteMapping("/delete")
	public String deleteStudentById(@RequestParam int id) 
	{
		return studentService.deleteStudent(id);
	}
	
	@DeleteMapping("/deleteStudent")
	public String deleteStudentById(@RequestBody Student student) 
	{
		return studentService.deleteStudent2(student);
	}
	
	@GetMapping("/find/name/{name}")
	public List<Student> StudByName(@PathVariable String name){
		return studentService.getStudByName(name);
	}
	
	@GetMapping("/find/name2/{name}")
	public List<Student> StudByName2(@PathVariable String name){
		return studentService.getStudByName(name);
	}
	
	@GetMapping("find/phone/{phone}")
	public List<Student> StudByPhone(@PathVariable long phone){
		return studentService.findStudByPhone(phone);
	}
	
	@GetMapping("/findAll/Students")
	public List<Student> AllStudents(){
		return studentService.getAllStuds();
	}
}
