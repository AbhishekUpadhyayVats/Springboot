package com.lpu.testing.service;

import java.util.Collection;
import java.util.Collections;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lpu.testing.entity.Testing;
import com.lpu.testing.repository.TestingRepository;

public class TestingUserDetail implements UserDetails{
	
	private Testing test;
	
	public TestingUserDetail(Testing test) {
		this.test = test;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority(test.getRole()));
	}

	@Override
	public @Nullable String getPassword() {
		return test.getPassword();
	}

	@Override
	public String getUsername() {
		return test.getEmail();
	}

}
