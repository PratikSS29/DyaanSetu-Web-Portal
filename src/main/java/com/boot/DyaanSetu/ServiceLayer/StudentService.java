package com.boot.DyaanSetu.ServiceLayer;

import java.util.List;

import com.boot.DyaanSetu.dto.StudentDto;
import com.boot.DyaanSetu.entity.Student;

public interface StudentService {

	StudentDto completeRegistration(StudentDto studentDto,String password);
		
	
	Student getStudentByEmail(String email);
	
	StudentDto getStudentbyPRN(String prnNo);
	
	List<StudentDto> getAllStudents();
	
	List<StudentDto> getAllStudentsbyYearofPassing(long yearofPassing);
	
	List<StudentDto> getAllStudentsbyBranch(String branch);
	
	void deleteStudent(String rollNo,String email);
	
	
	
}
