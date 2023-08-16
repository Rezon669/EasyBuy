package com.easybuy.app.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easybuy.app.controller.LoginController;
import com.easybuy.app.repository.UsersRepo;

@Service
public class EmailVerification {

	private static final Logger logger = LogManager.getLogger(EmailVerification.class);
	@Autowired
	UsersRepo usersrepo;
	private EmailService emailService;

	// private EmailVerification emailVerification;

	@Autowired
	public EmailVerification(EmailService emailService) {
		this.emailService = emailService;
	}

	public void checkPasswords(String password, String confirmpassword, String email) {

		if (password.isEmpty() || confirmpassword.isEmpty()) {
			logger.warn("Please enter the Passwords");
			throw new IllegalArgumentException("Please enter the Passwords");
		} else if (!password.equals(confirmpassword)) {
			logger.warn("Passwords didn't match");
			throw new IllegalArgumentException("Passwords didn't match");
		}

		logger.info("Updating the password");
		usersrepo.updatePasswordByEmailid(password, email);
		logger.info("Password updated successfully");
		try {
			String subject = "EasyBuy: Account Password Updated";
			String fromEmail = "rezon449@gmail.com";
			String body = "Hi..." + " your EasyBuy account password has been updated"

					+ "Thank you";
			String toEmail = email;
			emailService.sendEmail(fromEmail, toEmail, subject);

		} catch (Exception e) {
			logger.error("Emaild is not verified in AWS SES to send Email");
			logger.error(e);
		}
	}

}
