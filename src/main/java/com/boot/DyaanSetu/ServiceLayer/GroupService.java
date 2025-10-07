package com.boot.DyaanSetu.ServiceLayer;

import java.lang.foreign.Linker.Option;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.boot.DyaanSetu.dto.GroupDto;
import com.boot.DyaanSetu.entity.Group;
import com.boot.DyaanSetu.entity.GroupInvitation;

@Service
public interface GroupService {

	
	public GroupDto createGroup(String groupName , String leaderPrn);
	
	public GroupInvitation inviteMember(Long groupId , String leaderPrn , String studentPrn);
	
	public GroupDto getGroupByID(Long groupId);
	
	GroupDto getgroupByName(String groupName);
}
