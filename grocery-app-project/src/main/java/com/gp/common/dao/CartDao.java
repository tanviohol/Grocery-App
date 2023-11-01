package com.gp.common.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.gp.users.beans.Cart;

public interface CartDao extends JpaRepository<Cart,Integer> {
	
	@Query("select c from Cart c where c.user_id_ref =?1 and c.product_id_ref =?2")
	public Cart FindProductInCart(int user_id_ref,int product_id_ref);
	
	@Modifying
	@Query("update Cart c set c.quantity = c.quantity + 1 where c.product_id_ref =?1 and c.user_id_ref=?2 ")
	public void AddProductInCart(int product_id_ref,int user_id_ref);
	
	@Query("select c from Cart c where c.user_id_ref =?1")
	public List<Cart>findCartProductsByUser(int user_id);
	
//	if cart has only one quantity the this method is used
	@Modifying
	@Query("delete  from Cart c where c.user_id_ref =?1 and c.product_id_ref=?2 ")
	public void deleteProductFromCart(int user_id,int product_id);

//	if cart has product who's quantity is more than 1 then 
	@Modifying
	@Query("update Cart c set c.quantity = c.quantity - 1 where c.product_id_ref =?1 and c.user_id_ref=?2 ")
	public void removeProductFromCart(int product_id_ref,int user_id_ref);
	
//	delete all cart relates particular user
	@Modifying
	@Query("delete  from Cart c where c.user_id_ref =?1  ")
	public void deleteAllItemsOfUserfromCart(int user_id);
}
