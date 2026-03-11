package com.lpu.assignment2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lpu.assignment2.dto.MobileDTO;
import com.lpu.assignment2.entity.Mobile;

public interface MobileRepository extends JpaRepository<Mobile, Integer>{

	List<Mobile> findMobileByBrandName(String brandName);
}
