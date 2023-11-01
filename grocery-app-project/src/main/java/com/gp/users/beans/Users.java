package com.gp.users.beans;

//import java.sql.Timestamp;
import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;
	private String username;
	private String email;
	private String password;
	private String first_name;
	private String last_name;
	private LocalDate dob;
	private String phone_number;
	private String address;
	private double amount;
	private boolean account_locked;
//	we can ommit this column as in MySQL our column has default current timestamp
//	private TimeStamp registration_date;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "account_number_ref")
	private Account account;

//	superclass constructor 
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

//	constructor with field and 
//	haven't included constructor of field account_locked and registration_date
	public Users(int user_id, String username, String email, String password, String first_name, String last_name,
			LocalDate dob, String phone_number, String address, double amount) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.dob = dob;
		this.phone_number = phone_number;
		this.address = address;
		this.amount = amount;
	}

//	getters and setters methods
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public boolean isAccount_locked() {
		return account_locked;
	}

	public void setAccount_locked(boolean account_locked) {
		this.account_locked = account_locked;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	// toString method to display the users info
	@Override
	public String toString() {
		return "Users [user_id=" + user_id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", first_name=" + first_name + ", last_name=" + last_name + ", dob=" + dob + ", phone_number="
				+ phone_number + ", address=" + address + ", amount=" + amount + ", account_locked=" + account_locked
				+ ", account=" + account + "]";
	}
}
