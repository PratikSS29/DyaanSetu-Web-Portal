package com.boot.DyaanSetu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.DyaanSetu.entity.Admin;
import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{

	Admin findByEmail(String email);
	
	List<Admin> findByDepartmentName(String departmentName);
	
}
