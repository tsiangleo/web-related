package com.chance.monitor.model;

import java.util.List;

public class BangBangListResult {
	private int status;
	private String desc;
	private List<BangBangModel> data;
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
	public List<BangBangModel> getData() {
		return data;
	}
	public void setData(List<BangBangModel> data) {
		this.data = data;
	}
}
