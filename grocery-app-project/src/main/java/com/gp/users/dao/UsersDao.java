package com.gp.users.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gp.users.beans.Users;

public interface UsersDao extends JpaRepository<Users,Integer>{
	
//	Search Users By EmailId 
	@Query("select u from Users u  where u.email =?1")
	public Users findByEmailId(String emailid);

}
