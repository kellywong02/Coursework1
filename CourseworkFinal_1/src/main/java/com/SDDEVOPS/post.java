package com.SDDEVOPS;

public class post {
	
	protected int post_id;
	protected String post_name;
	protected String post_message;
	protected String post_timestamp;


	
	public post() {
		super();
	}

	public post(int post_id, String post_name, String post_message, String post_timestamp) {
		super();
		this.post_id = post_id;
		this.post_name = post_name;
		this.post_message = post_message;
		this.post_timestamp = post_timestamp;

	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public String getPost_name() {
		return post_name;
	}

	public void setPost_name(String post_name) {
		this.post_name = post_name;
	}

	public String getPost_message() {
		return post_message;
	}

	public void setPost_message(String post_message) {
		this.post_message = post_message;
	}

	public String getPost_timestamp() {
		return post_timestamp;
	}

	public void setPost_timestamp(String post_timestamp) {
		this.post_timestamp = post_timestamp;
	}

}