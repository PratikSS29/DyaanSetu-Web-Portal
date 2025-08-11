package com.boot.DyaanSetu.entity;

import org.apache.el.parser.AstFalse;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Student_Information")
public class Student {
	

	
	@Id
	@NotNull(message = "please enter roll no")
	private String rollNo;
	
	@NotBlank(message = "please enter PRN Number")
	@Column(name = "prn_number" , nullable = false , unique = true)
	private String prnNo;
	
	@NotBlank(message = "please enter first Name")
	@Column(name = "first_Name" , nullable = false)
	private String firstName;
	
	@NotBlank(message = "please enter last Name")
	@Column(name = "last_Name" , nullable = false)
	private String lastName;
	
	@NotBlank(message = "please enter Email ID")
	@Column(name = "email_ID" ,nullable = false ,unique = true)
	private String email;
	
	@NotNull(message = "Please enter your current year of study")
	@Column(name = "current_yearOf_study" ,nullable = false)
	private String currentYearofStudy;
	
	@NotBlank(message = "please enter the branch you are studying in")
	@Column(name = "branch" ,nullable = false)
	private String branch;
	
	@NotNull(message = "please enter your division")
	@Column(name = "division",nullable = false)
	private String division;
	
	//@NotNull(message = "please enter year of passing")
	@Column(name = "yearOf_passing" , nullable = false)
	private long yearOfPassing;
	
	@NotBlank(message = "please enter contact Number")
	@Column(name = "contact_No" ,nullable = false ,unique = true)
	private String contactNo;	
	
	@NotBlank(message = "please enter your Gender")
	@Column(name = "gender" ,nullable = false)
	private String gender;	
	
	@Column(name = "bio" , nullable = false)
	private String bio;
	
	
}
