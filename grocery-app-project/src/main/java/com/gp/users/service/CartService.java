package com.gp.users.service;

import java.util.List;

import com.gp.adminportal.bean.Products;
import com.gp.common.Orders;
import com.gp.common.exceptionstored.ProductNotFoundException;
import com.gp.users.beans.Cart;


public interface CartService {
	
//	this methods are productService related methods
	
	List<Products>findAllProduct();
	Products findProductById(int product_id) throws ProductNotFoundException;
	void deleteProductById(int product_id)throws ProductNotFoundException;
	
//	this methods are cart related methods
	void addToCart(int user_id,int product_id);
	void removeProductFromCart(int user_id,int product_id);
//	return all cart products related to particular user
	List<Cart>findAllCartProduct(int user_id);
	Cart FindCartProductById(int user_id,int product_id);
	
	void checkOutFromCart(int user_id) throws Exception;
	
	// get all order history related particular user_id
	List<Orders> findOrdersForUser(int user_id);

}
