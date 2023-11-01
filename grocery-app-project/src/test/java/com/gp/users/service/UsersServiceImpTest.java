package com.gp.users.service;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gp.adminportal.bean.Products;
import com.gp.common.Orders;
import com.gp.common.exceptionstored.ProductNotFoundException;
import com.gp.users.beans.Cart;
import com.gp.users.beans.Users;
import com.gp.users.exceptionstored.UserNotFoundException;

@SpringBootTest
class UsersServiceImpTest {
   @Autowired
	private UsersService usersService;
   @Autowired
   private  CartService cartService;
   
	@Test
	void createUserTest() {
		Users user=new Users(0,"abc","abc@gmail.com","abc@12","abc","def"
				,LocalDate.parse("1998-08-13"),"1114567899","Andheri, Mumbai",1000);
		

		System.out.println("_________");
		System.out.println("Before adding");
		System.out.println(user);
		System.out.println("_________");
		
		Users newUser=usersService.createUser(user);
		
		System.out.println("_________");
		System.out.println("After adding");
		System.out.println(newUser);
		System.out.println("_________");
	}

	@Test
	void findAllUserTest() {
		
		List<Users> users=usersService.findAllUser();
	
		System.out.println("_________");
		users.forEach(user->System.out.println(user));
		System.out.println("_________");
	}
	
	@Test
	void loginUserTest()
	{
		try {
			Users user=usersService.loginUser("brook@gmail.com", "Brook@12");
			System.out.println("_________");
			System.out.println(user);
		} catch (UserNotFoundException e) {
			System.out.println(e);
		}
		
	}
	
	@Test
	void addToCartTest() {
		 cartService.addToCart(1, 3);
		
		System.out.println("__________");
		 System.out.println("added to cart");
		System.out.println("______________");
	}
		
	
	@Test
	void findProductByIdTest() {
		try {
			Products product = cartService.findProductById(1);
			System.out.println("____________");
			System.out.println(product);
			System.out.println("____________");
		} catch (ProductNotFoundException e) {
			System.out.println("____________");
			System.out.println(e);
			System.out.println("____________");
		}
	}
	
	@Test 
	void deleteProductByIdTest() {
		try {
			cartService.deleteProductById(0);
			
			System.out.println("____________");
			System.out.println("Deleted");
			System.out.println("____________");
		} catch (ProductNotFoundException e) {
			System.out.println("____________");
			System.out.println(e);
			System.out.println("____________");
		}
	}
	
	@Test
	void FindCartProductByIdTest()
	{
		Cart cart= cartService.FindCartProductById(1, 2);
		System.out.println("____________");
		System.out.println(cart);
		System.out.println("____________");
	}
		
	@Test
	void findAllCartProductTest() {
		List<Cart> cartsOfUser=cartService.findAllCartProduct(1);
		System.out.println("____________");
		cartsOfUser.forEach(cart->System.out.println(cart));
		System.out.println("____________");
	}
		
	@Test
	void removeProductFromCartTest()
	{
		 cartService.removeProductFromCart(1, 1);
		 System.out.println("____________");
		System.out.println("Deleted ..");
		System.out.println("____________");

	}
	
	@Test
	void checkOutFromCartTest() {
		 
		try {
			cartService.checkOutFromCart(4);
			System.out.println("__________");
			System.out.println("after delete from test case");
			System.out.println("__________");
		} catch (Exception e) {
			System.out.println("__________");
			System.out.println(e);
			System.out.println("__________");
		}
		
	}
	
	@Test 
	void transferFundsTest() {
		try {
			Users user =usersService.findUser(2);
			System.out.println("__________");
			System.out.println("before adding");
			System.out.println(user);
			System.out.println("__________");
			
		    usersService.transferFunds(2, 1750);
		    Users newuser =usersService.findUser(2);
			System.out.println("__________");
			System.out.println("after adding");
			System.out.println(newuser);
			System.out.println("__________");
		} catch (UserNotFoundException e) {
			System.out.println(e);
		}
	}
	
	@Test
	void editUserTest() {
		Users user;
		try {
			Users existinguser=usersService.findUser(2);
			System.out.println("__________");
			System.out.println("before adding");
			System.out.println(existinguser);
			System.out.println("__________");
			
			user = usersService.editUser(2," note@123", "address", "9898978", "note@gmail.com");
			System.out.println("__________");
			System.out.println("after adding");
			System.out.println(user);
			System.out.println("__________");
			
			
		} catch (UserNotFoundException e) {
			System.out.println(e);
		}
		
	}
	
	@Test
	void findOrdersForUserTest() {
		  List<Orders> userorders=cartService.findOrdersForUser(4);
		  System.out.println("__________");
		  userorders.forEach(order->System.out.println(order));
		  System.out.println("__________");
	}
	
	
}
