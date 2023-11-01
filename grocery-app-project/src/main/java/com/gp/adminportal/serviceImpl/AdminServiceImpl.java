package com.gp.adminportal.serviceImpl;


import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp.adminportal.bean.Admin;
import com.gp.adminportal.bean.Employee;
import com.gp.adminportal.bean.Products;
import com.gp.adminportal.dao.AdminDao;
import com.gp.adminportal.dao.EmployeeRepository;
import com.gp.adminportal.dao.ProductsDao;
import com.gp.adminportal.exception.ProductNotFoundException;
import com.gp.adminportal.service.AdminService;
import com.gp.common.Orders;
import com.gp.common.dao.OrderDao;

import jakarta.persistence.criteria.Order;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private EmployeeRepository EmployeeRepository;
	@Autowired
	private ProductsDao productRepository;
	
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private OrderDao orderDao ;

	@Override
	public Employee saveEmployee( Employee employee) {

//		Employee employee = new Employee();
//		employee.setUsername(username);
//		employee.setEmail(email);
//		employee.setPassword(password);
//		employee.setFirst_name(first_name);
//		employee.setLast_name(last_name);

		return EmployeeRepository.save(employee);
	}

	@Override
	public void deleteEmployee(int id) {
		EmployeeRepository.deleteById(id);
	}

	@Override
    public Products addProduct(Products product) {
        // Add validation and business logic as needed
        return productRepository.save(product);
    }

	 @Override
	    public Products updateProduct(int productId, Products product) throws ProductNotFoundException {
	        // Add validation and business logic as needed
	        Products existingProduct = productRepository.findById(productId)
	                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));

	        // Update product attributes
	        existingProduct.setName(product.getName());
	        existingProduct.setQuantity(product.getQuantity());
	        existingProduct.setPrice(product.getPrice());
	        existingProduct.setProduct_Id(product.getProduct_Id());

	        return productRepository.save(existingProduct);
	    }

	@Override
    public void deleteProduct(int productId) throws ProductNotFoundException {
        
        Products product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));

        productRepository.delete(product);
    }

	@Override
	public Admin logIn(String emailId, String password) {
		 Admin admin = adminDao.findByEmailId(emailId);

	        if (admin != null && admin.getPassword().equals(password)) {
	            return admin; // Successful authentication
	        }

	        return null; // Authentication failed
	}

	@Override
	public List<Orders> dailyReport(Date startDate,Date endDate) {
		return orderDao.findByTimestampBetween(startDate,endDate);
	}

	@Override
	public List<Products> getAallProducts() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	public List<Employee> getAallEmployees() {
		// TODO Auto-generated method stub
		return EmployeeRepository.findAll();
	}

	

}
