package com.chance.monitor.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DiaryModel")
public class DiaryListResult {
	private int status;
	private String desc;
	private DiaryModelAndSize data;
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
	public DiaryModelAndSize getData() {
		return data;
	}
	public void setData(DiaryModelAndSize data) {
		this.data = data;
	}

	
}
