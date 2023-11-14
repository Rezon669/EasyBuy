package com.easybuy.app.service;

import javax.naming.NameNotFoundException;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;

import com.easybuy.app.entity.Users;

public interface UsersService {

	Users loadUserByUsername(String username) throws NameNotFoundException;

	ResponseEntity<String> createUsers(Users user);

	void emailVerification(String emailid);

	

	void loginValidation(String username, String password, HttpSession session);

	void checkPassword(String password, String confirmpassword, String email);

	//void checkPassword(String password, String confirmpassword, String email);
	
	

}
