package com.gp.users.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.weaver.patterns.HasThisTypePatternTriedToSneakInSomeGenericOrParameterizedTypePatternMatchingStuffAnywhereVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gp.common.Orders;
import com.gp.users.beans.Cart;
import com.gp.users.beans.Users;
import com.gp.users.exceptionstored.UserNotFoundException;
import com.gp.users.service.CartService;
import com.gp.users.service.UsersService;


@CrossOrigin(origins={"*"})  //to enable all cors
@RestController 
@RequestMapping("/users")
public class UsersController {
   @Autowired
   private  UsersService userService;
   @Autowired
   private CartService cartService;
   
 //a string of exceptions
 	private List<Map> exceptionList =new ArrayList<>();
   
//	 creating for to register a user
 //  pass details like  username,email,password,first_name,last_name,dob,phone_number, address, amount in json body
   @PostMapping("/newUser")
  public ResponseEntity<Users>createNewProfile(@RequestBody Users user)
  {
	   Users newuser= userService.createUser(user);
	   return ResponseEntity.status(200).body(newuser);
  }
   
//	 login webservice
   // just pass email and password in json body at time of calling api
 @PostMapping("/login")
public ResponseEntity<Users>loginToProfile(@RequestBody Users user)
{
	    Users newuser;
		try {
			newuser = userService.loginUser(user.getEmail(), user.getPassword());
			return ResponseEntity.status(200).body(newuser);
		} catch (UserNotFoundException e) {
			 Map<String,String> ex=new HashMap<>();
			 ex.put("error", e.getMessage());
			 exceptionList.add(ex);
			return ResponseEntity.status(404).body(null);
		}   
} 
   
 // get all items from the cart for particular user
 // just pass user_id as params
@GetMapping("/userCart/{user_id}")
public ResponseEntity<List<Cart>>getAllCartitems(@PathVariable int user_id)
{
	   List<Cart> userCartlist=cartService.findAllCartProduct(user_id);
	   return ResponseEntity.status(200).body(userCartlist);
} 

//  adding items to cart
// for updating quantity also call same api
// just pass user_id and product_id 
// ensure you are passing valid user_id and product_id
@PostMapping("/addItems/{user_id}/{product_id}")
public ResponseEntity<String>addItemsToCart(@PathVariable int user_id,@PathVariable int product_id)
{
	    cartService.addToCart(user_id, product_id);
//	    List<Cart> userCartlist=cartService.findAllCartProduct(user_id);
	    return ResponseEntity.status(200).body("Item added to cart..");
} 
   
// delete items(it will not directly delete it will reduce by quantity) and  reducing items quantity
@PostMapping("/removeItems/{user_id}/{product_id}")
public ResponseEntity<String>removeItemsFromCart(@PathVariable int user_id,@PathVariable int product_id)
{
	    cartService.removeProductFromCart(user_id, product_id);
//	    List<Cart> userCartlist=cartService.findAllCartProduct(user_id);
	    return ResponseEntity.status(200).body("Item remove from cart..");
} 

 // getting history of orders
@GetMapping("/userOrders/{user_id}")
public ResponseEntity<List<Orders>>getAllUserOrders(@PathVariable int user_id)
{
	   List<Orders> userorders=cartService.findOrdersForUser(user_id);
	   return ResponseEntity.status(200).body(userorders);
} 

// checkout call
@PostMapping("/checkoutCart/{user_id}")
public ResponseEntity<String>checkoutUserCart(@PathVariable int user_id)
{
	    try {
			cartService.checkOutFromCart(user_id);
			return ResponseEntity.status(200).body("Order Placed successfully");
		} catch (Exception e) {
			 Map<String,String> ex=new HashMap<>();
			 ex.put("error", e.getMessage());
			 exceptionList.add(ex);
			 return ResponseEntity.status(404).body("Failed to order..");
		}
} 

// edit profile call
// it will need user_id and and details like password, address, phone_number
// email , etc
// if edit only particular field still for other fields mention pass previous data
@PostMapping("/editProfile")
public ResponseEntity<Users>editUserProfile(@RequestBody Users user )
{
	  try {
		Users newuser=userService.editUser(user.getUser_id(), user.getPassword(), user.getAddress(), user.getPhone_number(), user.getEmail());
		return ResponseEntity.status(200).body(newuser);
		
	} catch (UserNotFoundException e) {
		Map<String,String> ex=new HashMap<>();
		 ex.put("error", e.getMessage());
		 exceptionList.add(ex);
		 return ResponseEntity.status(404).body(null);
	}
} 

// transfer funds request
@PostMapping("/transferFunds/{user_id}/{amount}")
public ResponseEntity<String>transferFundsFromAccount(@PathVariable int user_id,@PathVariable double amount )
{
	 try {
		userService.transferFunds(user_id, amount);
		return ResponseEntity.status(404).body("Money added successfuly");
		
	} catch (UserNotFoundException e) {
		Map<String,String> ex=new HashMap<>();
		 ex.put("error", e.getMessage());
		 exceptionList.add(ex);
		 return ResponseEntity.status(404).body("Money transfer failed .. Pls check user_id or sufficient account balance");
	}
} 


}