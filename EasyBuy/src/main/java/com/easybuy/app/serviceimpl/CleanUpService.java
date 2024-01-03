package com.easybuy.app.serviceimpl;

import javax.annotation.PreDestroy;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class CleanUpService {
	private static final Logger logger = LogManager.getLogger(CleanUpService.class);
	
    @PreDestroy
    public void cleanUp() {
        // Perform clean-up tasks when the server is shutting down
        // For example, releasing resources, closing connections, etc.
    	//public String logout(HttpServletResponse response) {
    		// method to perform logout validation
    
    		Cookie jwtCookie = new Cookie("token", "");
    		jwtCookie.setMaxAge(0);
    		jwtCookie.setPath("/");
    		//HttpServletResponse response=response.addCookie(jwtCookie);
        logger.info("Performing clean-up before server shutdown...");
    }
}
