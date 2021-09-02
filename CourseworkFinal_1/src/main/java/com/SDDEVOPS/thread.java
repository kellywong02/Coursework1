package com.SDDEVOPS;

public class thread {
	
	protected int thread_id;
	protected String thread_name;
	protected String thread_message;
	protected String thread_timestamp;
	protected String thread_username;
	

	
	public thread() {
		super();
	}

	public thread(int thread_id, String thread_name, String thread_message, String thread_timestamp, String thread_username) {
		super();
		this.thread_id = thread_id;
		this.thread_name = thread_name;
		this.thread_message = thread_message;
		this.thread_timestamp = thread_timestamp;
		this.thread_username = thread_username;
		
		
		
	}

	public int getThread_id() {
		return thread_id;
	}

	public void setThread_id(int thread_id) {
		this.thread_id = thread_id;
	}

	public String getThread_name() {
		return thread_name;
	}

	public void setThread_name(String thread_name) {
		this.thread_name = thread_name;
	}

	public String getThread_message() {
		return thread_message;
	}

	public void setThread_message(String thread_message) {
		this.thread_message = thread_message;
	}

	public String getThread_timestamp() {
		return thread_timestamp;
	}

	public void setThread_timestamp(String thread_timestamp) {
		this.thread_timestamp = thread_timestamp;
	}

	public String getThread_username() {
		return thread_username;
	}

	public void setThread_username(String thread_username) {
		this.thread_username = thread_username;
	}
	
	
	

	
	
	
	
	
	

}
