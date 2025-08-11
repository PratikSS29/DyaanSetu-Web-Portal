package com.boot.DyaanSetu.mapper;

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
				studentDto.getBio()
				);
	}
}
