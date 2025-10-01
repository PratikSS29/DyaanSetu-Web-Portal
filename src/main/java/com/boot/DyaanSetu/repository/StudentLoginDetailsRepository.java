package com.boot.DyaanSetu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.DyaanSetu.entity.Student;
import com.boot.DyaanSetu.entity.StudentLogin;

@Repository
public interface StudentLoginDetailsRepository extends JpaRepository<StudentLogin, Long> {

	StudentLogin findByEmail(String email);

}
