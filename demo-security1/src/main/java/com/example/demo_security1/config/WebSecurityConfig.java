package com.example.demo_security1.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) {
//		http.authorizeHttpRequests(req -> 
//		req.requestMatchers("/reg", "/error").permitAll()
//		.anyRequest().authenticated());
////		req.anyRequest().authenticated());  //Check for login
////		req.anyRequest().permitAll()   //It permits all the incoming requests
////		req.anyRequest().denyAll();	   //It Denies all the incoming requests
//		http.formLogin(Customizer.withDefaults());    //Chrome login page
//		http.httpBasic(Customizer.withDefaults());    //Postman basic auth
//		return http.build();
//	}
	
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) {
		http.authorizeHttpRequests(req -> req.requestMatchers("/hiii","/home").authenticated().anyRequest().permitAll());
		
		http.formLogin(Customizer.withDefaults());
		http.httpBasic(Customizer.withDefaults());
		
		return http.build();
	}
	
	
	
	@Bean
	public UserDetailsService detailsService() {
		UserDetails user1 = User.withUsername("Abhishek")
								.password("{noop}123")    //noop -> No Operation password encoder.
								.roles("read")
								.build();
		UserDetails user2 = User.withUsername("Admin")
								.password("{noop}321")
								.roles("admin")
								.build();
		
		return new InMemoryUserDetailsManager(user1,user2);
	}
}
