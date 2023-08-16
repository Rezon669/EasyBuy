package com.easybuy.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.easybuy.app.entity.Product;
import com.easybuy.app.entity.Users;
import com.easybuy.app.repository.ProductRepo;
import com.easybuy.app.service.EmailValidator;
import com.easybuy.app.serviceimpl.Regex;

@Controller
public class ProductController {

	private static final Logger logger = LogManager.getLogger(ProductController.class);
	@Autowired
	ProductRepo repo;

	// private HttpServletRequest request;

	/*
	 * @RequestMapping("/") public String app() {
	 * 
	 * return "home"; }
	 * 
	 * @RequestMapping("/signin") public String signin() {
	 * 
	 * return "signin"; }
	 * 
	 * @RequestMapping("/signup") public String signup() {
	 * 
	 * return "signup"; }
	 */
	@GetMapping("/easybuy/secure/addproduct")
	public String addProduct(HttpSession ses) {

		return "product";
	}

	@RequestMapping("/easybuy/secure/app")
	public String home(HttpSession session) {

		return "App";
	}

	/*
	 * @GetMapping("/easybuy/secure/saveproduct") public String saveProduct(Product
	 * product) {
	 * 
	 * repo.save(product); return "thankyou"; }
	 */
	

	// Check if the username already exists
	public ResponseEntity<String> addProduct(Product product){
	if (product.getName().isEmpty() 
			|| product.getCategory().isEmpty()
			|| product.getSearchkeyword().isEmpty() ) {
		logger.warn("All the fields are mandataroy fields");
		throw new IllegalArgumentException("All the fields are mandataroy fields");
	} /*else if (usersrepo.findByUsername(user.getUsername()) != null) {
		logger.warn("Username already exists");
		throw new IllegalArgumentException("Username already exists");
	} else if (usersrepo.findByEmailid(user.getEmailid()) != null) {
		logger.warn("Email id already exists");
		throw new IllegalArgumentException("Email id already exists");
	}*/

		
		repo.save(product);
		return null;
	}	

	/*
	 * String subject = "EasyBuy: Welcome Email";
	 * 
	 * String body= "Hi..." + user.getUsername() + "\n" +
	 * "This is the mail form the EasyBuy.\n" +
	 * "Your EasyBuy account has been successfully created\n" +
	 * "Kindly reach out to us if any information required.\n" + "\n" + "Thank you";
	 * String toEmail = user.getEmailid(); emailService.sendEmail( toEmail, subject,
	 * body); } }catch(Exception e) { logger.error(e); throw new
	 * IllegalArgumentException("Invalid Email ID"); } return null;
	 * 
	 * }
	 */	@GetMapping("/easybuy/secure/listofproducts")
	public ModelAndView listofProducts(HttpSession session) {

		ModelAndView mv = new ModelAndView("listofproducts");
		List<Product> list = repo.findAll();
		mv.addObject("list", list);
		mv.setViewName("listofproducts");
		return mv;

	}

	@GetMapping("/easybuy/secure/searchproduct")
	public String searchProduct(HttpSession session) {

		return "searchproduct";

	}

	/*
	 * @GetMapping("/searchitems?category=?") public ModelAndView
	 * searchitem(@PathVariable String category ) { ModelAndView mv = new
	 * ModelAndView("listofitem"); List list = repo.listAll(category);//("Additem",
	 * category).get(); mv.addObject("list", list); mv.setViewName("listofitems");
	 * return mv; //return "searchitem"; }
	 */
	@GetMapping("/easybuy/secure/searchproducts")
	public String searchProducts(Model model, @Param("searchkeyword") String searchkeyword, HttpSession session) {
		List<Product> list = repo.search(searchkeyword);
		model.addAttribute("list", list);
		// model.addAttribute("keyword", searchkeyword);

		return "listofproducts";
	}
}