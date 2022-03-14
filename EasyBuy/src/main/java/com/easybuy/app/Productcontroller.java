package com.easybuy.app;


import java.util.List;


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

import com.easybuy.app.Productrepo;

@Controller
public class Productcontroller {
	
	@Autowired
	Productrepo repo;
	
/*	@RequestMapping("/")
	public String app() {
		
	return "home";
}
	@RequestMapping("/signin")
	public String signin() {
		
	return "signin";
}
	@RequestMapping("/signup")
	public String signup() {
		
	return "signup";
}*/
	@RequestMapping("/addproduct")
	public String addproduct() {
		
	return "product";
}
	@RequestMapping("/app")
	public String home() {
		
	return "App";
}
	
	
	@PostMapping("/saveproduct")
	public String saveproduct(Product product) {
	
		repo.save(product);
		return "thankyou";
}
	@GetMapping("/listofproducts")
    public ModelAndView listofproducts() {
		
	    ModelAndView mv = new ModelAndView("listofproducts");
	   List list=repo.findAll();
				mv.addObject("list", list);
				mv.setViewName("listofproducts");
				return mv;		
		
	}
	@GetMapping("/searchproduct")
	public String searchitem() {
		
		return "searchproduct";
		
		
	}
/*	@GetMapping("/searchitems?category=?")
	public ModelAndView searchitem(@PathVariable String category ) {
		 ModelAndView mv = new ModelAndView("listofitem");	
	List list =	repo.listAll(category);//("Additem", category).get();
	mv.addObject("list", list);
	mv.setViewName("listofitems");
	return mv;	
		//return "searchitem";
	}
	  @GetMapping("/category")
	    public String viewHomePage(Model model, @Param("category") String category) {
	        List<Additem> list = repo.search(category);
	        model.addAttribute("list", list);
	        model.addAttribute("keyword", category);
	         
	        return "listofitems";
	    }*/
}