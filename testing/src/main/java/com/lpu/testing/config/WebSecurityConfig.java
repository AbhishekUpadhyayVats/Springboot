package com.lpu.testing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) {
		http.cors(c -> {});
		http.csrf(c -> c.disable());
		
		http.authorizeHttpRequests(req -> 
			req.requestMatchers("/testing/add").permitAll()
			.requestMatchers("/testing/getTest").hasRole("ADMIN")
			.requestMatchers("/testing/hello").hasAnyRole("STUDENT","ADMIN")
			.anyRequest().authenticated()
		);
		
		http.httpBasic(Customizer.withDefaults());
		return http.build();
	}
}
