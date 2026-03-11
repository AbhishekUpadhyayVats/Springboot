package com.lpu.demo_security2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lpu.demo_security2.entity.Customer;
import com.lpu.demo_security2.repository.CustomerRepo;

@Service
public class CustomerUserDetailService implements UserDetailsService{

	@Autowired
	private CustomerRepo customerRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = customerRepo.findByName(username);
		return new CustomerUserDetails(customer);
	}

}
