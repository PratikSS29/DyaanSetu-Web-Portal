package com.boot.DyaanSetu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Student_LogIn_Details")
public class StudentLogin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long LoginId;
	
	@Column(name="email" , nullable = false , unique = true)
	private String email;
	
	@Column(name = "password" , nullable = false)
	private String password;
	
}
