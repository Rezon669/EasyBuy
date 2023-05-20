package com.easybuy.app;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "login")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

	

	public Login(String username, String password, ArrayList arrayList) {
		// TODO Auto-generated constructor stub
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}


    // Constructors, getters, and setters
}
