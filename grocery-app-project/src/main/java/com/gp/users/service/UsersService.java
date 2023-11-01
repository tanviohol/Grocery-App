package com.gp.users.service;

import java.util.List;

import com.gp.users.beans.Users;
import com.gp.users.exceptionstored.UserNotFoundException;

public interface UsersService {
	
	Users createUser(Users user);
	List<Users>findAllUser();
	Users loginUser(String email,String password) throws UserNotFoundException;
	
	void transferFunds(int user_id,double amount) throws UserNotFoundException;
	
	Users findUser(int user_id) throws UserNotFoundException;
	Users editUser(int user_id,String password,String address,String phone_number,String email) throws UserNotFoundException;
	void unlockUserAccount(int userId);
	
}
