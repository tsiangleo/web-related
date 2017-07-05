package com.chance.monitor.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "FeedbackModel")
public class FeedbackModel {
	private int userId;
	private String userName;
	private long time;
	private String content;
	
	public int getUserId() {
		return userId;
	}
	@XmlElement
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	@XmlElement
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContent() {
		return content;
	}
	@XmlElement
	public void setContent(String content) {
		this.content = content;
	}
	public long getTime() {
		return time;
	}
	@XmlElement
	public void setTime(long time) {
		this.time = time;
	}
	
	
}
