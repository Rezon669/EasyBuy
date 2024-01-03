package com.easybuy.app.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.easybuy.app.entity.Product;
import com.easybuy.app.repository.ProductRepository;

@Controller
@RequestMapping("/easybuy/product")
public class ProductController {

	private static final Logger logger = LogManager.getLogger(ProductController.class);
	@Autowired
	ProductRepository productRepository;

	@GetMapping("/secure/addproducts")
	public String addProduct() {

		return "product";
	}

	@RequestMapping("/easybuy/app")
	public String home() {

		return "App";
	}

	@PostMapping("/secure/addproduct")
	// @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> addProduct(Product product) {
		if (product.getProductname().isEmpty() || product.getCategory().isEmpty()
				|| product.getSearchkeyword().isEmpty() || product.getPrice() <= 0) {
			logger.warn("All the fields are mandatory fields");
			throw new IllegalArgumentException("All the fields are mandatory fields");
		}
		// String token = tokenManager.getToken();
		productRepository.save(product);
		return ResponseEntity.ok("Product Details added");
	}

	@GetMapping("/listofproducts")
	// @PreAuthorize("hasRole('ADMIN')")
	public ModelAndView allProducts() {

		ModelAndView mv = new ModelAndView("listofproducts");
		List<Product> list = productRepository.findAll();
		mv.addObject("list", list);
		mv.setViewName("listofproducts");
		return mv;

	}

	@GetMapping("/searchproduct")
	public String searchProduct() {

		return "searchproduct";

	}

	/*
	 * @GetMapping("/searchitems?category=?") public ModelAndView
	 * searchitem(@PathVariable String category ) { ModelAndView mv = new
	 * ModelAndView("listofitem"); List list = repo.listAll(category);//("Additem",
	 * category).get(); mv.addObject("list", list); mv.setViewName("listofitems");
	 * return mv; //return "searchitem"; }
	 */

	@GetMapping("/searchproducts")
	public String searchProducts(Model model, @Param("searchkeyword") String searchkeyword) {
		List<Product> list = productRepository.search(searchkeyword);
		model.addAttribute("list", list);
		// model.addAttribute("keyword", searchkeyword);

		return "listofproducts";
	}
}