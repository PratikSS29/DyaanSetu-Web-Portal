package com.boot.DyaanSetu.entity;

import com.boot.DyaanSetu.dto.GroupDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Group_invitation")

public class GroupInvitation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	//Which student is invited
	@ManyToOne
	@JoinColumn(name="student_prn" , referencedColumnName = "prn_number")
	private Student student;
	
	
	//Which group invited them
	@ManyToOne
	@JoinColumn(name="group_id")
	private Group group;
	
	
	//Status : Pending , Accepted , Rejected
	@Column(nullable = false)
	private String status;
	
	
}
