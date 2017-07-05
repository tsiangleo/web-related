package com.chance.remote;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * 将调用服务器的这些看成unchecked异常，层次结构类似DAO
 * 
 * @author michael
 *
 */
public class RemoteDataAccessException extends RuntimeException{

	private static final long serialVersionUID = -2974456223620178197L;
	private Integer errorCode; // 错误代码

	public RemoteDataAccessException() {}
	
	public RemoteDataAccessException(String msg) {
		super(msg);
	}
	
	public RemoteDataAccessException( Integer errorCode,String msg) {
		super(msg);
		this.errorCode = errorCode;
	}

	
	public String getMessage() {
		return super.getMessage();
	}
		
		
	private Throwable cause;
//
//	public RemoteDataAccessException(String msg) {
//		super(msg);
//	}
//
	public RemoteDataAccessException(String msg, Throwable ex) {
		super(msg);
		this.cause = ex;
	}
//
//	public RemoteDataAccessException(int code, String msg) {
//		super(msg);
//		this.errorCode = code;
//	}
//	
//	public RemoteDataAccessException(int code, String msg, Throwable cause) {
//		super(msg, cause);
//		this.errorCode = code;
//		this.cause = cause;
//	}
//
////	public Throwable getCause() {
////		return (this.cause == null ? this : this.cause);
////	}
//
//	public String getMessage() {
//		String message;
//		if (errorCode != null)
//			message = "error code:" + errorCode + ",detail:" + super.getMessage();
//		else
//			message = super.getMessage();
//
//		Throwable cause = getCause();
//		if (cause != null) {
//			message = message + ";nested Exception is:" + cause;
//		}
//		return message;
//
//	}
//
//	public void printStackTrace(PrintStream ps) {
//		if (getCause() == null) {
//			super.printStackTrace(ps);
//
//		} else {
//			ps.println(this);
//			getCause().printStackTrace(ps);
//		}
//	}
//
//	public void printStackTrace(PrintWriter pw) {
//		if (getCause() == null) {
//			super.printStackTrace(pw);
//		} else {
//			pw.println(this);
//			getCause().printStackTrace(pw);
//		}
//	}
//
//	public void printStackTrace() {
//		printStackTrace(System.err);
//	}
//
//	public int getErrorCode() {
//		return errorCode;
//	}

}
