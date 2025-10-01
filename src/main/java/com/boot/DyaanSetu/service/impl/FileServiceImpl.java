package com.boot.DyaanSetu.service.impl;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.boot.DyaanSetu.ServiceLayer.FileService;
import com.boot.DyaanSetu.entity.FileUpload;
import com.boot.DyaanSetu.entity.Group;
import com.boot.DyaanSetu.entity.Student;
import com.boot.DyaanSetu.repository.FileUploadRepository;
import com.boot.DyaanSetu.repository.StudentRepository;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private FileUploadRepository fileRepo;
	
	@Autowired
	StudentRepository studentRepo;
	
	@Override
	public FileUpload saveFile(MultipartFile file, String uploaderPrn, Long groupId) throws IOException {
		
		Student uploader = studentRepo.findByPrnNo(uploaderPrn)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Group group = uploader.getGroup();
        if (group == null || !group.getId().equals(groupId)) {
            throw new RuntimeException("You are not part of this group!");
        }
		
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
