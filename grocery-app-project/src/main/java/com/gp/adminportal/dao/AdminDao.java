package com.gp.adminportal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gp.adminportal.bean.Admin;

@Repository
public interface AdminDao extends JpaRepository<Admin, Integer> {
	
	@Query("select a from Admin a where email=?1")
	Admin findByEmailId(String emailId);
}
