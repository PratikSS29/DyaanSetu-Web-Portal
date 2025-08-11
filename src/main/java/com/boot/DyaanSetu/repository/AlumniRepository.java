package com.boot.DyaanSetu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.DyaanSetu.dto.AlumniDto;
import com.boot.DyaanSetu.entity.Alumni;

public interface AlumniRepository extends JpaRepository<Alumni, Long> {
	
	Optional<Alumni> findByEmail(String email);
	
	List<Alumni> findByYearOfPassing(Long yearOfPassing);
	
	
}
