package com.chance.monitor.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ReportDiaryModel")
public class ReportDiaryListResult {
	
	private int status;
	private String desc;
	private List<ReportDiaryModel> data;
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
	public List<ReportDiaryModel> getData() {
		return data;
	}
	public void setData(List<ReportDiaryModel> data) {
		this.data = data;
	}
	
	
}
