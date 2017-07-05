package com.chance.monitor.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "Number")
public class Number {
	private long number;
	private boolean isSuccess;

	public long getNumber() {
		return number;
	}
	@XmlElement
	public void setNumber(long number) {
		this.number = number;
	}
	public boolean isSuccess() {
		return isSuccess;
	}
	@XmlElement
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	
}
