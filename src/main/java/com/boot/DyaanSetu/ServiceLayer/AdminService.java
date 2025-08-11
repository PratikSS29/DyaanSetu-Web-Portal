package com.boot.DyaanSetu.ServiceLayer;

import org.springframework.stereotype.Service;

import com.boot.DyaanSetu.dto.AdminDto;

@Service
public interface AdminService {

	AdminDto completeRegistration(AdminDto adminDto, String password);

	
	
}
