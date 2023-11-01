package com.gp.employee.beans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employees {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id")
	private int empId;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "email_id")
	private String emailId;

	private String password;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "registration_date")
	private String registrationDate;

	private boolean firstLogin;

	public boolean isFirstLogin() {
		return firstLogin;
	}

	public Employees(int empId, String userName, String emailId, String password, String firstName, String lastName,
			String registrationDate, boolean firstLogin) {
		super();
		this.empId = empId;
		this.userName = userName;
		this.emailId = emailId;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.registrationDate = registrationDate;
		this.firstLogin = firstLogin;
	}

	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Override
	public String toString() {
		return "Employees [empId=" + empId + ", userName=" + userName + ", emailId=" + emailId + ", password="
				+ password + ", firstName=" + firstName + ", lastName=" + lastName + ", registrationDate="
				+ registrationDate + ", firstLogin=" + firstLogin + "]";
	}

	public Employees(int empId, String userName, String emailId, String password, String firstName, String lastName,
			String registrationDate) {
		super();
		this.empId = empId;
		this.userName = userName;
		this.emailId = emailId;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.registrationDate = registrationDate;
	}

	public Employees() {
		super();
		// TODO Auto-generated constructor stub
	}

}
