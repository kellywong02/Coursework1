package com.SDDEVOPS;

public class topic {
	
	protected int topic_id;
	protected String topic_name;
	protected String topic_message;
	protected String topic_created;
	
	public topic() {
		super();
	}

	public topic(int topic_id, String topic_name, String topic_message, String topic_created) {
		super();
		this.topic_id = topic_id;
		this.topic_name = topic_name;
		this.topic_message = topic_message;
		this.topic_created = topic_created;
	}

	public int getTopic_id() {
		return topic_id;
	}

	public void setTopic_id(int topic_id) {
		this.topic_id = topic_id;
	}

	public String getTopic_name() {
		return topic_name;
	}

	public void setTopic_name(String topic_name) {
		this.topic_name = topic_name;
	}

	public String getTopic_message() {
		return topic_message;
	}

	public void setTopic_message(String topic_message) {
		this.topic_message = topic_message;
	}

	public String getTopic_created() {
		return topic_created;
	}

	public void setTopic_created(String topic_created) {
		this.topic_created = topic_created;
	}
	
	
	
	

}
