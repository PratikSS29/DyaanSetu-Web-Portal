package com.boot.DyaanSetu.ServiceLayer;

import com.boot.DyaanSetu.entity.Group;
import com.boot.DyaanSetu.entity.GroupInvitation;

public interface GroupService {

	
	public Group createGroup(String groupName , String leaderPrn);
	
	public GroupInvitation inviteMember(Long groupId , String leaderPrn , String studentPrn);
	
	
	
}
