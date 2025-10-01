package com.boot.DyaanSetu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.DyaanSetu.entity.FileUpload;

@Repository
public interface FileUploadRepository extends JpaRepository<FileUpload, Long>{

}
