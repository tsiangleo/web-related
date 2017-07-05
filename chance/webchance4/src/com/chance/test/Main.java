package com.chance.test;

import org.apache.commons.codec.digest.DigestUtils;
public class Main {

}

class DaoException extends RuntimeException{
	
	public DaoException(){}
	public DaoException(String msg) {
		super(msg);
	}
	public void get() throws DaoException{
		System.out.println("invoke daoexception.get()");
		throw new DaoException("数据库URL无法访问");
	}
}

class ServiceException extends Exception{
	
	public ServiceException(){}
	public ServiceException(String msg) {
		super(msg);
	}
	
	public void doit() throws ServiceException{
		System.out.println("invoke ServiceException.doit()");
		try {
			new DaoException().get();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
		
		
	}
}
