package com.chance.monitor.model;

import javax.xml.bind.annotation.XmlElement;

public class BangBangModel {
    private int bangbangId;
    private int authorId;
    private String name;
    private String content;
    private String url;
    private long startTime;
    private long endTime;
    private boolean isActive;
    
	public int getBangbangId() {
		return bangbangId;
	}
	@XmlElement
	public void setBangbangId(int bangbangId) {
		this.bangbangId = bangbangId;
	}
	public int getAuthorId() {
		return authorId;
	}
	@XmlElement
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public String getName() {
		return name;
	}
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	@XmlElement
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	@XmlElement
	public void setUrl(String url) {
		this.url = url;
	}
	public long getStartTime() {
		return startTime;
	}
	@XmlElement
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	@XmlElement
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public boolean isActive() {
		return isActive;
	}
	@XmlElement
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
}
