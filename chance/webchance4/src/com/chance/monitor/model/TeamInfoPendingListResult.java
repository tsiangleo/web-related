package com.chance.monitor.model;

import java.util.List;

public class TeamInfoPendingListResult {
	private int status;
	private String desc;
	private List<TeamInfoPendingModel> data;
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
	public List<TeamInfoPendingModel> getData() {
		return data;
	}
	public void setData(List<TeamInfoPendingModel> data) {
		this.data = data;
	}
	
	
	
}
