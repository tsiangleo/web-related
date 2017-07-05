package com.chance.service.impl;

public class BusinessException extends Exception {

	private static final long serialVersionUID = -2974456223620178197L;
	private Integer errorCode; // 错误代码


	public BusinessException() {}
	
	public BusinessException(String msg) {
		super(msg);
	}
	
	public BusinessException( Integer errorCode,String msg) {
		super(msg);
		this.errorCode = errorCode;
	}

	
	public String getMessage() {
		return  errorCode == null ? super.getMessage() :"Detail Messgae:"+errorCode +" "+super.getMessage();
	}
		
		
	private Throwable cause;

	public BusinessException(String msg, Throwable ex) {
		super(msg);
		this.cause = ex;
	}
	


}
