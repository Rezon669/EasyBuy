package com.easybuy.app;

import java.util.ArrayList;

import javax.naming.NameNotFoundException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	UsersRepo usersrepo;

	public Users loadUserByUsername(String username) throws NameNotFoundException {
		Users user = usersrepo.findByUsername(username);
		if (user == null) {
			throw new NameNotFoundException("User not found");
		}
		return new com.easybuy.app.Users(user.getUsername(), user.getPassword(), new ArrayList<>());
	}

	public void createUsers(Users user) {
		// Check if the username already exists
		/*
		 * || user.getPassword()==null || user.getMobilenumber()==null ||
		 * user.getEmailid()==null || user.getCity()==null || user.getUsername()== "" ||
		 * user.getPassword()=="" || user.getMobilenumber()=="" || user.getEmailid()==""
		 * || user.getCity()==""
		 */
		if (user.getUsername().isEmpty() || user.getPassword().isEmpty() || user.getMobilenumber().isEmpty()
				|| user.getEmailid().isEmpty() || user.getCity().isEmpty()) {
			throw new IllegalArgumentException("All the fields are mandataroy fields");
		} else if (usersrepo.findByUsername(user.getUsername()) != null) {
			throw new IllegalArgumentException("Username already exists");
		} else if (usersrepo.findByEmailid(user.getEmailid()) != null) {
			throw new IllegalArgumentException("Email id already exists");
		}

		// Save the user to the database
		usersrepo.save(user);
	}

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
			throw new IllegalArgumentException("Please enter the emaild");
		}
		Object result = usersrepo.findByEmailid(emailid);

		if (result == null) {
			throw new IllegalArgumentException("Given emaild is not found");
		}

	}

	@Override
	public void checkPassword(String password, String confirmpassword) {
		// password== null || password=="" || confirmpassword== null ||
		// confirmpassword==""
		if (password.isEmpty() || confirmpassword.isEmpty()) {
			throw new IllegalArgumentException("Please enter the Passwords");
		} else if (!password.equals(confirmpassword)) {
			throw new IllegalArgumentException("Passwords didn't match");
		}

	}

	@Override
	public void loginValidation(String username, String password, HttpSession session) {
		// TODO Auto-generated method stub
		if (username.isEmpty() || password.isEmpty()) {
			throw new IllegalArgumentException("Please enter the Username & Password");
		}
		Users user = usersrepo.findByUsername(username);
		if (user == null) {
			throw new IllegalArgumentException("Username not found");
		}

		else if (user != null && user.getPassword().equals(password)) {

			session.setAttribute("username", username);
		}

	}
}
