package com.boot.DyaanSetu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlumniDto {
	private long alumniID;
	private String firstName;
	private String lastName;
	private String email;
	private long yearOfPassing;	
	private String branch;
	private String industry;
	private String currentWorkProfile;
	private String currentCompany;
	private long yearsOfExperience;
	private String currentCountry;
	private String currentState;
	private String currentCity;
	private String linkedInProfile;
	private String isWillingToMentor;
	private String bio;
}
