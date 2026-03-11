package com.lpu.StudentManagementSystem.service;

import java.util.Collection;
import java.util.Collections;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lpu.StudentManagementSystem.entity.Sms;

public class SmsUserDetails implements UserDetails{

	private Sms sms;
	
	public SmsUserDetails(Sms sms) {
		super();
		this.sms = sms;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    System.out.println("ROLE FROM DB: " + sms.getRole());
		return Collections.singleton(new SimpleGrantedAuthority(sms.getRole()));
	}

	@Override
	public @Nullable String getPassword() {
		return sms.getPassword();
	}

	@Override
	public String getUsername() {
		return sms.getEmail();
	}
}

