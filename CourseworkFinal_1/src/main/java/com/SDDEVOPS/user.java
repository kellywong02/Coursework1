package com.SDDEVOPS;

public class user {
	protected int role_id;
	protected String role_name;
	
	public user() {
		super();
	}
	
	public user(int role_id, String role_name) {
		super();
		this.role_id = role_id;
		this.role_name = role_name;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
	
	
	
	
	

}
