package com.gp.common;

import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int order_id;
	private int user_id_ref;
//	we can ommit this column as in MySQL our column has default current timestamp
//	private TimeStamp order_date;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name= "order_date")
	private Date orderDate;
	private double total_amount;
    private String status;
//	superclass constructor
	public Orders() {
		super();
		
	}

//constructor with field 
	public Orders( int user_id_ref, double total_amount) {
		super();
//		this.order_id = order_id;
		this.user_id_ref = user_id_ref;
		this.total_amount = total_amount;
	}

public Date getOrder_date() {
		return orderDate;
	}

	public void setOrder_date(Date order_date) {
		this.orderDate = order_date;
	}

	//getters and setters method
	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getUser_id_ref() {
		return user_id_ref;
	}

	public void setUser_id_ref(int user_id_ref) {
		this.user_id_ref = user_id_ref;
	}

	public double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}

public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	//toString method to display the order details
	@Override
	public String toString() {
		return "Orders [order_id=" + order_id + ", user_id_ref=" + user_id_ref + ", total_amount=" + total_amount
				+ ", status=" + status + "]";
	}


}
