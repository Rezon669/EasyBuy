package com.easybuy.app;

import java.util.ArrayList;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	UsersRepo usersrepo;

	 
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
	
	    public void createUser(Users user) {
	        // Check if the username already exists
	    	if (user.getUsername()== null || user.getPassword()==null) {
	    		 throw new IllegalArgumentException("Username & Password are mandataroy fields");
	    	}
	    	else if (usersrepo.findByUsername(user.getUsername()) != null) {
	            throw new IllegalArgumentException("Username already exists");
	        }
	        
	       
	        
	        // Save the user to the database
	        usersrepo.save(user);
	    }
	


}
