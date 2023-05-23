package com.easybuy.app;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users {  
	@Id
		@GeneratedValue
		
    private int uid;
	private String username;    
	
	private String mobilenumber;
	private String address;
	private String password; 
	
	public Users() {
	    // Default constructor implementation
		
	}
	public Users(String username, String password) {
	    // Default constructor implementation
		this.username= username;
		this.password= password;
	}
	 
	public Users(String username, String password, ArrayList arrayList) {
		// TODO Auto-generated constructor stub
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Users [uid=" + uid + ", username=" + username + ", mobilenumber=" + mobilenumber + ", address=" + address
				+ ", password=" + password + "]";
	}
}
