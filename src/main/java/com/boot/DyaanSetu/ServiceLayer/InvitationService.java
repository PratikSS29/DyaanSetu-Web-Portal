package com.boot.DyaanSetu.ServiceLayer;

import java.util.List;

import com.boot.DyaanSetu.entity.GroupInvitation;

public interface InvitationService {

	public String respondToInvitation(Long invitationID , String action);
	
	public List<GroupInvitation> getPendingInvitations(String studentPrn);
}
