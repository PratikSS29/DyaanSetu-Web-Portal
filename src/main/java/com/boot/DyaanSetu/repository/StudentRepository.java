package com.boot.DyaanSetu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.DyaanSetu.entity.Student;

public interface StudentRepository extends JpaRepository<Student, String> {

	Optional<Student> findByEmail(String email);
	
	Optional<Student> findByPrnNo(String prnNo);
	
	List<Student> findAllByYearOfPassing(long yearOfPassing);
	
	List<Student> findAllBybranch(String branch);
}
