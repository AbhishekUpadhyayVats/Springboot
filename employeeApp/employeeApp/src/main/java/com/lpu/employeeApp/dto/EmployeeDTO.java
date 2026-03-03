package com.lpu.employeeApp.dto;

import com.lpu.employeeApp.entity.Company;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class EmployeeDTO {
	
	@NotBlank(message = "Name cannot be empty")
	private String name;
	
	@Positive(message = "Mobile number must be positive")
//	@NotBlank(message = "Phone Number is required")   --> Only applicable to String not any other like long,int,double,Integer,Long,Double
//	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Phone Number")    --> same, only applicable on String
	private long phone;
	
	@Email(message = "Email must be in valid format")   // --> It checks, something@something but it does not check something@something.com.....so even if you remove dot(.) it does not give any error but if you remove @ it does give error
//	@Email from jakarta.validation.constraints.Email does NOT use a strict RFC email regex.   ---> RFC(Request For Comments)
	private String email;
	
	@Positive(message = "Age must be positive")
	@Min(value = 18, message = "Age should be atleast 18")
	@Max(value = 50, message = "Age must not be more than 50")
	private int age;
	
//	private Company company;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public EmployeeDTO() {
		super();
	}
//	public Company getCompany() {
//		return company;
//	}
//	public void setCompany(Company company) {
//		this.company = company;
//	}	
}
