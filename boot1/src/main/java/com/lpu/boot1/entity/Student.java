package com.lpu.boot1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Student {

	@Id
	private int id;
	private String name;
	private long phone;
	
	@ManyToOne
	@JoinColumn(name="college_id")
	@JsonBackReference
	private College college;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public Student() {
		super();
	}
//	@Override
//	public String toString() {
//		return "Student [id=" + id + ", name=" + name + ", phone=" + phone + "]";
//	}
	public College getCollege() {
		return college;
	}
	public void setCollege(College college) {
		this.college = college;
	}
	
	
}
