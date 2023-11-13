package com.easybuy.app.serviceimpl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.naming.NameNotFoundException;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.easybuy.app.entity.Users;
import com.easybuy.app.repository.UsersRepo;
import com.easybuy.app.service.EmailService;
import com.easybuy.app.service.EmailVerificationService;
import com.easybuy.app.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	private static final Logger logger = LogManager.getLogger(LoginServiceImpl.class);
//	private static final String EmailValidator = null;
	@Autowired
	UsersRepo usersrepo;

	

	EmailVerificationService emailVerificationService;
	private EmailService emailService;

	/*
	 * @Autowired public LoginServiceImpl(EmailVerificationService email) {
	 * this.emailVerificationService = email; }
	 */

	@Autowired
	public LoginServiceImpl(EmailService emailService) {
		this.emailService = emailService;
	}

	public Users loadUserByUsername(String username) throws NameNotFoundException {
		Users user = usersrepo.findByUsername(username);
		if (user == null) {
			logger.error("Username not found");
			throw new NameNotFoundException("User not found");
		}
		return new com.easybuy.app.entity.Users(user.getUsername(), user.getPassword(), new ArrayList<>());
	}

	public ResponseEntity<String> createUsers(Users user) {
		// Check if the username already exists
		if (user.getUsername().isEmpty() || user.getPassword().isEmpty() || user.getMobilenumber().isEmpty()
				|| user.getEmailid().isEmpty() || user.getCity().isEmpty()) {
			logger.warn("All the fields are mandataroy fields");
			throw new IllegalArgumentException("All the fields are mandataroy fields");
		} else if (usersrepo.findByUsername(user.getUsername()) != null) {
			logger.warn("Username already exists");
			throw new IllegalArgumentException("Username already exists");
		} else if (usersrepo.findByEmailid(user.getEmailid()) != null) {
			logger.warn("Email id already exists");
			throw new IllegalArgumentException("Email id already exists");
		}

		String emailid = user.getEmailid();
		String phno = user.getMobilenumber();

		boolean isValidEmail = Regex.isValidGmailAddress(emailid);

		if (isValidEmail == true) {
			boolean isValidPhno = Regex.isValidPhoneNumber(phno);
			if (isValidPhno == true) {

			} else {
				throw new IllegalArgumentException("Phone number is not Valid");
			}
		} else {
			throw new IllegalArgumentException("Email id is not Valid");
		}

		String username = user.getUsername();
		String password = user.getPassword();
		logger.info("Generating hash value for the provided data");
		
		String hashvalue = generateHashvalue(username, password);
		

		user.setPassword(hashvalue);

		usersrepo.save(user);
		
		logger.info("Sending welcome email to the given emailid");

		String subject = "EasyBuy: Welcome Email";

		String body = "Hi..." + user.getUsername() + "\n" + "This is the mail form the EasyBuy.\n"
				+ "Your EasyBuy account has been successfully created\n"
				+ "Kindly reach out to us if any information required.\n" + "\n" + "Thank you";
		String toEmail = user.getEmailid();
		try {
			emailService.sendEmail(toEmail, subject, body);
			
			logger.info("Successfully sent the welcome email to the given emailid");
			
		} catch (Exception e) {
			logger.info("Failed to send welcome email");
		}

		return null;
	}

	public void emailVerification(String emailid) {
		// TODO Auto-generated method stub
		// emailid==null || emailid==""
		if (emailid.isEmpty()) {
			logger.warn("Please enter the emaild");
			throw new IllegalArgumentException("Please enter the emaild");
		}
		Object result = usersrepo.findByEmailid(emailid);

		if (result == null) {
			logger.error("Given emaild is not found");
			throw new IllegalArgumentException("Given emaild is not found");
		}

	}

	@Override
	public void loginValidation(String username, String password, HttpSession session) {
		// TODO Auto-generated method stub
		if (username.isEmpty() || password.isEmpty()) {
			logger.warn("Please enter the Username & Password");
			throw new IllegalArgumentException("Please enter the Username & Password");
		}
		
		String hashvalue=generateHashvalue(username, password);
		
		Users user = usersrepo.findByUsername(username);
		if (user == null) {
			logger.error("Username not found");
			throw new IllegalArgumentException("Username not found");
		}

		else if (user != null && !user.getPassword().equals(hashvalue)) {

			logger.error("Autehtication failed");
			throw new IllegalArgumentException("Autehtication failed");
			// session.setAttribute("username", username);
		}

	}

	public void checkPassword(String password, String confirmpassword, String email) {
		// TODO Auto-generated method stub

		if (password.isEmpty() || confirmpassword.isEmpty()) {
			logger.warn("Please enter the Passwords");
			throw new IllegalArgumentException("Please enter the Passwords");
		} else if (!password.equals(confirmpassword)) {
			logger.warn("Passwords didn't match");
			throw new IllegalArgumentException("Passwords didn't match");
		}

		usersrepo.updatePasswordByEmailid(password, email);

		String subject = "EasyBuy: Account Password Updated";

		String body = "Hi...\n" + "\n" + "This is the mail form the Easybuy.\n"
				+ "Your EasyBuy account password has been updated\n"
				+ "Kindly reach out to us if any information required.\n" + "\n" + "Thank you";
		String toEmail = email;
		emailService.sendEmail(toEmail, subject, body);

	}

	public String generateHashvalue(String username, String password) {
		

		// Combine username and password
		String genratedHashvalue = username + password;

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(genratedHashvalue.getBytes(StandardCharsets.UTF_8));

			// Convert byte array to a hexadecimal representation
			StringBuilder hexStringBuilder = new StringBuilder();
			for (byte b : hash) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1) {
					hexStringBuilder.append('0');
				}
				hexStringBuilder.append(hex);
			}

			return hexStringBuilder.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
}
