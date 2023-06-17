package com.easybuy.app;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

import com.easybuy.app.ProductRepo;

@Controller
public class ProductController {

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

	@PostMapping("/easybuy/secure/saveproduct")
	public String saveProduct(Product product) {

		repo.save(product);
		return "thankyou";
	}

	@GetMapping("/easybuy/secure/listofproducts")
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