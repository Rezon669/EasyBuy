package com.easybuy.app;

import javax.naming.NameNotFoundException;

public interface LoginService {

	Users loadUserByUsername(String username) throws NameNotFoundException;

	 void createUser(Users user);


}
