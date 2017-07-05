package com.chance.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResultNotUniqueException extends DBRuntimeException{


	private static final long serialVersionUID = -368646349014580765L;
	
	private static final Logger logger = LoggerFactory.getLogger(ResultNotUniqueException.class);
	
	
	public ResultNotUniqueException(String sql) {
		
		super("数据异常：找到了不止一条记录");
		if(logger.isErrorEnabled()){
			logger.error("数据异常：找到了不止一条记录，相关sql语句为:"+ sql);
		}
	}
	
	
	public ResultNotUniqueException(String message,String sql) {
		
		super(message);
		if(logger.isErrorEnabled()){
			logger.error(message+"，相关sql语句为:"+ sql);
		 
		}
		
	}
}
