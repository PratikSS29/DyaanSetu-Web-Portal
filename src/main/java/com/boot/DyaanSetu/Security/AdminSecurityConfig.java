package com.boot.DyaanSetu.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.boot.DyaanSetu.Filters.JwtAuthenticationFilter;
import com.boot.DyaanSetu.service.impl.CombinedUserDetailsService;

@Configuration
@EnableWebSecurity
public class AdminSecurityConfig {

	@Autowired
	private final CombinedUserDetailsService userDetailsService;
	
	@Autowired
	JwtAuthenticationFilter jwtAuthenticationFilter;
	
	public AdminSecurityConfig(CombinedUserDetailsService userDetailsService) {
		this.userDetailsService=userDetailsService;
	}
	
	public AuthenticationProvider adminAuthenticationProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		
		return provider;
	}
	
	
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder(12);
//	}
	
	
	@Order(3)
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	public SecurityFilterChain adminFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity
					.securityMatcher("/api/admin/**")
					.csrf(csrf -> csrf.disable())
					.authorizeHttpRequests(auth -> auth
							.requestMatchers(
									"/api/admin/login",
									"/api/admin/info",
									"/api/admin/set-password/**"
									)
							.permitAll()
							.requestMatchers("/api/admin/**").hasRole("ADMIN")
							.anyRequest().authenticated()
							)
					.httpBasic(Customizer.withDefaults())
					.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
					.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		return httpSecurity.build();
		
	}
}
