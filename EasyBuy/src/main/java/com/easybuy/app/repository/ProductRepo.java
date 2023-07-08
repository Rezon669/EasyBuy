package com.easybuy.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.easybuy.app.entity.Product;

public interface ProductRepo extends JpaRepository<Product, String> {

	// List findBy(String Category, String category);

	// List findBy(String category, String category2);

	@Query("SELECT p FROM Product p WHERE p.searchkeyword LIKE %?1%")
	public List<Product> search(String searchkeyword);

}
