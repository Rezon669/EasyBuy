package com.easybuy.app.serviceimpl;

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
import com.easybuy.app.service.EmailValidator;
import com.easybuy.app.service.EmailVerificationService;
import com.easybuy.app.service.LoginService;

import software.amazon.awssdk.services.ses.model.GetIdentityVerificationAttributesRequest;


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
		
		boolean isValidEmail= Regex.isValidGmailAddress(emailid);
	
		if(isValidEmail == true) {
			boolean isValidPhno= Regex.isValidPhoneNumber(phno);
			if(isValidPhno == true) {
				
			}else {
				throw new IllegalArgumentException("Phone number is not Valid");	
			}
		}else {
			throw new IllegalArgumentException("Email id is not Valid");
		}
		// Save the user to the database
		boolean emailverifiedstatus;
		try {
		
		//emailverifiedstatus = emailVerificationService.verifyEmail(emailid);
			emailverifiedstatus = EmailValidator.isValidEmail(emailid);
		logger.info("Verfication email is sent to Given emailid");
		//throw new IllegalArgumentException("One verfication email is sent to ypur given emaild");
	//	return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
if(emailverifiedstatus== true) {
			
			usersrepo.save(user);
			
			String subject = "EasyBuy: Welcome Email";
			
			String body=
					"Hi..." + user.getUsername() 
					+ "\n"
					+ "This is the mail form the EasyBuy.\n"
					+ "Your EasyBuy account has been successfully created\n" 
					+ "Kindly reach out to us if any information required.\n"
					+ "\n"
					+ "Thank you";
			String toEmail = user.getEmailid();
			emailService.sendEmail( toEmail,  subject,  body);
}
		}catch(Exception e) {
			logger.error(e);
			throw new IllegalArgumentException("Invalid Email ID");
		}
		return null;
	}
	
		//throw new Const("hfvisduhb");
		/*
		 * String subject = "EasyBuy: Welcome Email"; String fromEmail=
		 * "rezon449@gmail.com"; String body="Hi" + user.getUsername() +
		 * "Successfully  created the account for you"
		 * 
		 * + "Thank you"; String toEmail = user.getEmailid();
		 */
		/*
		 * try { if(emailverifiedstatus== true) { ///emailService.sendEmail( fromEmail,
		 * toEmail, subject, body); usersrepo.save(user); } }catch(Exception e){
		 * 
		 * throw new IllegalArgumentException("Invalid Email ID"); }
		 */
		//return null;
		
	
		/* return ResponseEntity.ok("User registered successfully!");
		}
		}catch(Exception e){
			
			throw new IllegalArgumentException("Invalid Email ID");
			}
		return null;	
		}*/
		
		//return null;
	

	/*
	 * @Override public void emailVerification(emailid) { // TODO Auto-generated
	 * method stub Object result = usersrepo.findByEmailid(emailid);
	 * 
	 * if(result == null) { throw new
	 * IllegalArgumentException("Given emaild is not found"); }
	 * 
	 * }
	 */

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
		Users user = usersrepo.findByUsername(username);
		if (user == null) {
			logger.error("Username not found");
			throw new IllegalArgumentException("Username not found");
		}

		else if (user != null && !user.getPassword().equals(password)) {

			logger.error("Wrong Password");
			throw new IllegalArgumentException("Wrong Password");
			//session.setAttribute("username", username);
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
	
	
 //  emailService.getEmailStatus(email);
				
				String subject = "EasyBuy: Account Password Updated";
				
				String body=
						"Hi...\n"
						+ "\n"
						+ "This is the mail form the Easybuy.\n"
						+ "Your EasyBuy account password has been updated\n" 
						+ "Kindly reach out to us if any information required.\n"
						+ "\n"
						+ "Thank you";
				String toEmail = email;
				emailService.sendEmail( toEmail,  subject,  body);
			
		}
	
		}



		

		

