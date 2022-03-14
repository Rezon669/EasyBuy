package com.easybuy.app;

import java.util.NoSuchElementException;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.easybuy.app.Usersrepo;
@Controller
public class UsersController {

	@Autowired
	Usersrepo urepo;
	
	@RequestMapping("/")
	public String app() {
		
	return "home";
}
	@RequestMapping("/signin")
	public String signin() {
		
	return "signin";
}
	@RequestMapping("/signup")
	public String signup() {
		
	return "signup";
}
	
	@PostMapping("/createaccount")
	public String saveitem(Users user) {
	
		urepo.save(user);
		return "backtologin";
}
	@PostMapping("/signin/validation")
	public String signvalidation() {
	
		//a=Request.
		return "backtologin";
}
	
	@RequestMapping("/signin/validation/{username}{password}")
	public String login(@RequestBody Product product, @PathVariable String username, @PathVariable String password) {
	 
	        String a = "admin";
			if(username==a && password==a) {
				
				return "App";
		
			}	
			return null;
	}	
}

