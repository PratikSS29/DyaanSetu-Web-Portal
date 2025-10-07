package com.boot.DyaanSetu.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MentorDto {

	private Long id;
	private String firstName;
	private String lastName;
	private String emailId;
	private String department;
	private List<GroupDto> groups;
	
}
