package com.gp.common;

import java.sql.Timestamp;

import com.gp.users.beans.Users;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_items")
public class OrderItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int order_item_id;
	private int order_id_ref;
	private int product_id_ref;
	private int quantity;
	private double item_price;

	public OrderItems() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderItems(int order_id_ref, int product_id_ref, int quantity, double item_price) {
		super();
		this.order_id_ref = order_id_ref;
		this.product_id_ref = product_id_ref;
		this.quantity = quantity;
		this.item_price = item_price;
	}

	public int getOrder_item_id() {
		return order_item_id;
	}

	public void setOrder_item_id(int order_item_id) {
		this.order_item_id = order_item_id;
	}

	public int getOrder_id_ref() {
		return order_id_ref;
	}

	public void setOrder_id_ref(int order_id_ref) {
		this.order_id_ref = order_id_ref;
	}

	public int getProduct_id_ref() {
		return product_id_ref;
	}

	public void setProduct_id_ref(int product_id_ref) {
		this.product_id_ref = product_id_ref;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getItem_price() {
		return item_price;
	}

	public void setItem_price(double item_price) {
		this.item_price = item_price;
	}

	@Override
	public String toString() {
		return "OrderItems [order_item_id=" + order_item_id + ", order_id_ref=" + order_id_ref + ", product_id_ref="
				+ product_id_ref + ", quantity=" + quantity + ", item_price=" + item_price + "]";
	}

}
