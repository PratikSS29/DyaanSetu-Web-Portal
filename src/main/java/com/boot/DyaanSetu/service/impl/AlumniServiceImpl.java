package com.boot.DyaanSetu.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.boot.DyaanSetu.ServiceLayer.AlumniService;
import com.boot.DyaanSetu.dto.AlumniDto;
import com.boot.DyaanSetu.entity.Alumni;
import com.boot.DyaanSetu.entity.AlumniLogin;
import com.boot.DyaanSetu.exception.ResourceNotFoundException;
import com.boot.DyaanSetu.mapper.AlumniMapper;
import com.boot.DyaanSetu.repository.AlumniLoginDetailsRepository;
import com.boot.DyaanSetu.repository.AlumniRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AlumniServiceImpl implements AlumniService {
	
	private final AlumniRepository alumniRepository;
	private final AlumniLoginDetailsRepository alumniloginDetailsRepository;
	private final BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
	
	@Override
	@Transactional
	public AlumniDto completeRegistration(AlumniDto alumniDto,String password) {
		Alumni alumni= AlumniMapper.mapToAlumni(alumniDto);
		Alumni savedAlumni= alumniRepository.save(alumni);
		
		
		AlumniLogin login=new AlumniLogin();
		login.setEmail(alumniDto.getEmail());
		login.setPassword(encoder.encode(password));
		alumniloginDetailsRepository.save(login);
		
		
		return AlumniMapper.mapToAlumniDto(alumni);
	}

		
	@Override
	public void deleteAlumniByEmail(String email) {
		
		Alumni alumni= alumniRepository.findByEmail(email)
										.orElseThrow(() -> new ResourceNotFoundException("Alumni not found with email : "+email));
		
		alumniRepository.delete(alumni);
		
	}
	
	@Override
	public AlumniDto getAlumnibyEmail(String email) {
		Alumni alumni= alumniRepository.findByEmail(email)
										.orElseThrow(() -> new ResourceNotFoundException("Alumni with email "+email+" does not exists !!"));
		return AlumniMapper.mapToAlumniDto(alumni);
	}

	@Override
	public List<AlumniDto> getAllAlumniWithYearOfPassing(Long yearOfPassing) {
	 	
		List<Alumni> alumnis= alumniRepository.findByYearOfPassing(yearOfPassing);
	 	return alumnis.stream().map((alumni) -> AlumniMapper.mapToAlumniDto(alumni)).collect(Collectors.toList());
	}

	@Override
	public AlumniDto updateAlumni(String email, AlumniDto updatedAlumniDetails) {
	    Alumni alumni = alumniRepository.findByEmail(email)
	            .orElseThrow(() -> new ResourceNotFoundException("Alumni with email " + email + " not found !!"));

	    // Do not update email
	    alumni.setIndustry(updatedAlumniDetails.getIndustry());
	    alumni.setCurrentWorkProfile(updatedAlumniDetails.getCurrentWorkProfile());
	    alumni.setCurrentCompany(updatedAlumniDetails.getCurrentCompany());
	    alumni.setYearsOfExperience(updatedAlumniDetails.getYearsOfExperience());
	    alumni.setCurrentCountry(updatedAlumniDetails.getCurrentCountry());
	    alumni.setCurrentState(updatedAlumniDetails.getCurrentState());
	    alumni.setCurrentCity(updatedAlumniDetails.getCurrentCity());
	    alumni.setLinkedInProfile(updatedAlumniDetails.getLinkedInProfile());
	    alumni.setIsWillingToMentor(updatedAlumniDetails.getIsWillingToMentor());
	    alumni.setBio(updatedAlumniDetails.getBio());

	    Alumni updatedAlumniObj = alumniRepository.save(alumni);
	    return AlumniMapper.mapToAlumniDto(updatedAlumniObj);
	}


//	@Override
//	public boolean Login(String email, String password) {
//		AlumniLogin loginOpt= alumniloginDetailsRepository.findByEmail(email);
//		if(loginOpt.isPresent()) {
//			AlumniLogin login=loginOpt.get();
//			return encoder.matches(password, login.getPasswordHash());
//		}
//		return false;
//	}
	
}
