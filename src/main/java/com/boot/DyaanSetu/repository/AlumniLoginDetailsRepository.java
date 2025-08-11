package com.boot.DyaanSetu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.DyaanSetu.entity.AlumniLogin;

public interface AlumniLoginDetailsRepository extends JpaRepository<AlumniLogin,Long > {
	
	AlumniLogin findByEmail(String email);
}
