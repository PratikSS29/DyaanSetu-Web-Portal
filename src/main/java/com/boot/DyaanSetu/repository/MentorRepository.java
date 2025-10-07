package com.boot.DyaanSetu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.DyaanSetu.entity.Mentor;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {

	
	
}
