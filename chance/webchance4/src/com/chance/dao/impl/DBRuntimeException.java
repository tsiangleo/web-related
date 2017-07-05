package com.chance.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 数据库操作运行期异常
 *
 */
public class DBRuntimeException extends RuntimeException {

 
	private static final long serialVersionUID = -368646349014580765L;
	
	private static final Logger logger = LoggerFactory.getLogger(DBRuntimeException.class);
	
	public DBRuntimeException(String message){
		super(message);
	}
	public DBRuntimeException(Exception e,String sql) {
		
		super("数据库运行期异常");
		
		if(logger.isErrorEnabled()){
			logger.error("数据库运行期异常，相关sql语句为:"+ sql,e);
		}
	}
	
	
	public DBRuntimeException(String message,String sql) {
		
		super(message);
		if(logger.isErrorEnabled()){
			logger.error(message+"，相关sql语句为:"+ sql);
		 
		}
		
	}
	
	
}
