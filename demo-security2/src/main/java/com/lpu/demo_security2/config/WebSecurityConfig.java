package com.lpu.demo_security2.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableMethodSecurity    //This is for @PostAuthorize and @PreAuthorize
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) {
//		http.csrf(csrf -> csrf.disable());  // on POST,PUT,PATCH AND DELETE Csrf token is required and for GET does not required Csrf token
		//GET -> It is considered a safe method because it should only read data, not modify it.
		
		
		
		
		// Types of Session Policy
		// IF_REQUIRED -> Spring creates a session only when needed.
		//NEVER -> create only one session
		//STATELESS -> Spring does not create or use session at all.
//		http.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.cors(c -> {});   //Enables CORS in security filter chain
		http.authorizeHttpRequests(req -> 
			req.requestMatchers("/register","/public","/error","/register2").permitAll()
			.requestMatchers("/delete","/findAll").hasRole("ADMIN") //-> hasRole() automatically adds ROLE_ as prefix thats why we need to pass ROLE_ADMIN OR ROLE_USER in json
//			.requestMatchers("/delete").hasAuthority("ADMIN")  //-> in JSON format we do not have to write ROLE_ADMIN
//			.requestMatchers("/delete").hasAnyAuthority("ADMIN","USER")  //-> in JSON format we do not have to write ROLE_ADMIN....just write ADMIN OR USER normally
			.requestMatchers("/update").hasAnyRole("ADMIN","USER")  //-> hasAnyRole() automatically adds ROLE_ as prefix thats why we need to pass ROLE_ADMIN OR ROLE_USER in json
//			.requestMatchers("/deleteById/**").hasRole("ADMIN")  // or use @PreAuthorize in service....and /** -> means any number of parameter after /deleteById/......and if you use only /* -> it means only one paramater after /deleteById/
			.anyRequest().authenticated()
			);
		
		http.formLogin(Customizer.withDefaults());
		http.httpBasic(Customizer.withDefaults());
		
		return http.build();
	}
	
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();  ///by default Bcrypt Cost Factor is 10 and maximum is 31 and for high security systems 12-14 and lowest is 4
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



// CSRF -> CROSS SITE REQUEST FORGERY

//Simple Meaning :-
//CSRF (Cross-Site Request Forgery) means:
//When a malicious website tricks your browser into sending a request to another website where you are already logged in.

// And it is only possible till our session is valid or not expired or in duration


//✔ Malicious site forcing the user's browser to send a request to another site where the user is authenticated.
//That whole attack scenario is called CSRF.
