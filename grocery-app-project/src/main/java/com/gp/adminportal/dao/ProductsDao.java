package com.gp.adminportal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gp.adminportal.bean.Employee;
import com.gp.adminportal.bean.Products;

public interface ProductsDao extends JpaRepository<Products, Integer>{

}
