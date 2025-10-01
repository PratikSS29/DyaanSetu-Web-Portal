package com.boot.DyaanSetu.service.impl;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.boot.DyaanSetu.ServiceLayer.FileService;
import com.boot.DyaanSetu.entity.FileUpload;
import com.boot.DyaanSetu.entity.Group;
import com.boot.DyaanSetu.entity.Student;
import com.boot.DyaanSetu.repository.FileUploadRepository;

@Service
public class FileServiceImpl implements FileService {

	private FileUploadRepository fileRepo;
	
	@Override
	public FileUpload saveFile(MultipartFile file, Student uploader, Group group) throws IOException {
		
		String filePath="uploads/" + file.getOriginalFilename();
		
		File dest=new File(filePath);
		file.transferTo(dest);
		
		FileUpload fileUpload=new FileUpload();
		fileUpload.setFileName(file.getOriginalFilename());
		fileUpload.setFileType(file.getContentType());
		fileUpload.setFilePath(filePath);
		fileUpload.setUploadedBy(uploader);
		fileUpload.setGroup(group);
		
		return fileRepo.save(fileUpload);
	}

}
