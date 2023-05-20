package com.easybuy.app;

import java.util.ArrayList;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	Usersrepo usersrepo;

	 
	    public  Users loadUserByUsername(String username) throws NameNotFoundException {
	        Users user = usersrepo.findByUsername(username);
	        if (user == null) {
	            throw new NameNotFoundException("User not found");
	        }
	        return new com.easybuy.app.Users(
	                user.getUsername(),
	                user.getPassword(),
	                new ArrayList<>());
	    }
	
		
	


}