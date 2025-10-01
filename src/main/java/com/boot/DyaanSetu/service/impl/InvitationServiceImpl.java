package com.boot.DyaanSetu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.DyaanSetu.ServiceLayer.InvitationService;
import com.boot.DyaanSetu.entity.GroupInvitation;
import com.boot.DyaanSetu.entity.Student;
import com.boot.DyaanSetu.repository.GroupInvitationRepository;
import com.boot.DyaanSetu.repository.GroupRepository;
import com.boot.DyaanSetu.repository.StudentRepository;

@Service
public class InvitationServiceImpl implements InvitationService{

	@Autowired
	private GroupInvitationRepository invitationRepo;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private GroupRepository groupRepo;
	
	@Override
	public String respondToInvitation(Long invitationID, String action) {
	    GroupInvitation invitation = invitationRepo.findById(invitationID)
	        .orElseThrow(() -> new RuntimeException("Invitation not Found "));

	    if (action.equalsIgnoreCase("ACCEPT")) {
	    	int memberCount=invitation.getGroup().getStudents().size();
	    	System.out.println(memberCount);
	    	if(memberCount==3) {
	    		throw new RuntimeException("Group already has maximum members (3)");
	    	}
    		invitation.setStatus("ACCEPTED");
	        Student student = invitation.getStudent();
	        student.setGroup(invitation.getGroup());
	        studentRepo.save(student);
	    } else if (action.equalsIgnoreCase("REJECT")) {
	        invitation.setStatus("REJECTED");
	    } else {
	        throw new IllegalArgumentException("Invalid action: " + action);
	    }

	    invitationRepo.save(invitation);
	    return "Invitation " + invitation.getStatus();
	}


	@Override
	public List<GroupInvitation> getPendingInvitations(String studentPrn) {
		
		return invitationRepo.findByStudentPrnNoAndStatus(studentPrn, "PENDING");
	}

	
	
}
