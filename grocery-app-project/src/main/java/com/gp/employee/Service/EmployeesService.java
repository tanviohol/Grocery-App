package com.gp.employee.Service;

public interface EmployeesService {
	
	String signIn(int employeeId, String password);
	
	void sendRequestToIncreaseProductQuantity(int productId, int quantity);
	
	
	void updateOrderStatus(int orderId, String newStatus);
	
	void unlockUserAccount(int userId);
	
	void editEmployeePassword(int employeeId, String newPassword);
	 
	
	


	
	

}
