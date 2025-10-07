package com.boot.DyaanSetu.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.DyaanSetu.Config.AdminTempRegistrationCache;
import com.boot.DyaanSetu.ServiceLayer.AdminService;
import com.boot.DyaanSetu.ServiceLayer.AlumniService;
import com.boot.DyaanSetu.ServiceLayer.GroupService;
import com.boot.DyaanSetu.ServiceLayer.StudentService;
import com.boot.DyaanSetu.dto.AdminDto;
import com.boot.DyaanSetu.dto.AlumniDto;
import com.boot.DyaanSetu.dto.DeleteStudentRequest;
import com.boot.DyaanSetu.dto.GroupDto;
import com.boot.DyaanSetu.dto.JwtResponse;
import com.boot.DyaanSetu.dto.SetPasswordDto;
import com.boot.DyaanSetu.dto.StudentDto;
import com.boot.DyaanSetu.entity.AdminLogin;
import com.boot.DyaanSetu.entity.Group;import com.boot.DyaanSetu.entity.Student;
import com.boot.DyaanSetu.exception.ResourceNotFoundException;
import com.boot.DyaanSetu.mapper.StudentMapper;
import com.boot.DyaanSetu.repository.AdminLoginDetailRepository;
import com.boot.DyaanSetu.service.impl.JwtService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:5173",
			  allowCredentials = "true")
public class AdminController {

	@Autowired
	AdminLoginDetailRepository repo;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	AdminTempRegistrationCache tempcache;
	
	@Autowired
	private AdminService service;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private AlumniService alumniService;
	
	@Autowired
	private GroupService groupService;
	
	@PostMapping("/info")
	public ResponseEntity<String> beginRegistration(@RequestBody AdminDto adminDto) {
		
		String tempId=tempcache.storeTempRegistration(adminDto);
		return ResponseEntity.ok(tempId);
		
	}
	
	@PostMapping("/set-password/{tempId}")
	public ResponseEntity<AdminDto> completeRegistration(
			@PathVariable String tempId,
			@RequestBody @Valid SetPasswordDto setPasswordDto) {
		
		AdminDto adminDto=tempcache.getTempregistration(tempId);
		AdminDto savedAdmin=service.completeRegistration(adminDto,setPasswordDto.getPassword());
		tempcache.clearTempRegistration(tempId);
		
		return new ResponseEntity<>(savedAdmin,HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> adminLogin(@RequestBody AdminLogin request){
		AdminLogin admin=repo.findByEmail(request.getEmail());
		if(admin==null || !passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
			throw new ResourceNotFoundException("Invalid Credentials");
		}
		
		String token=jwtService.generateToken(admin.getEmail(), "ADMIN");
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
			// ** STUDENT SERVICES **//
	
	@GetMapping("/email/{email}")
	public ResponseEntity<StudentDto> getStudentByEmail(@PathVariable String email) {
		
		Student student=studentService.getStudentByEmail(email);
		StudentDto stu=StudentMapper.mapToStudentDto(student);
		return ResponseEntity.ok(stu);
		
	}
	
	@GetMapping("/getStudent/{prnNo}")
	public ResponseEntity<StudentDto> getStudentByPrn(@PathVariable String prnNo) {
		StudentDto student= studentService.getStudentbyPRN(prnNo);
		return ResponseEntity.ok(student);
	}
	
	@GetMapping("/getAllStudents")
	ResponseEntity<List<StudentDto>> getAllStudents(){
		List<StudentDto> students= studentService.getAllStudents();
		System.out.println("Fetched !!");
		return ResponseEntity.ok(students);
	}
	
	@GetMapping("/getAllStudents/year/{yearofPassing}")
	public ResponseEntity<List<StudentDto>> getAllStudentsbyYearofPassing(@PathVariable long yearofPassing) {
		List<StudentDto> students= studentService.getAllStudentsbyYearofPassing(yearofPassing);
		return ResponseEntity.ok(students);
	}
	
	@GetMapping("/getAllStudents/branch/{branch}")
	public ResponseEntity<List<StudentDto>> getAllStudentsbyBranch(@PathVariable String branch) {
		List<StudentDto> students= studentService.getAllStudentsbyBranch(branch);
		return ResponseEntity.ok(students);
	}
	
	@DeleteMapping("/deleteStudent")
	public ResponseEntity<String> deleteStudent(@RequestBody DeleteStudentRequest request) {
		studentService.deleteStudent(request.getRollNo(),request.getEmail());
		return ResponseEntity.ok("Student data with roll no "+request.getRollNo() +" deleted succesfully!!");
	}
	
	// **ALUMNI SERVICES** //
	
	@GetMapping("/findAlumniByEmail/{email}")
	public ResponseEntity<AlumniDto> getAlumnibyEmail(@PathVariable String email) {
		AlumniDto alumni= alumniService.getAlumnibyEmail(email);
		return ResponseEntity.ok(alumni);
	}
	
	@GetMapping("/year/{yearOfPassing}")
	public ResponseEntity<List<AlumniDto>> getAllAlumniByYearOfPassing (@PathVariable Long yearOfPassing) {
		List<AlumniDto> alumnis= alumniService.getAllAlumniWithYearOfPassing(yearOfPassing);
		return ResponseEntity.ok(alumnis);
	}
	
	@PutMapping("/update/{email}")
	public ResponseEntity<String> updateAlumni(@PathVariable String email ,@RequestBody AlumniDto updatedAlumni) {
		alumniService.updateAlumni(email, updatedAlumni);
		return ResponseEntity.ok("Employee Updated succesfully !!!!");
	}
	
	@DeleteMapping("/delete/{email}")
	public ResponseEntity<String> deleteAlumniByEmail(@PathVariable String email) {
		alumniService.deleteAlumniByEmail(email);
		return ResponseEntity.ok("Alumni with email "+email+" deleted succesfully !!!");
	}
	
	@GetMapping("/getGroup/id/{groupId}")
	public ResponseEntity<GroupDto> getGroupById(@PathVariable Long groupId) {
		
		GroupDto group = groupService.getGroupByID(groupId);
		
		return ResponseEntity.ok(group);
		
	}
	
	@GetMapping("/getGroup/name/{groupName}")
	public ResponseEntity<GroupDto> getgroupByName(@PathVariable String groupName){
		
		GroupDto group = groupService.getgroupByName(groupName);
		return ResponseEntity.ok(group);
	}
	
	
}
