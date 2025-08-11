package com.boot.DyaanSetu.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.boot.DyaanSetu.ServiceLayer.AdminService;
import com.boot.DyaanSetu.dto.AdminDto;
import com.boot.DyaanSetu.dto.StudentDto;
import com.boot.DyaanSetu.entity.Admin;
import com.boot.DyaanSetu.entity.AdminLogin;
import com.boot.DyaanSetu.entity.Student;
import com.boot.DyaanSetu.exception.ResourceNotFoundException;
import com.boot.DyaanSetu.mapper.AdminMapper;
import com.boot.DyaanSetu.mapper.StudentMapper;
import com.boot.DyaanSetu.repository.AdminLoginDetailRepository;
import com.boot.DyaanSetu.repository.AdminRepository;
import com.boot.DyaanSetu.repository.StudentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

	private  AdminRepository adminRepository;
	private  AdminLoginDetailRepository adminLoginDetailRepository;
	private BCryptPasswordEncoder encoder;
	private StudentRepository studentRepository;
	
	@Override
	public AdminDto completeRegistration(AdminDto adminDto, String password) {
		
		Admin admin=AdminMapper.toEntity(adminDto);
		Admin savedAdmin=adminRepository.save(admin);
		
		AdminLogin login=new AdminLogin();
		login.setEmail(adminDto.getEmail());
		login.setPassword(encoder.encode(password));
		adminLoginDetailRepository.save(login);
		
		return AdminMapper.toDto(admin);
	}

	
}
