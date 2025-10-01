package com.boot.DyaanSetu.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.boot.DyaanSetu.ServiceLayer.FileService;
import com.boot.DyaanSetu.ServiceLayer.GroupService;
import com.boot.DyaanSetu.ServiceLayer.InvitationService;
import com.boot.DyaanSetu.entity.FileUpload;
import com.boot.DyaanSetu.entity.Group;
import com.boot.DyaanSetu.entity.GroupInvitation;
import com.boot.DyaanSetu.entity.Student;
import com.boot.DyaanSetu.repository.StudentRepository;
import com.boot.DyaanSetu.service.impl.GroupServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {

	@Autowired
	private GroupService groupService;
	
	
	
	@Autowired
	private InvitationService invitationService;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private FileService fileService;


	@PostMapping("/create")
	public ResponseEntity<Group> createGroup(@RequestParam String groupName , @RequestParam String leaderPrn) {
		
		Group group=groupService.createGroup(groupName, leaderPrn);
		
		return new ResponseEntity<>(group,HttpStatus.CREATED);
	}
	
	@PostMapping("/{groupId}/invite")
	public GroupInvitation inviteMember(@PathVariable Long groupId,
										@RequestParam String leaderPrn,
										@RequestParam String studentPrn) {
		
		return groupService.inviteMember(groupId, leaderPrn, studentPrn);
	}
	
	@PostMapping("/invitations/{id}/respond")
	public String respondInvitation(@PathVariable Long id, @RequestParam String action) {
		
		return invitationService.respondToInvitation(id, action);
	}
	
	  @GetMapping("/invitations/{studentPrn}")
	    public List<GroupInvitation> getInvitations(@PathVariable String studentPrn) {
	        return invitationService.getPendingInvitations(studentPrn);
	    }

	    @PostMapping("/{groupId}/upload")
	    public FileUpload uploadFile(@PathVariable Long groupId,
	                                 @RequestParam String uploaderPrn,
	                                 @RequestParam("file") MultipartFile file) throws IOException {
	        Student uploader = studentRepo.findByPrnNo(uploaderPrn)
	                .orElseThrow(() -> new RuntimeException("Student not found"));
	        Group group = uploader.getGroup();
	        if (group == null || !group.getId().equals(groupId)) {
	            throw new RuntimeException("You are not part of this group!");
	        }
	        return fileService.saveFile(file, uploader, group);
	    }

}
