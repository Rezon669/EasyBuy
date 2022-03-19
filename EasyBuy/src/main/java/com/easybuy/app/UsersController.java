package com.easybuy.app;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.easybuy.app.Usersrepo;
@Controller
public class UsersController {

	@Autowired
	Usersrepo urepo;
	private String a= "admin";
/*	
	@RequestMapping("/")
	public String app() {
		
	return "home";
}*/
	@RequestMapping("/")
	public String signin() {
		
	return "signin";
}
	@RequestMapping("/signup")
	public String signup() {
		
	return "signup";
}

	  @RequestMapping("/signinvalidation")
	    public String search(Model model, @Param("mobilenummber")  String mobilenummber, @Param("password") String password) {
		
	       String uid1 = urepo.finduser(mobilenummber,password);
	   
	    	if(uid1!=null && uid1.equals(a)) {
	    		
	    			
	    	return "App";
	    	
	    	}
	    	if (uid1!=null){
	    		
	    		return "searchproduct";
	    	}
	    	
	    	else {
	    		
	    		return "signin";
	    	}
	  }
	    
	
	    
	@PostMapping("/createaccount")
	public String saveuser(Users user) {
	
		urepo.save(user);
		return "backtologin";
	}

	    

}



