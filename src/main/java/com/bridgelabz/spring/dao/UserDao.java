package com.bridgelabz.spring.dao;



import com.bridgelabz.spring.model.UserDetails;

public interface UserDao {


	int register(UserDetails user);

	UserDetails loginUser(String emailId);

	UserDetails getUserById(int id);

	void updateUser(UserDetails user);

   void deleteUser(int id);
}
