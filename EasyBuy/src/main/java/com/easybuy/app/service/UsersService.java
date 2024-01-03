package com.easybuy.app.service;

import java.util.Map;

import javax.naming.NameNotFoundException;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.easybuy.app.entity.Users;

public interface UsersService {

//	String loadUserByUsername(String username) throws NameNotFoundException;

	String createUsers(Users user);

	String loginValidation(String email, String password);

	void updatePassword(String password, String confirmpassword, String email);


}
