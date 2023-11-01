package com.gp.users.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="cart")
public class Cart {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cart_id;
	private int user_id_ref;
	private int quantity ;
	private int product_id_ref;
	
	// generate constructors,getters and setters
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cart(int cart_id, int user_id_ref, int product_id_ref) {
		super();
		this.cart_id = cart_id;
		this.user_id_ref = user_id_ref;
		this.quantity =1;
		this.product_id_ref = product_id_ref;
	}

	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}

	public int getUser_id_ref() {
		return user_id_ref;
	}

	public void setUser_id_ref(int user_id_ref) {
		this.user_id_ref = user_id_ref;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getProduct_id_ref() {
		return product_id_ref;
	}

	public void setProduct_id_ref(int product_id_ref) {
		this.product_id_ref = product_id_ref;
	}

	@Override
	public String toString() {
		return "Cart [cart_id=" + cart_id + ", user_id_ref=" + user_id_ref + ", quantity=" + quantity
				+ ", product_id_ref=" + product_id_ref + "]";
	}

	

}
