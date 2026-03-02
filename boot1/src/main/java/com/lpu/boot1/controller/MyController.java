package com.lpu.boot1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lpu.boot1.entity.Student;

@RestController
public class MyController {
	
	@GetMapping("/hii")
	public String takeReg() {
		return "byeee";
	}
	
	@GetMapping("/takeData") //http://localhost:8080/takeData?id=108&name=Abhishek Upadhyay
	public String takeData(@RequestParam int id, @RequestParam String name) {
		return "ID=" + id + ", NAME="  + name;
	}
	
	@GetMapping("/college")  //http://localhost:8080/college?id=108&name=Abhishek Upadhyay&location=Punjab
	public String getCollege(@RequestParam int id, @RequestParam String name, @RequestParam String location) {
		return "ID=" + id + ", NAME=" + name + ", LOCATION=" + location;
	}
	
	
	//http://localhost:8080/takeData2/1110
	@GetMapping("/takeData2/{id}")   //both should be same  -->   @PathVariable parameter name  =  URI template variable name
	public String takeData2(@PathVariable int id) {  //@PathVariable parameter name
		return "ID="+id;
	}
	
	//http://localhost:8080/takeData3/1110/Abhishek
	@GetMapping("/takeData3/{id}/{name}")
	public String takeData3(@PathVariable int id, @PathVariable String name) {
		return "ID="+id + ", NAME="+name;
	}
	
	
	//http://localhost:8080/student/109/college/108
	@GetMapping("/student/{sId}/college/{cId}")
	public String takeData4(@PathVariable int sId, @PathVariable int cId) {
		return "SID=" + sId + ", CID=" + cId;
	}
	

	//http://localhost:8080/takeData4
	//key : id,   value: 55
	//key : name,   value: xyz
	@GetMapping("/takeData4")
	public String takeData5(@RequestHeader int id, @RequestHeader String name) {
		return "ID="+id + ", NAME="+name;
	}
	
	
	
	
	//http://localhost:8080/student
	/*
	 * 
	 {
    "id":105,
    "name":"Abhishek Upadhyay",
    "phone":9878656789
    }
	 */
	@GetMapping("/student")
	public String getStudent(@RequestBody Student s) {
		System.out.println(s.getId());
		System.out.println(s.getName());
		System.out.println(s.getPhone());
		
		return s.getName() + " is received";
	}
	
	
	
	//http://localhost:8080/student
	/*
	 * 
	 {
    "id":105,
    "name":"Abhishek Upadhyay",
    "phone":9878656789
    }
	 */
	@GetMapping("/studentObject")
	public Student getStudentObject(@RequestBody Student s) {
		System.out.println(s.getId());
		System.out.println(s.getName());
		System.out.println(s.getPhone());
		
		return s;
	}
	
	
	// http://localhost:8080/emp/106?naam=Abhishek Upadhyay&sal=200000
	@GetMapping("/emp/{id}")
	public String getEmp(@PathVariable int id, @RequestParam("naam") String name, @RequestParam double sal) {
		return "ID="+id +", NAME="+name+", SALARY="+sal;
	}
}
