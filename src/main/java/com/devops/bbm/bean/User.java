package com.devops.bbm.bean;

public class User {
	private String role;
	private String password;
	
	public User(String role, String password) {
		super();
		this.role = role;
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
