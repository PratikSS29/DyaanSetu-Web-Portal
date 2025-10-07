package com.boot.DyaanSetu.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.DyaanSetu.ServiceLayer.MentorService;
import com.boot.DyaanSetu.entity.MentorLogin;
import com.boot.DyaanSetu.repository.MentorLoginDetailsRepository;

@Service
public class MentorServiceImpl implements MentorService {

	@Autowired
	private MentorLoginDetailsRepository mentorLoginRepo;
	
	@Override
	public String mentorLogin(String email, String password) {
		
		MentorLogin mentor = mentorLoginRepo.findByEmail(email)		
						.orElseThrow(() -> new RuntimeException("Mentor with email "+email+" does not exists !!"));
											
		
		if(!mentor.getPassword().equals(password)) {
			throw new RuntimeException("Password Incorrect !!");
		}
		
		return "Succesfull";
	}

	
	
}
