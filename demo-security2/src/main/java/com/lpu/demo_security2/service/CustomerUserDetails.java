package com.lpu.demo_security2.service;

import java.util.Collection;
import java.util.Collections;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.lpu.demo_security2.entity.Customer;

public class CustomerUserDetails implements UserDetails{
	
	private Customer customer;   //Customer details are fetched from DataBase

	public CustomerUserDetails(Customer customer) {
		super();
		this.customer = customer;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority(customer.getRole()));
	}

	@Override
	public @Nullable String getPassword() {
		return customer.getPassword();
	}

	@Override
	public String getUsername() {
		return customer.getName();
	}

}
