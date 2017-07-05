package com.chance.dao.impl;

import java.io.PrintStream;
import java.io.PrintWriter;

public class DataAccessException extends RuntimeException{

	private static final long serialVersionUID = 6792326345586343814L;

	private Throwable cause;

	public DataAccessException(String msg) {
		super(msg);
	}

	public DataAccessException(String msg, Throwable ex) {
		super(msg);
		this.cause = ex;
	}

	public Throwable getCause() {
		return (this.cause == null ? this : this.cause);
	}

	public String getMessage() {
		String message = super.getMessage();
		Throwable cause = getCause();
		if (cause != null) {
			message = message + ";nested Exception is:" + cause;
		}
		return message;
	}

	public void printStackTrace(PrintStream ps) {
		if (getCause() == null) {
			super.printStackTrace(ps);

		} else {
			ps.println(this);
			getCause().printStackTrace(ps);
		}
	}

	public void printStackTrace(PrintWriter pw) {
		if (getCause() == null) {
			super.printStackTrace(pw);
		} else {
			pw.println(this);
			getCause().printStackTrace(pw);
		}
	}

	public void printStackTrace() {
		printStackTrace(System.err);
	}
	
}
