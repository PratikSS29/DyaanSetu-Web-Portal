 package com.boot.DyaanSetu.Controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.DyaanSetu.Config.StudentTempRegistrationCache;
import com.boot.DyaanSetu.ServiceLayer.StudentService;
import com.boot.DyaanSetu.dto.JwtResponse;
import com.boot.DyaanSetu.dto.SetPasswordDto;
import com.boot.DyaanSetu.dto.StudentDto;
import com.boot.DyaanSetu.entity.StudentLogin;
import com.boot.DyaanSetu.exception.ResourceNotFoundException;
import com.boot.DyaanSetu.repository.StudentLoginDetailsRepository;
import com.boot.DyaanSetu.service.impl.JwtService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = "http://localhost:5173",
				allowCredentials = "true")
@AllArgsConstructor
@RestController
@RequestMapping("/api/students")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentTempRegistrationCache tempcache;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	StudentLoginDetailsRepository repository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	//REST API ADD Student
	//STEP 1 :Cache Registration Data
	@PostMapping("/info")
	public ResponseEntity<String> beginRegistration(@RequestBody StudentDto studentDto) {
		String tempId=tempcache.storeTempRegistration(studentDto);
		return ResponseEntity.ok(tempId);
	}
	
	//STEP 2: Complete Registration with password
	@PostMapping("/set-password/{tempId}")
	public ResponseEntity<StudentDto> completeRegistration(
			@PathVariable String tempId,
			@RequestBody @Valid SetPasswordDto setPasswordDto) {
		
		StudentDto studentDto=tempcache.getTempregistration(tempId);
		StudentDto savedStudent=studentService.completeRegistration(studentDto,setPasswordDto.getPassword());
		tempcache.clearTempRegistration(tempId);
		
		return new ResponseEntity<>(savedStudent,HttpStatus.CREATED);
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?> studentLogin(@RequestBody StudentLogin request) {
	    StudentLogin student = repository.findByEmail(request.getEmail());
	    if (student == null || !passwordEncoder.matches(request.getPassword(), student.getPassword())) {
	        throw new ResourceNotFoundException("Invalid credentials");
	    }
	    String token = jwtService.generateToken(student.getEmail(), "STUDENT");
	    return ResponseEntity.ok(new JwtResponse(token));
	}
	
	
}
