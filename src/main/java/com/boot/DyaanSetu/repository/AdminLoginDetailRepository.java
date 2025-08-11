package com.boot.DyaanSetu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.DyaanSetu.entity.AdminLogin;

@Repository
public interface AdminLoginDetailRepository extends JpaRepository<AdminLogin, Long> {

	AdminLogin findByEmail(String email);
	
}
