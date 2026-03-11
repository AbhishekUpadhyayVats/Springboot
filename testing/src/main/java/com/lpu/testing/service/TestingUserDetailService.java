package com.lpu.testing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lpu.testing.entity.Testing;
import com.lpu.testing.repository.TestingRepository;

@Service
public class TestingUserDetailService implements UserDetailsService{

	@Autowired
	private TestingRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Testing test = repo.findByEmail(username);
		return new TestingUserDetail(test);
	}

}
