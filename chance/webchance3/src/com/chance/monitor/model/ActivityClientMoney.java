package com.chance.monitor.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ActivityClientMoney")
public class ActivityClientMoney {
	private int userId;
	private int activityId;
	private int hasNotGet;
	private int hasGet;
	private int hasSend;
	
	public int getUserId() {
		return userId;
	}
	@XmlElement
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getActivityId() {
		return activityId;
	}
	@XmlElement
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public int getHasNotGet() {
		return hasNotGet;
	}
	@XmlElement
	public void setHasNotGet(int hasNotGet) {
		this.hasNotGet = hasNotGet;
	}
	public int getHasGet() {
		return hasGet;
	}
	@XmlElement
	public void setHasGet(int hasGet) {
		this.hasGet = hasGet;
	}
	public int getHasSend() {
		return hasSend;
	}
	@XmlElement
	public void setHasSend(int hasSend) {
		this.hasSend = hasSend;
	}
	
	

}
