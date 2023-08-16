package com.easybuy.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.easybuy.app.entity.Users;
import com.easybuy.app.repository.UsersRepo;
import com.easybuy.app.service.LoginService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Controller
@Transactional

public class LoginController {

	private static final Logger logger = LogManager.getLogger(LoginController.class);

	@Autowired
	UsersRepo usersrepo;

//	private EmailService emailService;

	Users user;

	private String email;

	private LoginService loginservice;

	@Autowired
	public LoginController(LoginService loginservice) {
		this.loginservice = loginservice;
	}

	@GetMapping("/easybuy/signin")
	public String showLoginpage() {
		return "signin";
	}

	@GetMapping("/easybuy/createaccount")
	public String createUser(Users user, Model model) {
		// method to create account
		try {
			loginservice.createUsers(user);
			logger.info("User registred Successfully");
			return "backtologin";
		} catch (IllegalArgumentException e) {
			// return "userexists";
			model.addAttribute("errorMessage", e.getMessage());
			logger.error(e);
			return "signup";
		}

	}

	@GetMapping("/easybuy/loginvalidation")
	// method to perform login validation
	public String login(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password, Model model, HttpSession session) {
		try {

			loginservice.loginValidation(username, password, session);

			return "welcome";
		}

		catch (IllegalArgumentException e) {
			model.addAttribute("errorMessage", e.getMessage());
			logger.error(e);
			return "signin"; // return the login page again if login fails
		}

	}

	@GetMapping("/easybuy/logout")
	public String logout(HttpSession session) {
		// method to perform logout validation
		session.invalidate();
		return "signin"; // redirect to the login page after logout
	}

	@GetMapping("/easybuy/emailverification")
	public String emailVerification() {
		return "emailverify";
	}

	@GetMapping("/easybuy/emailidverification")
	public String emailidVerification(@RequestParam(value = "emailid", required = true) String emailid, Model model,
			HttpSession session) {

		try {
			loginservice.emailVerification(emailid);
			// session.setAttribute("emailid", emailid);
			email = emailid;

			return "resetpassword";

		} catch (IllegalArgumentException e) {
			// return "userexists";
			model.addAttribute("errorMessage", e.getMessage());
			logger.error(e);
			return "emailverify";
		}
	}

	/*
	 * @GetMapping("/easybuy/resetpassword") public String
	 * resetPassword(@RequestParam(value = "password", required = true) String
	 * password,
	 * 
	 * @RequestParam(value = "confirmpassword", required = true) String
	 * confirmpassword, Model model, HttpSession session) { try {
	 * loginservice.checkPassword(password, confirmpassword);
	 * 
	 * //usersrepo.setPassword(password);
	 * 
	 * return "passwordupdate";
	 * 
	 * }catch (IllegalArgumentException e) { //return "userexists";
	 * model.addAttribute("errorMessage", e.getMessage()); return "resetpassword"; }
	 * }
	 */
	@PostMapping("/easybuy/resetpassword")
	public String resetPassword(@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "confirmpassword", required = true) String confirmpassword, Model model) {
		try {

			loginservice.checkPassword(password, confirmpassword, email);

			logger.info("Password updated successfully");
			return "passwordupdate";

		} catch (IllegalArgumentException e) {
			// return "userexists";
			model.addAttribute("errorMessage", e.getMessage());
			logger.error(e);
			return "resetpassword";
		}
	}
}
