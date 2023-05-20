package com.easybuy.app;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class LoginController {

	@Autowired
	Usersrepo usersrepo;
	
	private LoginRepo loginrepo;
	


    

    @GetMapping("/signin")
    public String showLoginPage() {
        return "signin"; // return the login page template name
    }

    @GetMapping("/loginvalidation")
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

        @GetMapping("/logout")
        public String logout(HttpSession session) {
            session.invalidate();
            return "signin"; // redirect to the login page after logout
        }
    }
	 

