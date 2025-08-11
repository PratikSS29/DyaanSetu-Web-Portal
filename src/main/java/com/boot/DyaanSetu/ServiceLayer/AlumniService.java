package com.boot.DyaanSetu.ServiceLayer;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boot.DyaanSetu.dto.AlumniDto;
import com.boot.DyaanSetu.dto.SetPasswordDto;


public interface AlumniService {
	
	AlumniDto completeRegistration(AlumniDto alumniDto, String password);
	
	void deleteAlumniByEmail(String email);
	
	List<AlumniDto> getAllAlumniWithYearOfPassing(Long yearOfPassing);
	
	AlumniDto updateAlumni(String email, AlumniDto updatedEmployeeDetails );
	
	AlumniDto getAlumnibyEmail(String email);
	
//	boolean Login(String email,String password);
}
