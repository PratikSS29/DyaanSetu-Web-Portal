package com.boot.DyaanSetu.mapper;

import com.boot.DyaanSetu.dto.AlumniDto;
import com.boot.DyaanSetu.entity.Alumni;

public class AlumniMapper {

	public static AlumniDto mapToAlumniDto(Alumni alumni) {
	  return new AlumniDto(
			  alumni.getAlumniID(),
			  alumni.getFirstName(),
			  alumni.getLastName(),
			  alumni.getEmail(),
			  alumni.getYearOfPassing(),
			  alumni.getBranch(),
			  alumni.getIndustry(),
			  alumni.getCurrentWorkProfile(),
			  alumni.getCurrentCompany(),
			  alumni.getYearsOfExperience(),
			  alumni.getCurrentCountry(),
			  alumni.getCurrentState(),
			  alumni.getCurrentCity(),
			  alumni.getLinkedInProfile(),
			  alumni.getIsWillingToMentor(),
			  alumni.getBio()
			  
			  );
	}

	public static Alumni mapToAlumni(AlumniDto alumniDto) {
		return new Alumni(
				false,
				alumniDto.getAlumniID(),
				alumniDto.getFirstName(),
				alumniDto.getLastName(),
				alumniDto.getEmail(),
				alumniDto.getYearOfPassing(),
				alumniDto.getBranch(),
				alumniDto.getIndustry(),
				alumniDto.getCurrentWorkProfile(),
				alumniDto.getCurrentCompany(),
				alumniDto.getYearsOfExperience(),
				alumniDto.getCurrentCountry(),
				alumniDto.getCurrentState(),
				alumniDto.getCurrentCity(),
				alumniDto.getLinkedInProfile(),
				alumniDto.getIsWillingToMentor(),
				alumniDto.getBio()
				);
	}
	
}
