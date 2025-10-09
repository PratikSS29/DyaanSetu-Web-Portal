package com.boot.DyaanSetu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.DyaanSetu.ServiceLayer.GroupService;
import com.boot.DyaanSetu.ServiceLayer.MentorService;
import com.boot.DyaanSetu.dto.GroupDto;
import com.boot.DyaanSetu.dto.MentorDashboardDto;
import com.boot.DyaanSetu.entity.MentorLogin;

@RestController
@RequestMapping("/api/mentor")
public class MentorController {
	
	@Autowired
	private MentorService mentorService;
	
	@Autowired
	private GroupService groupService;
	
	@PostMapping("/login")
	public ResponseEntity<?> mentorLogin(@RequestBody MentorLogin request) {
		
		String msg = mentorService.mentorLogin(request.getEmail(), request.getPassword());
		
		return ResponseEntity.ok("Login");
	}
	
	@GetMapping("/dashboard")
	public ResponseEntity<MentorDashboardDto> getDashboard() {
		
		return null;
		
	}
	
	@GetMapping("/getGroup/id/{groupId}")
	public ResponseEntity<GroupDto> getGroupDetails(@PathVariable Long groupId) {
	
		GroupDto group = groupService.getGroupByID(groupId);
		
		return ResponseEntity.ok(group);
		
	}
	
	@GetMapping("/getGroup/name/{groupName}")
	public ResponseEntity<GroupDto> getGroupDetails(@PathVariable String groupName) {
		
		GroupDto group = groupService.getgroupByName(groupName);
		return ResponseEntity.ok(group);
	}

}
