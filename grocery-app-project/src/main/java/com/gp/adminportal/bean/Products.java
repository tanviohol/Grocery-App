package com.gp.adminportal.bean;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="product")
public class Products {

	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_id")
	private int product_Id;
	@Column(name="name")
	private String name;
	@Column(name="price")
	private double price;
	
	private int quantity;
	@Column(name="image_url")
	private String image_Url;
	@Column(name="added_date")
	private LocalDate added_Date;
	public Products(int product_Id, String name, double price, int quantity, String image_Url, LocalDate added_Date) {
		super();
		this.product_Id = product_Id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.image_Url = image_Url;
		this.added_Date = added_Date;
	}
	public int getProduct_Id() {
		return product_Id;
	}
	public void setProduct_Id(int product_Id) {
		this.product_Id = product_Id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getImage_Url() {
		return image_Url;
	}
	public void setImage_Url(String image_Url) {
		this.image_Url = image_Url;
	}
	public LocalDate getAdded_Date() {
		return added_Date;
	}
	public void setAdded_Date(LocalDate added_Date) {
		this.added_Date = added_Date;
	}
	@Override
	public String toString() {
		return "Product [product_Id=" + product_Id + ", name=" + name + ", price=" + price + ", quantity=" + quantity
				+ ", image_Url=" + image_Url + ", added_Date=" + added_Date + "]";
	}
	public Products() {
		super();
		// TODO Auto-generated constructor stub
	} 
	
	

}
