package com.gp.users.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp.users.beans.Account;
import com.gp.users.beans.Users;
import com.gp.users.dao.UsersDao;
import com.gp.users.exceptionstored.UserNotFoundException;

import jakarta.transaction.Transactional;

@Service("userService")
public class UsersServiceImp implements UsersService {
	
	@Autowired
	private UsersDao usersDao;

	@Override
	@Transactional
	public Users createUser(Users user) {
	  Account accnt=new Account(0,5000);
	  user.setAccount(accnt);
	  Users users = usersDao.save(user);
	
		return users;
	}

	@Override
	public List<Users> findAllUser() {
		
		return usersDao.findAll();
	}

	@Override
	public Users loginUser(String email, String password) throws UserNotFoundException {
		Users user=usersDao.findByEmailId(email);
		if(user==null) {
			throw new UserNotFoundException("Invalid EmailId!");
		}else if(!user.getPassword().equals(password)){
			throw new UserNotFoundException("Invalid Password!");
		}
		return user;
	}

	@Override
	@Transactional
	public void transferFunds(int user_id, double amount) throws UserNotFoundException{
			Users user = usersDao.findById(user_id).orElseThrow(()-> new UserNotFoundException());
		double account_balance=	user.getAccount().getAccount_balance();
		if(account_balance>amount) {
			double newuseramt=amount+user.getAmount();		
			double newaccount_balance = user.getAccount().getAccount_balance()-amount;
			user.setAmount(newuseramt);
			Account account=user.getAccount();
			account.setBalance(newaccount_balance);
		}
		
		
	}

	@Override
	public Users findUser(int user_id) throws UserNotFoundException {
		Users user = usersDao.findById(user_id).orElseThrow(()-> new UserNotFoundException("Invalid User Id"));
		return user;
	}

	@Override
	@Transactional
	public Users editUser(int user_id,String password, String address, String phone_number, String email) throws UserNotFoundException {
		Users user = usersDao.findById(user_id).orElseThrow(()-> new UserNotFoundException("Invalid User Id"));
		user.setEmail(email);
		user.setAddress(address);
		user.setPassword(password);
		user.setPhone_number(phone_number);
		return user;
	}

	@Override
	public void unlockUserAccount(int userId) {
		// TODO Auto-generated method stub
		Users user =usersDao.findById(userId).get();
		user.setAccount_locked(false);
		usersDao.save(user);
	}

}
