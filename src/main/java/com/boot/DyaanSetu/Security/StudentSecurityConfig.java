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
public class StudentSecurityConfig {

	@Autowired
    private final CombinedUserDetailsService studentuserDetailsService;


	
	@Autowired
	JwtAuthenticationFilter jwtAuthenticationFilter;
	
	
	
    public StudentSecurityConfig(CombinedUserDetailsService studentuserDetailsService) {
        this.studentuserDetailsService = studentuserDetailsService;
    }
    
    

    public AuthenticationProvider studentauthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(studentuserDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return provider;
    }


    @Bean
    @Order(1)
    public SecurityFilterChain studentfilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
            .securityMatcher("/api/students/**")
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/api/students/info",
                    "/api/students/set-password/**",
                    "/api/students/login"
                ).permitAll()
                .requestMatchers("/api/students/**").hasRole("STUDENT")
                .anyRequest().authenticated()
            )
            .oauth2Login(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // ✅ REQUIRED

        return httpSecurity.build();
    }

}
