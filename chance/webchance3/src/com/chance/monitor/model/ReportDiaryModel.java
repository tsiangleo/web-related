package com.chance.monitor.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ReportDiaryModel")
public class ReportDiaryModel {
	protected Integer 	userId;
	protected String 	userName;
	protected Integer 	diaryId;
	protected Integer 	authorId;
	protected String	authorName;
	protected Long 		time;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getDiaryId() {
		return diaryId;
	}
	public void setDiaryId(Integer diaryId) {
		this.diaryId = diaryId;
	}
	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	
	
	
}
