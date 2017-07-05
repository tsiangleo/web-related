package com.chance.monitor.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "BriefInfoModel")
public class BriefInfoSingleResult {
	private int status;
	private String desc;
	private BriefInfoModel data;
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
	public BriefInfoModel getData() {
		return data;
	}
	public void setData(BriefInfoModel data) {
		this.data = data;
	}
	
	
}
