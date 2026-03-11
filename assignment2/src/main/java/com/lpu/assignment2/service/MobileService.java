package com.lpu.assignment2.service;

import java.util.List;

import com.lpu.assignment2.dto.MobileDTO;

public interface MobileService {
	
	MobileDTO addMobile(MobileDTO mobileDTO);
	
	List<MobileDTO> getAllMobile();
	
	MobileDTO getMobileById(int id);
	
	MobileDTO updateMobile(int id, MobileDTO mobileDTO);
	
	void deleteMobileById(int id);
	
	List<MobileDTO> getMobileByName(String name);
	
	List<MobileDTO> getAllMobiles(int pageNumber, int size);

	List<MobileDTO> getAllMobilesAscPagination(int pageNumber, int size, String field);

	List<MobileDTO> getAllMobilesDescPagination(int pageNumber, int size, String field);
}
