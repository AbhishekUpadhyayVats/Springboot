package com.lpu.assignment2.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.lpu.assignment2.dto.MobileDTO;
import com.lpu.assignment2.entity.Mobile;
import com.lpu.assignment2.exception.MobileNotFoundException;
import com.lpu.assignment2.repository.MobileRepository;

@Service
public class MobileServiceImpl implements MobileService{
	
	@Autowired
	private MobileRepository repository;
	
	Mobile DTOtoEntity(MobileDTO mobileDTO) {
		Mobile mobile = new Mobile();
		mobile.setBrandName(mobileDTO.getBrandName());
		mobile.setPrice(mobileDTO.getPrice());
		mobile.setColour(mobileDTO.getColour());
		mobile.setRam(mobileDTO.getRam());
		mobile.setStorage(mobileDTO.getStorage());
		mobile.setMobileImage(mobileDTO.getMobileImage());
		
		return mobile;
	}
	
	MobileDTO EntityToDTO(Mobile mobile) {
		MobileDTO mobileDTO = new MobileDTO();
		mobileDTO.setId(mobile.getId());
		mobileDTO.setBrandName(mobile.getBrandName());
		mobileDTO.setColour(mobile.getColour());
		mobileDTO.setPrice(mobile.getPrice());
		mobileDTO.setRam(mobile.getRam());
		mobileDTO.setStorage(mobile.getStorage());
		mobileDTO.setMobileImage(mobile.getMobileImage());
		
		return mobileDTO;
	}

	@Override
	@CachePut(value = "mobiles", key = "#result.id")
	public MobileDTO addMobile(MobileDTO mobileDTO) {
		Mobile mobile = DTOtoEntity(mobileDTO);
		repository.save(mobile);
		return EntityToDTO(mobile);
	}

	@Override
	@Cacheable(value = "mobiles")
	public List<MobileDTO> getAllMobile() {
		List<Mobile> list = repository.findAll();
		List<MobileDTO> list2 = new ArrayList<>();
		for(Mobile m : list) {
			list2.add(EntityToDTO(m));
		}
		return list2;
	}

	@Override
	@Cacheable(value = "mobiles", key = "#id")
	public MobileDTO getMobileById(int id) {
		Mobile mobile = repository.findById(id).orElseThrow(() -> new MobileNotFoundException("Mobile with ID:" + id + " does not exist"));
		return EntityToDTO(mobile);
	}

	@Override
	public MobileDTO updateMobile(int id, MobileDTO mobileDTO) {
		Mobile mobile = repository.findById(id).orElseThrow(() -> new MobileNotFoundException("Mobile with ID:" + id + " does not exist"));
		mobile.setBrandName(mobileDTO.getBrandName());
		mobile.setColour(mobileDTO.getColour());
		mobile.setPrice(mobile.getPrice());
		mobile.setRam(mobileDTO.getRam());
		mobile.setStorage(mobileDTO.getStorage());
		
		return EntityToDTO(mobile);
	}

	@Override
	@CacheEvict(value = "mobiles", key = "#id")
	public void deleteMobileById(int id) {
		repository.deleteById(id);
	}

	@Override
	@Cacheable(value = "mobiles")
	public List<MobileDTO> getMobileByName(String brandName) {

	    List<Mobile> mobiles = repository.findMobileByBrandName(brandName);
	    List<MobileDTO> mobileDTO = new ArrayList<>();

	    for(Mobile m : mobiles) {
	        mobileDTO.add(EntityToDTO(m));
	    }

	    return mobileDTO;
	}
	
	@Override
	@Cacheable(value = "mobiles")
	public List<MobileDTO> getAllMobiles(int pageNumber, int size){
	    Pageable pageable = PageRequest.of(pageNumber, size);

	    List<Mobile> mobiles = repository.findAll(pageable).getContent();

	    List<MobileDTO> mobileDTO = new ArrayList<>();

	    for(Mobile m : mobiles) {
	        mobileDTO.add(EntityToDTO(m));
	    }
	    return mobileDTO;
	}

	@Override
	@Cacheable(value = "mobiles")
	public List<MobileDTO> getAllMobilesAscPagination(int pageNumber, int size, String field){
	    Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(field).ascending());

	    List<Mobile> mobiles = repository.findAll(pageable).getContent();

	    List<MobileDTO> mobileDTO = new ArrayList<>();

	    for(Mobile m : mobiles) {
	        mobileDTO.add(EntityToDTO(m));
	    }
	    return mobileDTO;
	}
	
	@Override
	@Cacheable(value = "mobiles")
	public List<MobileDTO> getAllMobilesDescPagination(int pageNumber, int size, String field){
	    Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(field).ascending());

	    List<Mobile> mobiles = repository.findAll(pageable).getContent();

	    List<MobileDTO> mobileDTO = new ArrayList<>();

	    for(Mobile m : mobiles) {
	        mobileDTO.add(EntityToDTO(m));
	    }
	    return mobileDTO;
	}
	
}
