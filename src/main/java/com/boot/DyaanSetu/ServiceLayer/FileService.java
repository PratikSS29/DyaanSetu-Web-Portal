package com.boot.DyaanSetu.ServiceLayer;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.boot.DyaanSetu.entity.FileUpload;
import com.boot.DyaanSetu.entity.Group;
import com.boot.DyaanSetu.entity.Student;

public interface FileService {

	public FileUpload saveFile(MultipartFile file ,String uploaderPrn , Long groupId) throws IOException;
	
	
	
}
