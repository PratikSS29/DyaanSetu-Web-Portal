package com.boot.DyaanSetu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDashboardDto {

		private String rollNo;
	    private String prnNo;
	    private String firstName;
	    private String lastName;
	    private String email;
	    private String branch;
	    private String division;
	    private long yearOfPassing;
	    
	    private GroupDto group;
	
}
