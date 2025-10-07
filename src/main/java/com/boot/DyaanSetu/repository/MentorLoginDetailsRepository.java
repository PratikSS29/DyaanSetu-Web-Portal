package com.boot.DyaanSetu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.DyaanSetu.entity.Mentor;
import com.boot.DyaanSetu.entity.MentorLogin;

@Repository
public interface MentorLoginDetailsRepository extends JpaRepository<MentorLogin, Long> {

	public Optional<MentorLogin> findByEmail(String email);
	
}
