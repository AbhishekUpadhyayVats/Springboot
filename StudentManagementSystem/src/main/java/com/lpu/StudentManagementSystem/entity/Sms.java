package com.lpu.StudentManagementSystem.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
public class Sms implements Serializable{
	
	private final static long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	
//	@JsonIgnore  //If you use @JsonIgnore here so it will ignore during serialization and deserialization in both cases
	@NotBlank(message = "Must Enter Password")
	private String password;
	
	@NotBlank(message = "Message should be there")
	private String role;
	
	@Email(message = "Email should not be invalid")
	@Column(unique = true)
	private String email;
	
	@Positive(message = "Marks should not be less than 0")
	@Min(value = 0, message = "Marks should at least be 0")
	private double marks;
	
	@Basic(fetch = FetchType.LAZY)
	@JsonIgnore    //but here it ignore in case of serialization(java to JSON) but when we doing deserialization we did not sending it with object we sending it from MultipartFile and setting it manually so internally it ignoring but we are not sending so it works
	@Lob
//	@NotBlank     //but if you add this so it says it cannot be blank but @JsonIgnore is ignoring during deserialization(json to java) so we get -> {"error:": "HV000030: No validator could be found for constraint 'jakarta.validation.constraints.NotBlank' validating type 'byte[]'. Check configuration for 'profileImages'"}
	private byte[] profileImages;
	
	@Basic(fetch = FetchType.LAZY)
	@JsonIgnore
	@Lob
	private byte[] assignmentFile;
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getMarks() {
		return marks;
	}
	public void setMarks(double marks) {
		this.marks = marks;
	}
	public byte[] getProfileImages() {
		return profileImages;
	}
	public void setProfileImages(byte[] profileImages) {
		this.profileImages = profileImages;
	}
	public byte[] getAssignmentFile() {
		return assignmentFile;
	}
	public void setAssignmentFile(byte[] assignmentFile) {
		this.assignmentFile = assignmentFile;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Sms() {
		super();
	}
}
