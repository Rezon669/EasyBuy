package com.easybuy.app.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Entity
@Component

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
	private String role;

	public Users() {

	}

	public Users(int userid, String username, String emailid, String mobilenumber, String password, String city,
			String role) {
		super();
		this.userid = userid;
		this.username = username;
		this.emailid = emailid;
		this.mobilenumber = mobilenumber;
		this.password = password;
		this.city = city;
		this.role = role;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Users [userid=" + userid + ", username=" + username + ", emailid=" + emailid + ", mobilenumber="
				+ mobilenumber + ", password=" + password + ", city=" + city + ", role=" + role + "]";
	}

	public boolean isEmpty() {
		return username == null || username.isEmpty() && emailid == null || emailid.isEmpty() && mobilenumber == null
				|| mobilenumber.isEmpty() && password == null || password.isEmpty() && city == null
				|| city.isEmpty() && role == null || role.isEmpty();
	}
}
