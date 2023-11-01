package com.gp.adminportal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gp.adminportal.bean.Employee;

//EmployeeRepository.java
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
}

