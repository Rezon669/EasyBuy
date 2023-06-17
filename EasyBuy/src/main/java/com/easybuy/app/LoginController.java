package com.easybuy.app;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.cj.Session;

@Controller
@Transactional

public class LoginController {

	@Autowired
	UsersRepo usersrepo;

	Users user;

	String email;

	private final LoginService loginservice;

	public LoginController(LoginService loginservice) {
		this.loginservice = loginservice;
	}

	@GetMapping("/easybuy/signin")
	public String showLoginpage() {
		// method to navigate signin page
		return "signin";
	}

	@GetMapping("/easybuy/createaccount")
	public String createUser(Users user, Model model) {
		// method to create account
		try {
			loginservice.createUsers(user);
			return "backtologin";
		} catch (IllegalArgumentException e) {
			// return "userexists";
			model.addAttribute("errorMessage", e.getMessage());
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
	@GetMapping("/easybuy/resetpassword")
	public String resetPassword(@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "confirmpassword", required = true) String confirmpassword, Model model) {
		try {
			loginservice.checkPassword(password, confirmpassword);

			usersrepo.updatePasswordByEmailid(password, email);

			// usersrepo.setPassword(password);

			return "passwordupdate";

		} catch (IllegalArgumentException e) {
			// return "userexists";
			model.addAttribute("errorMessage", e.getMessage());
			return "resetpassword";
		}
	}
}
