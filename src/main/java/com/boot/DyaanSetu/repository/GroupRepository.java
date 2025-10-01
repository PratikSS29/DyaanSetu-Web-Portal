package com.boot.DyaanSetu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.DyaanSetu.entity.Group;
import com.boot.DyaanSetu.entity.Student;


@Repository
public interface GroupRepository extends JpaRepository<Group, Long>{

	
		
		
}

