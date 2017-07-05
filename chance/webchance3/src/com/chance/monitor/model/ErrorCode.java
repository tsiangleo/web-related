package com.chance.monitor.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
public class ErrorCode {
	private int code;
	private String reason;
	public int getCode() {
		return code;
	}
	@XmlElement
	public void setCode(int code) {
		this.code = code;
	}
	public String getReason() {
		return reason;
	}
	@XmlElement
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}
