package com.easybuy.app;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Additemrepo extends JpaRepository<Additem, String>{

	//List findBy(String Category, String category);

	//List findBy(String category, String category2);

	 @Query("SELECT a FROM Additem a WHERE a.category LIKE %?1%")
	    public List<Additem> search(String category);
	
	
}
