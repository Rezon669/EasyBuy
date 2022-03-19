package com.easybuy.app;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Productrepo extends JpaRepository<Product, String>{

	//List findBy(String Category, String category);

	//List findBy(String category, String category2);

	 @Query("SELECT p FROM Product p WHERE p.searchkeyword LIKE %?1%")
	    public List<Product> search(String searchkeyword);
	
	
}
