package com.chance.monitor.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ReportBangBangModel")
public class ReportBangBangListResult {
	private int status;
	private String desc;
	private List<ReportBangBangModel> data;
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
	public List<ReportBangBangModel> getData() {
		return data;
	}
	public void setData(List<ReportBangBangModel> data) {
		this.data = data;
	}
	
	
}
