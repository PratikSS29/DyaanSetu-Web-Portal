package com.boot.DyaanSetu.mapper;

import com.boot.DyaanSetu.dto.GroupDto;
import com.boot.DyaanSetu.dto.MentorDto;
import com.boot.DyaanSetu.dto.MentorSummaryDto;
import com.boot.DyaanSetu.dto.StudentDashboardDto;
import com.boot.DyaanSetu.dto.StudentDto;
import com.boot.DyaanSetu.entity.Student;

public class StudentMapper {

	public static StudentDto mapToStudentDto(Student student) {
		return new StudentDto(
				student.getRollNo(),
				student.getPrnNo(),
				student.getFirstName(),
				student.getLastName(),
				student.getEmail(),
				student.getCurrentYearofStudy(),
				student.getBranch(),
				student.getDivision(),
				student.getYearOfPassing(),
				student.getContactNo(),
				student.getGender(),
				student.getBio()
				); 
	}
	
	public static Student mapToStudent(StudentDto studentDto) {
		return new Student(
				studentDto.getRollNo(),
				studentDto.getPrnNo(),
				studentDto.getFirstName(),
				studentDto.getLastName(),
				studentDto.getEmail(),
				studentDto.getCurrentYearofStudy(),
				studentDto.getBranch(),
				studentDto.getDivision(),
				studentDto.getYearofPassing(),
				studentDto.getContactNo(),
				studentDto.getGender(),
				studentDto.getBio(),
				null
				
				);
	}
	
	public static StudentDashboardDto toDashboardDto(Student student) {
        StudentDashboardDto dto = new StudentDashboardDto();
        dto.setRollNo(student.getRollNo());
        dto.setPrnNo(student.getPrnNo());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());
        dto.setBranch(student.getBranch());
        dto.setDivision(student.getDivision());
        dto.setYearOfPassing(student.getYearOfPassing());

        if (student.getGroup() != null) {
            GroupDto groupDto = new GroupDto();
            groupDto.setId(student.getGroup().getId());
            groupDto.setGroupName(student.getGroup().getGroupName());

            if (student.getGroup().getMentor() != null) {
                MentorSummaryDto mentorDto = new MentorSummaryDto();
                mentorDto.setId(student.getGroup().getMentor().getId());
                mentorDto.setFirstName(student.getGroup().getMentor().getFirstName());
                mentorDto.setLastName(student.getGroup().getMentor().getLastName());
                mentorDto.setEmailId(student.getGroup().getMentor().getEmailId());
                groupDto.setMentor(mentorDto);
            }

            dto.setGroup(groupDto);
        }

        return dto;
	}
}
