package com.lpu.StudentManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lpu.StudentManagementSystem.entity.Sms;
import com.lpu.StudentManagementSystem.exception.SmsNotFoundException;
import com.lpu.StudentManagementSystem.repository.SmsRepository;

@Service
public class SmsUserDetailService implements UserDetailsService{
	
	@Autowired
	private SmsRepository smsRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Sms sms = smsRepo.findByEmail(username);
		System.err.println("username:" + username);
		System.err.println(username);
		System.err.println(sms);
		if(sms == null) {
			throw new SmsNotFoundException("Student with email:" + username + " does not exists");
		}
		return new SmsUserDetails(sms);
	}

} 
