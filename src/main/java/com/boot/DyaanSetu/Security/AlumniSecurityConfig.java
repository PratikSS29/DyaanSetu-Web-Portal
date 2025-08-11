package com.boot.DyaanSetu.Security;

import com.boot.DyaanSetu.Filters.JwtAuthenticationFilter;
import com.boot.DyaanSetu.service.impl.CombinedUserDetailsService;

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

@Configuration
@EnableWebSecurity
public class AlumniSecurityConfig {

	@Autowired
    private final CombinedUserDetailsService alumniUSerDetailsService;

//	@Autowired
//	JwtAlumniFilter jwtAlumniFilter;
	
	@Autowired
	JwtAuthenticationFilter jwtAuthenticationFilter;
	
    public AlumniSecurityConfig(CombinedUserDetailsService alumniUSerDetailsService) {
        this.alumniUSerDetailsService = alumniUSerDetailsService;
    }

    public AuthenticationProvider alumniauthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(alumniUSerDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return provider;
    }
    
//    public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder(12);
//	}
    
    
    
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

    @Bean
    @Order(2)
    public SecurityFilterChain alumniFilterChain(HttpSecurity httpSecurity) throws Exception {
        

        httpSecurity
            .securityMatcher("/api/alumni/**")
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                		"/api/alumni/info", 
                		"/api/alumni/set-password/**",
                		"/api/alumni/login"
                		).permitAll()
                .requestMatchers("/api/alumni/**").hasRole("ALUMNI")
                .anyRequest().authenticated()
                
            )
            .httpBasic(Customizer.withDefaults())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

}
