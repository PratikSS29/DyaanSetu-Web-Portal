package com.boot.DyaanSetu.ServiceLayer;

import java.util.List;

import com.boot.DyaanSetu.dto.AlumniDto;


public interface AlumniService {
	
	AlumniDto completeRegistration(AlumniDto alumniDto, String password);
	
	void deleteAlumniByEmail(String email);
	
	List<AlumniDto> getAllAlumniWithYearOfPassing(Long yearOfPassing);
	
	AlumniDto updateAlumni(String email, AlumniDto updatedEmployeeDetails );
	
	AlumniDto getAlumnibyEmail(String email);
	
//	boolean Login(String email,String password);
}
