package com.easybuy.app.entity;

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

	private int userid;
	private String username;
	private String emailid;
	private String mobilenumber;
	private String password;
	private String city;

	public Users() {
		// Default constructor implementation

	}

	public Users(String username, String password) {
		// Default constructor implementation
		this.username = username;
		this.password = password;
	}

	public Users(String username, String password, ArrayList arrayList) {
		// TODO Auto-generated constructor stub
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Users [userid=" + userid + ", username=" + username + ", emailid=" + emailid + ", mobilenumber="
				+ mobilenumber + ", password=" + password + ", city=" + city + "]";
	}

}
