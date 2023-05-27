package com.easybuy.app;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class LoginController {

	@Autowired
	UsersRepo usersrepo;
	
	private LoginRepo loginrepo;
	 private final LoginService loginservice;
	

	 public LoginController(LoginService loginservice) {
	        this.loginservice = loginservice;
	    }


    

    @GetMapping("/easybuy/signin")
    public String showLoginpage() {
        return "signin"; // return the login page template name
    }

   // @RequestMapping(value = "/createaccount",method=RequestMethod.POST)
	/*@GetMapping("/createaccount")
	public String saveuser(Users user, HttpSession session) {
	
		 if (usersrepo.findByUsername(user.getUsername()) != null) {
	            throw new IllegalArgumentException("Username already exists");
	        }
		usersrepo.save(user);
		return "backtologin";
	}
	*/
    
    @GetMapping("/easybuy/createaccount")
    public String createUser( Users user,  HttpSession session,  Model model) {
        try {
            loginservice.createUser(user);
            return "backtologin";
        } catch (IllegalArgumentException e) {
            //return "userexists";
        	model.addAttribute("errorMessage", e.getMessage());
            return "signup";
        }
        
    }
    
    @GetMapping("/easybuy/loginvalidation")
    public String login(@RequestParam(value = "username", required = true) String username,
                        @RequestParam(value = "password", required = true) String password,
                        HttpSession session) {
        Users user = usersrepo.findByUsername(username);
            
            if (user != null && user.getPassword().equals(password)) {
                session.setAttribute("username", username);
                return "welcome"; // redirect to the dashboard page
            }
            return "signin"; // return the login page again if login fails
        }

        @GetMapping("/easybuy/logout")
        public String logout(HttpSession session) {
            session.invalidate();
            return "signin"; // redirect to the login page after logout
        }
    }
	 

