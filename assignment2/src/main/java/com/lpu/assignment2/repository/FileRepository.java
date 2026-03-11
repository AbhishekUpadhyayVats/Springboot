package com.lpu.assignment2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lpu.assignment2.entity.FileData;

public interface FileRepository extends JpaRepository<FileData, Long>{

}
