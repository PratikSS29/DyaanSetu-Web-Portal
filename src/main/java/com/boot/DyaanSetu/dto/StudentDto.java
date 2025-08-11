package com.boot.DyaanSetu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

	private String rollNo;
	private String prnNo;
	private String firstName;
	private String lastName;
	private String email;
	private String currentYearofStudy;
	private String branch;
	private String division;
	private long yearofPassing;
	private String contactNo;
	private String gender;
	private String bio;
}
