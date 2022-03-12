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

import com.easybuy.app.Additemrepo;

@Controller
public class Additemcontroller {
	
	@Autowired
	Additemrepo repo;
	
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
	@RequestMapping("/additem")
	public String additem() {
		
	return "additem";
}
	@RequestMapping("/app")
	public String home() {
		
	return "App";
}
	
	
	@PostMapping("/saveitem")
	public String saveitem(Additem item) {
	
		repo.save(item);
		return "thankyou";
}
	@GetMapping("/listofitems")
    public ModelAndView listofitems() {
		
	    ModelAndView mv = new ModelAndView("listofitem");
	   List list=repo.findAll();
				mv.addObject("list", list);
				mv.setViewName("listofitems");
				return mv;		
		
	}
	@GetMapping("/searchitem")
	public String searchitem() {
		
		return "searchitem";
		
		
	}
/*	@GetMapping("/searchitems?category=?")
	public ModelAndView searchitem(@PathVariable String category ) {
		 ModelAndView mv = new ModelAndView("listofitem");	
	List list =	repo.listAll(category);//("Additem", category).get();
	mv.addObject("list", list);
	mv.setViewName("listofitems");
	return mv;	
		//return "searchitem";
	}*/
	  @GetMapping("/category")
	    public String viewHomePage(Model model, @Param("category") String category) {
	        List<Additem> list = repo.search(category);
	        model.addAttribute("list", list);
	        model.addAttribute("keyword", category);
	         
	        return "listofitems";
	    }
}