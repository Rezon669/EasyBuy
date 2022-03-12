package com.easybuy.app;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Users {  
	@Id
		@GeneratedValue
		
    private int uid;
	private String name;    
	private String mobilenummber;
	private String address;
	private String password; 
	 
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobilenummber() {
		return mobilenummber;
	}
	public void setMobilenummber(String mobilenummber) {
		this.mobilenummber = mobilenummber;
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
		return "Users [uid=" + uid + ", name=" + name + ", mobilenummber=" + mobilenummber + ", address=" + address
				+ ", password=" + password + "]";
	}
}
