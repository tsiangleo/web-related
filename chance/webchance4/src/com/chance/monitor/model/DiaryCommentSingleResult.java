package com.chance.monitor.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DiaryCommentModel")
public class DiaryCommentSingleResult {
	
	private int status;
	private String desc;
	private DiaryCommentModel data;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public DiaryCommentModel getData() {
		return data;
	}
	public void setData(DiaryCommentModel data) {
		this.data = data;
	}
	
	
	
}
