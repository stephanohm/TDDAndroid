package de.haw_hamburg.tddandroid.utils;

import java.io.Serializable;

public class User implements Serializable {
	
	private static final long serialVersionUID = 5503371230259818655L;
	private String email;
	private String userName;
	private String password;
	private String mobileOS;
	
	public User(String email, String username) {
		this.email = email;
		this.userName = username;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobileOS() {
		return mobileOS;
	}
	public void setMobileOS(String mobileOS) {
		this.mobileOS = mobileOS;
	}
	
	
}