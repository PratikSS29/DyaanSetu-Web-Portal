package com.boot.DyaanSetu.Controller;
	
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boot.DyaanSetu.Config.AlumniTempRegistrationCache;
import com.boot.DyaanSetu.Config.StudentTempRegistrationCache;
import com.boot.DyaanSetu.ServiceLayer.AlumniService;
import com.boot.DyaanSetu.dto.AlumniDto;
import com.boot.DyaanSetu.dto.JwtResponse;
import com.boot.DyaanSetu.dto.LogInRequestDto;
import com.boot.DyaanSetu.dto.SetPasswordDto;
import com.boot.DyaanSetu.entity.AlumniLogin;
import com.boot.DyaanSetu.entity.StudentLogin;
import com.boot.DyaanSetu.exception.ResourceNotFoundException;
import com.boot.DyaanSetu.repository.AlumniLoginDetailsRepository;
import com.boot.DyaanSetu.service.impl.JwtService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
	
@CrossOrigin(origins = "http://localhost:5173",
						allowCredentials = "true")

	@AllArgsConstructor
	@RestController
	@RequestMapping("/api/alumni")
	public class AlumniController {
	
		@Autowired
		private AlumniService alumniService;
		
		@Autowired
		private AlumniTempRegistrationCache tempcache;
		
		@Autowired
		AuthenticationManager authenticationManager;
		
		@Autowired
		JwtService jwtService;
		
		@Autowired
		AlumniLoginDetailsRepository repository;
		
		@Autowired
		BCryptPasswordEncoder passwordEncoder;
		
		//REST API Add Alumni
		@PostMapping("/info")
		public ResponseEntity<String> saveAlumniInfo (@RequestBody AlumniDto alumniDto) {

			String tempId=tempcache.storeTempRegistration(alumniDto);
			
			return ResponseEntity.ok(tempId);
		}
		
		@PostMapping("/set-password/{tempId}")
		public ResponseEntity<AlumniDto> setPassword(
				@PathVariable String tempId,
				@RequestBody @Valid SetPasswordDto setPasswordDto){
			
			AlumniDto alumniDto=tempcache.getTempRegistration(tempId);
			AlumniDto savedAlumni=alumniService.completeRegistration(alumniDto, setPasswordDto.getPassword());
			tempcache.clearTempRegistration(tempId);
			
			return new ResponseEntity<>(savedAlumni,HttpStatus.CREATED);
		}
		
		@PostMapping("/login")
		public ResponseEntity<?> alumniLogin(@RequestBody AlumniLogin request) {
		    AlumniLogin student = repository.findByEmail(request.getEmail());
		    if (student == null || !passwordEncoder.matches(request.getPassword(), student.getPassword())) {
		        throw new ResourceNotFoundException("Invalid credentials");
		    }
		    String token = jwtService.generateToken(student.getEmail(), "ALUMNI");
		    return ResponseEntity.ok(new JwtResponse(token));
		}
		
	}
