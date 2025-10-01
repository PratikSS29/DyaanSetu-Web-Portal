package com.boot.DyaanSetu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Project_File")

public class FileUpload {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String fileName;
	
	private String fileType;
	
	private String filePath;
	
	@ManyToOne
	@JoinColumn(name="uploaded_by", referencedColumnName = "prn_number")
	private Student uploadedBy;
	
	@ManyToOne
	@JoinColumn(name="group_id")
	private Group group;
}
