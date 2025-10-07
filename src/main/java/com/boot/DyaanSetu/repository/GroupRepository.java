package com.boot.DyaanSetu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.DyaanSetu.entity.Group;


@Repository
public interface GroupRepository extends JpaRepository<Group, Long>{

	Optional<Group> findByGroupName(String groupName);
		
		
}

