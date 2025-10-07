package com.boot.DyaanSetu.service.impl;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.DyaanSetu.ServiceLayer.GroupService;
import com.boot.DyaanSetu.dto.GroupDto;
import com.boot.DyaanSetu.entity.Group;
import com.boot.DyaanSetu.entity.GroupInvitation;
import com.boot.DyaanSetu.entity.Student;
import com.boot.DyaanSetu.exception.GroupNotFoundException;
import com.boot.DyaanSetu.exception.ResourceNotFoundException;
import com.boot.DyaanSetu.mapper.GroupMapper;
import com.boot.DyaanSetu.repository.GroupInvitationRepository;
import com.boot.DyaanSetu.repository.GroupRepository;
import com.boot.DyaanSetu.repository.StudentRepository;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private  StudentRepository studentRepo;
	
	@Autowired
	private GroupRepository groupRepo;
	
	@Autowired
	private GroupInvitationRepository invitationRepo;
	
	
	@Override
	public GroupDto createGroup(String groupName, String leaderPrn) {
		Student leader = studentRepo.findByPrnNo(leaderPrn)
									.orElseThrow(() -> new ResourceNotFoundException("Student with PRN "+leaderPrn +" does not exists"));
		
		Group group=new Group();
		group.setGroupName(groupName);
		group.setLeader(group.getLeader());
		
		
		 Group savedGroup = groupRepo.save(group);
		 return GroupMapper.toGroupDto(savedGroup);
	}

	@Override
	public GroupInvitation inviteMember(Long groupId, String leaderPrn, String studentPrn) {
		
		Group group= groupRepo.findById(groupId)
				 .orElseThrow(() -> new ResourceNotFoundException("Group with "+groupId +" not found"));
		
		if (!group.getLeader().getPrnNo().equals(leaderPrn)) {
			throw new RuntimeException("Only leader can invite members !");
		}
		
		Student student = studentRepo.findByPrnNo(studentPrn)
					.orElseThrow(() -> new RuntimeException(" Student with prn "+studentPrn +" does not exists"));
		
		if(group.getLeader().getPrnNo().equals(studentPrn)) {
			throw new RuntimeException(studentPrn+" is already the leader of this group ");
		}
		
		if(student.getGroup() !=null) {
			throw new RuntimeException(studentPrn+" already belongs to another group !");
		}
		
		GroupInvitation invitation = new GroupInvitation();
		
		invitation.setGroup(group);
		invitation.setStudent(student);
		invitation.setStatus("PENDING");
		
		return invitationRepo.save(invitation);
	}

	@Override
	public GroupDto getGroupByID(Long groupId) {
			
		Group group =groupRepo.findById(groupId)
								.orElseThrow(() -> new ResourceNotFoundException("Group with ID "+groupId +" does not exists" ));
							 
		return GroupMapper.toGroupDto(group);
	}

	@Override
	public GroupDto getgroupByName(String groupName) {
		
	  Group group= groupRepo.findByGroupName(groupName)
			  		.orElseThrow(() -> new GroupNotFoundException("group with name "+groupName +" does not exists !"));
		
	  return GroupMapper.toGroupDto(group);
	}

	
	
	
}
