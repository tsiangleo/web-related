package com.chance.monitor.model;

import java.util.List;

public class ShopInfoPendingListResult {
	private int status;
	private String desc;
	private List<ShopInfoPendingModel> data;
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
	public List<ShopInfoPendingModel> getData() {
		return data;
	}
	public void setData(List<ShopInfoPendingModel> data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "ShopInfoPendingResult [status=" + status + ", desc=" + desc
				+ ", data=" + data + "]";
	}
	
	
}
