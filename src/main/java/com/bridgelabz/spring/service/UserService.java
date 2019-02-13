package com.bridgelabz.spring.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bridgelabz.spring.model.UserDetails;

public interface UserService {

	boolean register(UserDetails user, HttpServletRequest request);

	UserDetails loginUser(UserDetails user, HttpServletRequest request, HttpServletResponse response);

	UserDetails updateUser(String token, UserDetails user, HttpServletRequest request);

	UserDetails deleteUser(String token, HttpServletRequest request);

	UserDetails activateUser(String token, HttpServletRequest request);

	boolean forgotPassword(String emailId, HttpServletRequest request);

	UserDetails resetPassword(UserDetails user, String token, HttpServletRequest request);   
}
