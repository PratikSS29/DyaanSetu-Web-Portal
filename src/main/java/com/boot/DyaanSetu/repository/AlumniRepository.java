package com.boot.DyaanSetu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.DyaanSetu.dto.AlumniDto;
import com.boot.DyaanSetu.entity.Alumni;

@Repository
public interface AlumniRepository extends JpaRepository<Alumni, Long> {
	
	Optional<Alumni> findByEmail(String email);
	
	List<Alumni> findByYearOfPassing(Long yearOfPassing);
	
	
}
