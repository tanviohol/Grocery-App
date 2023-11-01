package com.gp.employee.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gp.employee.beans.Employees;

public interface EmployeesDao extends JpaRepository<Employees, Integer> {

}
