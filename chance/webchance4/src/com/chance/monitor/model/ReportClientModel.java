package com.chance.monitor.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ReportClientModel")
public class ReportClientModel {

	private Integer userId;
	private String 	userName;
	private Integer reportType;
	private String 	reportReason;
	private Integer byReportUserId;
	private String 	byReportUserName;
	private Long 	time;
	private List<ReportChatMsg> msgs;//举报时候附带的聊天信息
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
	public Integer getReportType() {
		return reportType;
	}
	public void setReportType(Integer reportType) {
		this.reportType = reportType;
	}
	public String getReportReason() {
		return reportReason;
	}
	public void setReportReason(String reportReason) {
		this.reportReason = reportReason;
	}
	public Integer getByReportUserId() {
		return byReportUserId;
	}
	public void setByReportUserId(Integer byReportUserId) {
		this.byReportUserId = byReportUserId;
	}
	public String getByReportUserName() {
		return byReportUserName;
	}
	public void setByReportUserName(String byReportUserName) {
		this.byReportUserName = byReportUserName;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public List<ReportChatMsg> getMsgs() {
		return msgs;
	}
	public void setMsgs(List<ReportChatMsg> msgs) {
		this.msgs = msgs;
	}
	

	
}
