package com.lpu.assignment2.dto;

import java.io.Serializable;

import com.lpu.assignment2.entity.MobileImage;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NotBlank;

public class MobileDTO implements Serializable{
	private static final long serialVersionID = 1L;
	
	@NotBlank(message = "use appropriate id")
	private int id;
	
	@NotBlank(message = "Name can not be empty")
	private String brandName;
	
	@Min(value=9000, message="Price cannot be less than 9000")
	@Negative(message = "Price cannot be Negative")
	private double price;
	
	@NotBlank(message = "ram can not be empty")
	private String ram;
	
	@NotBlank(message = "Storage can not be empty")
	private String storage;
	
	@NotBlank(message = "Colour can not be empty")
	private String colour;
	
	private MobileImage mobileImage;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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

	public static long getSerialversionid() {
		return serialVersionID;
	}

	public MobileDTO() {
		super();
	}
	
	
}
