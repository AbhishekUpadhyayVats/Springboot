package com.lpu.assignment2.entity;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Mobile{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String brandName;
	
	private double price;
	
	private String ram;
	
	private String storage;
	
	private String colour;
	
	@OneToOne(cascade = CascadeType.ALL)
	private MobileImage mobileImage;

	public int getId() {
		return id;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}
	
	public MobileImage getMobileImage() {
		return mobileImage;
	}

	public void setMobileImage(MobileImage mobileImage) {
		this.mobileImage = mobileImage;
	}

	public Mobile(String brandName, double price, String ram, String storage, String colour, MobileImage mobileImage) {
		super();
		this.brandName = brandName;
		this.price = price;
		this.ram = ram;
		this.storage = storage;
		this.colour = colour;
		this.mobileImage = mobileImage;
	}

	public Mobile() {
		super();
	}
}
