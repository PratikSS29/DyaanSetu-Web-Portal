package com.boot.DyaanSetu.ServiceLayer;

import java.util.List;

import com.boot.DyaanSetu.dto.StudentDto;

public interface StudentService {

	StudentDto completeRegistration(StudentDto studentDto,String password);
		
	
	StudentDto getStudentByEmail(String email);
	
	StudentDto getStudentbyPRN(String prnNo);
	
	List<StudentDto> getAllStudents();
	
	List<StudentDto> getAllStudentsbyYearofPassing(long yearofPassing);
	
	List<StudentDto> getAllStudentsbyBranch(String branch);
	
	void deleteStudent(String rollNo,String email);
	
	
	
}
