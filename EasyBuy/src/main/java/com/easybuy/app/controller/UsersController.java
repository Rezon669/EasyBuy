package com.easybuy.app.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.easybuy.app.entity.Login;
import com.easybuy.app.entity.Users;
import com.easybuy.app.repository.UsersRepository;
import com.easybuy.app.security.JWTUtil;
import com.easybuy.app.service.UsersService;

@Controller
@RequestMapping("/easybuy/user")

public class UsersController {

	private static final Logger logger = LogManager.getLogger(UsersController.class);
	private static final String SECRET_KEY = "dfjhb356kbkbj236bkjb";

	public String jwttoken;
	@Autowired
	UsersRepository usersRepository;

	@Autowired
	private JWTUtil jwtUtil;

	/*
	 * @Autowired private AuthenticationManager authenticationManager;
	 * 
	 * @Autowired private PasswordEncoder passwordEncoder;
	 */

	@Autowired
	private UsersService usersService;

	@GetMapping("/signin")
	public String showLoginpage() {
		return "signin";
	}

	@PostMapping("/createaccount")
	public String createUser(Users user, Model model) {
		// method to create account

		try {

			usersService.createUsers(user);
			logger.info("User registred Successfully");

			// TokenStorage.setJwtToken(jwttoken);
			return "backtologin";

		} catch (IllegalArgumentException e) {

			model.addAttribute("errorMessage", e.getMessage());
			logger.error(e);
			// throw e;
			return "signup";

		}

	}

	@PostMapping("/loginvalidation")
	// method to perform login validation
	public String loginValidation(Login login, Model model, HttpServletResponse response) {
		try {
			jwttoken = usersService.loginValidation(login.getEmailid(), login.getPassword());

			if (jwttoken.isEmpty()) {

				model.addAttribute("errorMessage", "invalid Login Credentials");
				return "signin";

			}

			Cookie jwtcookie = new Cookie("token", jwttoken);

			// Set additional cookie attributes
			jwtcookie.setHttpOnly(true); // Makes the cookie accessible only by the server
			jwtcookie.setSecure(true); // Ensures the cookie is sent only over HTTPS
			jwtcookie.setPath("/"); // Restricts the cookie to the root path
			jwtcookie.setMaxAge(3600);

			response.addCookie(jwtcookie);
			return "welcome";

		} catch (IllegalArgumentException e) {

			model.addAttribute("errorMessage", e.getMessage());
			logger.error(e);
			return "signin";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpServletResponse response) {
		// method to perform logout validation
		Cookie jwtCookie = new Cookie("token", "");
		jwtCookie.setMaxAge(0);
		jwtCookie.setPath("/");
		response.addCookie(jwtCookie);
		return "signin"; // redirect to the signin page after logout
	}

	@GetMapping("/resetpassword")
	public String emailVerification() {
		return "resetpassword";
	}

	@PostMapping("/updatepassword")
	public String resetPassword(@RequestParam(value = "emailid", required = true) String emailid,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "confirmpassword", required = true) String confirmpassword, Model model) {
		try {

			// usersservice.emailVerification(emailid);
			usersService.updatePassword(password, confirmpassword, emailid);

			logger.info("Password updated successfully");
			return "passwordupdate";

		} catch (IllegalArgumentException e) {

			model.addAttribute("errorMessage", e.getMessage());
			logger.error(e);
			return "resetpassword";
		}
	}

	@RequestMapping("/signup")
	public String signup() {

		return "signup";
	}

	@GetMapping("/backtohome")
	public String backtoHome() {

		return "welcome";

	}

	/*
	 * @PostMapping("/authenticate") public String authenticateAndGetToken(Login
	 * authRequest) throws AuthenticationException {
	 * 
	 * String role = usersRepository.findEmailid(authRequest.getEmailid());
	 * 
	 * UsernamePasswordAuthenticationToken auth = new
	 * UsernamePasswordAuthenticationToken(authRequest.getEmailid(),
	 * authRequest.getPassword()); System.out.println(auth); Authentication
	 * authentication = authenticationManager.authenticate(auth);
	 * 
	 * if (authentication.isAuthenticated()) {
	 * 
	 * return jwtUtil.generateToken(authRequest.getEmailid(), role);
	 * 
	 * } else {
	 * 
	 * throw new UsernameNotFoundException("invalid user request !"); }
	 * 
	 * // return token;//Collections.singletonMap("jwt-token", token); }
	 * 
	 * @PostMapping("/register") public String registerHandler(Users user) { String
	 * encodedPass = passwordEncoder.encode(user.getPassword());
	 * user.setPassword(encodedPass); user = usersRepository.save(user); String
	 * token = jwtUtil.generateToken(user.getEmailid(), user.getRole()); // return
	 * Collections.singletonMap("jwt-token", token); return token; }
	 */

}
