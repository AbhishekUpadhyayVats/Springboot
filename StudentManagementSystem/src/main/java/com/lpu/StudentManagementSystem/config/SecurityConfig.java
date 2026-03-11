package com.lpu.StudentManagementSystem.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.lpu.StudentManagementSystem.service.SmsUserDetailService;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf(csrf -> csrf.disable());
		http.cors(c -> {}); 
		http.authorizeHttpRequests(req ->
		req.requestMatchers("/student/add","/error").permitAll()
		.requestMatchers("/student/getStudent/*","/student/getAllStudents/**").hasRole("ADMIN")
		.requestMatchers("/student/update/*").hasAnyRole("STUDENT","ADMIN")
		.anyRequest().authenticated()
		);
		
		http.formLogin(Customizer.withDefaults());     //Enables form-based authentication with a login page where users enter username and password.
		http.httpBasic(Customizer.withDefaults());     //Enables HTTP Basic authentication where credentials are sent in the request header (often via browser popup or Postman).
		return http.build();
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		
		config.setAllowedOrigins(List.of("http://localhost:5173"));  //We can add multiple origin like more different frontend origins
		config.setAllowedMethods(List.of("GET","POST","PUT","DELETE","PATCH"));
		config.setAllowedHeaders(List.of("*"));
		config.setAllowCredentials(true);
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		
		source.registerCorsConfiguration("/**", config);  // /** -> any number of path segments after this point
		
		return source;
	}
}
