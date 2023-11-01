package com.gp.users.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp.adminportal.bean.Products;
import com.gp.adminportal.dao.ProductsDao;
import com.gp.common.OrderItems;
import com.gp.common.Orders;
import com.gp.common.dao.CartDao;
import com.gp.common.dao.OrderDao;
import com.gp.common.dao.OrderItemsDao;
import com.gp.common.exceptionstored.ProductNotFoundException;
import com.gp.users.beans.Cart;
import com.gp.users.beans.Users;
import com.gp.users.dao.UsersDao;
import com.gp.users.exceptionstored.UserNotFoundException;

import jakarta.transaction.Transactional;

@Service("cartService")
public class CartServiceImp implements CartService {

	@Autowired
	private ProductsDao productDao;
	@Autowired
	private CartDao cartDao;
	@Autowired
	private UsersDao usersDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired 
	OrderItemsDao orderItemsDao;
	
	@Override
	public List<Products> findAllProduct() {
		
		return productDao.findAll();
	}

	@Override
	public Products findProductById(int product_id) throws ProductNotFoundException {
		Optional<Products> products = productDao.findById(product_id);
		return products.orElseThrow(()-> new ProductNotFoundException ("Product Id"+product_id +" Not Found"));
	}

	@Override
	@Transactional
	public void deleteProductById(int product_id) throws ProductNotFoundException {
		findProductById(product_id);
//	    findProductById check the product are present or not 
//		if not it throws the exception otherwise delete the product	
		productDao.deleteById(product_id);
		
	}

	@Override
	@Transactional
	public void addToCart(int user_id,int product_id) {
	Cart cartProduct = cartDao.FindProductInCart(user_id, product_id);
	
	if(cartProduct == null) {
		
		Cart cart = new Cart(0,user_id,product_id);
		Cart newcart =cartDao.save(cart);
	}else {
			
	cartDao.AddProductInCart(product_id, user_id);

	}
	
//	return updatedCart;
		
	}

	@Override
	@Transactional
	public void removeProductFromCart(int user_id, int product_id) {
		Cart cart = cartDao.FindProductInCart(user_id, product_id);
		if(cart!=null) {
			if(cart.getQuantity()==1) {
				cartDao.deleteProductFromCart(user_id, product_id);
			}else if(cart.getQuantity()>1) {
				cartDao.removeProductFromCart(product_id, user_id);
			}
			
		}
		
	}

	@Override
	public List<Cart> findAllCartProduct(int user_id) {
		
		return cartDao.findCartProductsByUser(user_id);
	}

	@Override
	public Cart FindCartProductById(int user_id, int product_id) {
		Cart cart = cartDao.FindProductInCart(user_id, product_id);
		
		return cart;
	}

	@Override
	@Transactional
	public void checkOutFromCart(int user_id) throws Exception {
      List<Cart> userCart =  findAllCartProduct(user_id);
      double cartTotal_amt=0 ;
      
    	for( Cart item: userCart) {
    		 double product_price=0;
    			try {
    				product_price = findProductById(item.getProduct_id_ref()).getPrice();
    				cartTotal_amt =cartTotal_amt+( item.getQuantity()*product_price);
    			} catch (ProductNotFoundException e) {
    				System.out.println(e);
    			}
    	}
//    	 System.out.println(cartTotal_amt);
    	Users user;
		try {
			user = usersDao.findById(user_id).orElseThrow(()-> new UserNotFoundException("Invalid User Id"));
			if(user.getAmount()>cartTotal_amt) {
				 Orders orders=new Orders(user.getUser_id(),cartTotal_amt);
				 Orders neworder=orderDao.save(orders);
				 System.out.println(neworder);
				for(Cart item: userCart) {
				    double product_price=0;
					try {
	    				product_price = findProductById(item.getProduct_id_ref()).getPrice();
	    	
	    			} catch (ProductNotFoundException e) {
	    				System.out.println(e);
	    			}
					
				  OrderItems orderitem=new OrderItems(neworder.getOrder_id(),item.getProduct_id_ref(),item.getQuantity(),product_price);
				  OrderItems neworderitem= orderItemsDao.save(orderitem);
				  System.out.println(neworderitem);
				  cartDao.deleteAllItemsOfUserfromCart(user_id);
				  System.out.println("Deleted..");
			  }
				
		  
			}else {
				throw new Exception("Insufficient Amount");
			}
			
		} catch (UserNotFoundException e) {
			System.out.println(e);
		}	   
	}

// get all order history related particular user_id
	@Override
	public List<Orders> findOrdersForUser(int user_id) {
		List<Orders> orders=orderDao.findAllOrdersForUser(user_id);
		return orders;
	}
	
	
}
