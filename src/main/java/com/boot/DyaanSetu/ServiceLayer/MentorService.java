package com.boot.DyaanSetu.ServiceLayer;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface MentorService {

	public String mentorLogin(String email , String password);
	
}
