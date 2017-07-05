package com.chance.monitor.model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ResultInfo")
public class ResultInfo {
	private int status;
	private String desc;
	private Object data;
	
	public int getStatus() {
		return status;
	}
	@XmlElement
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDesc() {
		return desc;
	}
	@XmlElement
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Object getData() {
		return data;
	}
	@XmlElement
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "ResultInfo [status=" + status + ", desc=" + desc + ", data="
				+ data + "]";
	}
	
	
}


/*
public class ResultInfo<T> {
	private int status;
	private String desc;
	private List<T> data;	
	
	public int getStatus() {
		return status;
	}
	@XmlElement
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDesc() {
		return desc;
	}
	@XmlElement
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}	
	/**
	 * T可以使业务对象，也可以是Boolean，String等对象。
	public static void main(String[] args) {
		ResultInfo<Boolean> r = new ResultInfo<Boolean>();
		r.setDesc("success");
		r.setStatus(2);
		
		List<Boolean> data = new ArrayList<Boolean>();
		data.add(true);
		data.add(false);
		r.setData(data);
		System.out.println(r.getData());
		System.out.println(r);
	}
	
	
}

*/
