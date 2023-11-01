package com.gp.employee.ServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp.adminportal.bean.Products;
import com.gp.adminportal.dao.ProductsDao;
import com.gp.common.Orders;
import com.gp.common.dao.OrderDao;
import com.gp.employee.Dao.EmployeesDao;
import com.gp.employee.Service.EmployeesService;
import com.gp.employee.beans.Employees;
import com.gp.users.beans.Users;
import com.gp.users.dao.UsersDao;

import jakarta.persistence.criteria.Order;

@Service
public class EmployeeServiceImpl implements EmployeesService {
	@Autowired
	private ProductsDao productDao;

	@Override
	public void sendRequestToIncreaseProductQuantity(int productId, int quantity) {
		Optional<Products> productOptional = productDao.findById(productId);

		if (productOptional.isPresent()) {
			Products product = productOptional.get();
			product.setQuantity(product.getQuantity() + quantity);
			productDao.save(product);
		} else {
			System.out.println("Product is not present");
		}
	}

	@Autowired
	private OrderDao orderDao;

	@Override
	public void updateOrderStatus(int orderId, String newStatus) {
		Optional<Orders> orderOptional = orderDao.findById(orderId);

		if (orderOptional.isPresent()) {
			Orders order = orderOptional.get();

			if (isValidOrderStatus(newStatus)) {
				order.setStatus(newStatus);
				orderDao.save(order);
			} else {
				System.out.println("No updates yet");
			}
		} else {
			System.out.println("order with given order id is not present");
		}
	}

	private boolean isValidOrderStatus(String status) {

		List<String> validStatuses = Arrays.asList("Shipped", "Out for Delivery", "Delivered", "Cancelled");
		return validStatuses.contains(status);
	}

	@Autowired
	private UsersDao userDao;

	@Override
	public void unlockUserAccount(int userId) {
		Optional<Users> userOptional = userDao.findById(userId);

		if (userOptional.isPresent()) {
			Users user = userOptional.get();

			user.setAccount_locked(false);
			userDao.save(user);
		} else {
			System.out.println("user with given id is not availble");
		}
	}

	@Autowired
	private EmployeesDao employeeDao;

	@Override
	public void editEmployeePassword(int employeeId, String newPassword) {
		Optional<Employees> employeeOptional = employeeDao.findById(employeeId);

		if (employeeOptional.isPresent()) {
			Employees employee = employeeOptional.get();
			// Update the employee's password
			employee.setPassword(newPassword);
			employeeDao.save(employee);
		} else {
			System.out.println("Employee with given id is not present");
		}
	}

	@Override
	public String signIn(int employeeId, String password) {
		Optional<Employees> employeeOptional = employeeDao.findById(employeeId);

		if (employeeOptional.isPresent()) {
			Employees employee = employeeOptional.get();

			if (employee.isFirstLogin()) {
				if (isValidNewPassword(password)) {

					employee.setPassword(password);
					employee.setFirstLogin(false);
					employeeDao.save(employee);
					return "Password changed successfully.";
				} else {
					return "Please provide a valid new password.";
				}
			} else {
				if (employee.getPassword().equals(password)) {
					return "Sign-in successful.";
				} else {
					return "Invalid password.";
				}
			}
		} else {
			return "Employee not found.";
		}
	}

	private boolean isValidNewPassword(String password) {

		boolean hasUppercase = false;
		boolean hasLowercase = false;
		boolean hasDigit = false;

		for (char c : password.toCharArray()) {
			if (Character.isUpperCase(c)) {
				hasUppercase = true;
			} else if (Character.isLowerCase(c)) {
				hasLowercase = true;
			} else if (Character.isDigit(c)) {
				hasDigit = true;
			}
		}

		return password != null && password.length() >= 8;
	}
}
