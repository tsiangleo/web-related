package com.chance.monitor.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ReportBangBangModel")
public class ReportBangBangModel {
	private int userId;
	private String userName;
	private int bangId;
	private int authorId;
	private String authorName;
	private long time;
	private String reason;
	
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
	public int getBangId() {
		return bangId;
	}
	@XmlElement
	public void setBangId(int bangId) {
		this.bangId = bangId;
	}
	public int getAuthorId() {
		return authorId;
	}
	@XmlElement
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public String getAuthorName() {
		return authorName;
	}
	@XmlElement
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public long getTime() {
		return time;
	}
	@XmlElement
	public void setTime(long time) {
		this.time = time;
	}
	public String getReason() {
		return reason;
	}
	@XmlElement
	public void setReason(String reason) {
		this.reason = reason;
	}
}
