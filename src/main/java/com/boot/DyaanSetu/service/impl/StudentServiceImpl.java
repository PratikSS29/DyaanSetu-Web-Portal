package com.boot.DyaanSetu.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.boot.DyaanSetu.ServiceLayer.StudentService;
import com.boot.DyaanSetu.dto.StudentDto;
import com.boot.DyaanSetu.entity.Student;
import com.boot.DyaanSetu.entity.StudentLogin;
import com.boot.DyaanSetu.exception.ResourceNotFoundException;
import com.boot.DyaanSetu.mapper.StudentMapper;
import com.boot.DyaanSetu.repository.StudentLoginDetailsRepository;
import com.boot.DyaanSetu.repository.StudentRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;
	private final StudentLoginDetailsRepository studentLoginDetailsRepository;
	private final BCryptPasswordEncoder encoder =new BCryptPasswordEncoder(12);
	
	@Override
	@Transactional
		public StudentDto completeRegistration(StudentDto studentDto,String password) {
			
			Student student= StudentMapper.mapToStudent(studentDto);
			Student savedStudent= studentRepository.save(student);
			
			
			StudentLogin login=new StudentLogin();
			login.setEmail(studentDto.getEmail());
			login.setPassword(encoder.encode(password));
			studentLoginDetailsRepository.save(login);
			
			
			return StudentMapper.mapToStudentDto(student);
		}

	
	
	@Override
	public Student getStudentByEmail(String email) {
		Student student= studentRepository.findByEmail(email)
											.orElseThrow(()-> new ResourceNotFoundException("Student with email "+email+" does not exists !!"));
		
		return student;
	}

	@Override
	public StudentDto getStudentbyPRN(String prnNo) {
		Student student= studentRepository.findByPrnNo(prnNo)
											.orElseThrow(()-> new ResourceNotFoundException("Student with PRN "+prnNo+" does not exists !!"));
		return StudentMapper.mapToStudentDto(student);
	}
	
	@Override
	public List<StudentDto> getAllStudents() {
		List<Student> students= studentRepository.findAll();
		
		return students.stream().map((student)-> StudentMapper.mapToStudentDto(student)).collect(Collectors.toList());
	}

	@Override
	public List<StudentDto> getAllStudentsbyYearofPassing(long yearOfPassing) {
		List<Student> students= studentRepository.findAllByYearOfPassing(yearOfPassing);
		
		return students.stream().map((student) -> StudentMapper.mapToStudentDto(student)).collect(Collectors.toList());
	}
	
	@Override
	public List<StudentDto> getAllStudentsbyBranch(String branch) {
		List<Student> students= studentRepository.findAllBybranch(branch);
		return students.stream().map((student) -> StudentMapper.mapToStudentDto(student)).collect(Collectors.toList());
	}
	
	@Override
	public void deleteStudent(String rollNo,String email) {
		Student student= studentRepository.findById(rollNo)
						.orElseThrow(() -> new ResourceNotFoundException("Student with given roll no does not exists : "+rollNo));
		
	
		StudentLogin login= studentLoginDetailsRepository.findByEmail(email);
		
		if(login==null) {
			throw new ResourceNotFoundException("Student with given email does not exists");
		}
		
		studentRepository.delete(student);
		studentLoginDetailsRepository.delete(login);
	}
}
