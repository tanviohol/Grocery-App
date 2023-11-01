package com.gp.users.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int account_number;
	private double account_balance;

//	superclass constructor
	public Account() {
		super();
	}

//	constructor with fields
	public Account(int account_number, double account_balance) {
		super();
		this.account_number = account_number;
		this.account_balance = account_balance;
	}

//	getters and setters method for account
	public int getAccount_number() {
		return account_number;
	}

	public void setAccount_number(int account_number) {
		this.account_number = account_number;
	}

	public double getAccount_balance() {
		return account_balance;
	}

	public void setBalance(double account_balance) {
		this.account_balance = account_balance;
	}

	@Override
	public String toString() {
		return "Account [account_number=" + account_number + ", balance=" + account_balance + "]";
	}

	

}
