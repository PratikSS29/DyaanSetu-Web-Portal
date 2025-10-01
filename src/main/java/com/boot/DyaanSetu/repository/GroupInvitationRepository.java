package com.boot.DyaanSetu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.DyaanSetu.entity.GroupInvitation;


@Repository
public interface GroupInvitationRepository extends JpaRepository<GroupInvitation, Long> {

	List<GroupInvitation> findByStudentPrnNoAndStatus(String prnNo , String status);	
	
	
}
