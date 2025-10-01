package com.boot.DyaanSetu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.boot.DyaanSetu.entity.AlumniLogin;

@Repository
public interface AlumniLoginDetailsRepository extends JpaRepository<AlumniLogin,Long > {
	
	AlumniLogin findByEmail(String email);
}
