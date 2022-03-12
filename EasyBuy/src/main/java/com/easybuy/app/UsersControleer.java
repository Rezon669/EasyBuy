package com.easybuy.app;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.easybuy.app.Usersrepo;
@Controller
public class UsersControleer {

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
}}
