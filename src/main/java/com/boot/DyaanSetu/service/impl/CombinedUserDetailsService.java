package com.boot.DyaanSetu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.boot.DyaanSetu.entity.AdminLogin;
import com.boot.DyaanSetu.entity.AdminPrincipal;
import com.boot.DyaanSetu.entity.AlumniLogin;
import com.boot.DyaanSetu.entity.AlumniPrincipal;
import com.boot.DyaanSetu.entity.StudentLogin;
import com.boot.DyaanSetu.entity.StudentPrincipal;
import com.boot.DyaanSetu.exception.ResourceNotFoundException;
import com.boot.DyaanSetu.repository.AdminLoginDetailRepository;
import com.boot.DyaanSetu.repository.AlumniLoginDetailsRepository;
import com.boot.DyaanSetu.repository.StudentLoginDetailsRepository;

@Service
public class CombinedUserDetailsService implements UserDetailsService {

    @Autowired
    private AlumniLoginDetailsRepository alumniRepo;

    @Autowired
    private StudentLoginDetailsRepository studentRepo;
    
    @Autowired
    AdminLoginDetailRepository adminRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // Try Alumni
        AlumniLogin alumni = alumniRepo.findByEmail(email);
        if (alumni != null) {
            System.out.println("Alumni user found: " + email);
            return new AlumniPrincipal(alumni);
        } else {
			System.out.println("Alumni Not Found !!");
		}

        // Try Student
        StudentLogin student = studentRepo.findByEmail(email);
        if (student != null) {
            System.out.println("Student user found: " + email);
            return new StudentPrincipal(student);
        } else {
			System.out.println("Studenty Not Found !!");
		}
        
        //Try Admin
        AdminLogin admin=adminRepo.findByEmail(email);
        if(admin!=null) {
        	System.out.println("Admin user found "+email);
        	return new AdminPrincipal(admin);
        }else {
			System.out.println("Admin Not Found !!");
		}

        // If neither found
        System.out.println("User not found: " + email);
        throw new ResourceNotFoundException("No user (alumni or student) found with this email");
    }
}
