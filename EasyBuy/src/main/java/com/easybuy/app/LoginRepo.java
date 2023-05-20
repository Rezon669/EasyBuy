package com.easybuy.app;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepo extends JpaRepository<Login, Long> {

	//Login findByUsername(String username);

	

	// Login getDetails(String mobilenumber, String password);
}
