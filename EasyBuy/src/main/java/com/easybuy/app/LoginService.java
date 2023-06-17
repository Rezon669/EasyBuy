package com.easybuy.app;

import javax.naming.NameNotFoundException;
import javax.servlet.http.HttpSession;

public interface LoginService {

	Users loadUserByUsername(String username) throws NameNotFoundException;

	void createUsers(Users user);

	void emailVerification(String emailid);

	void checkPassword(String password, String confirmpassword);

	void loginValidation(String username, String password, HttpSession session);

}
