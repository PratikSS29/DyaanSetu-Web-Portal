package com.boot.DyaanSetu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "Alumni_Information")
public class Alumni {

	@Column(nullable = false)
	private boolean active=false;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private long alumniID;
	
	
	@NotBlank(message = "Please enter First Name ")
	@Column(name = "first_name" , nullable = false)
	private String firstName;
	
    @NotBlank(message = "Please enter Last Name ")
	@Column(name = "last_name" , nullable = false)
	private String lastName;
	
    @NotBlank(message = "Email Id is required ")
	@Column(name = "email" , nullable = false , unique = true)
	private String email;
    
     @NotNull(message = "Year of Passing is required")
	@Column(name = "year_of_Passing" , nullable = false)
	private long yearOfPassing;
	
    @NotBlank(message = "Branch name is required ")
	@Column(name = "branch" , nullable = false)
	private String branch;
	
    @NotBlank(message = "Industry  is required ")
	@Column(name = "industry" , nullable = false)
	private String industry;
    
	@NotBlank(message = "current Work Profile is required ")
	@Column(name = "work_profile")
	private String currentWorkProfile; // DropDown --- > fields  Software & IT,  AI / Research / Academia ,  Hardware / Core Engineering , Networking / Infrastructure ,  Management & Business , Finance & Consulting, Creative & Media ,  Healthcare & Life Sciences ,  Others

	@NotBlank(message = "Current Company name is required ")
	@Column(name = "current_company" , nullable = false)
	private String currentCompany;
	
	@NotNull(message = "Please Enter Years of Experience")
	@Column(name = "yearsOf_Experience")
	private long yearsOfExperience;
	
	@NotBlank(message = "Current Country is required ")
	@Column(name = "current_country" , nullable = false)
	private String currentCountry;
	
	@NotBlank(message = "Job Location is required ")
	@Column(name = "current_state" , nullable = false)
	private String currentState;
	
	@NotBlank(message = "Current City  is required ")
	@Column(name = "current_city" , nullable = false)
	private String currentCity;
	
	@NotBlank(message = "LinkedIn is required ")
	@Column(name = "linkedin_profile" , nullable = false , unique = true)
	private String linkedInProfile;
	
	
	@Column(name = "isWillingTo_Mentor" , nullable = false)
	private String isWillingToMentor;
	
	@NotBlank(message = "Add more about you")
	@Column(name = "bio" , nullable = false)
	private String bio;
	
}
		