package com.chance.test;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chance.dao.impl.ConvertDao;

public class TT {

	private ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	private ConvertDao convertDao =  ctx.getBean(ConvertDao.class);
	
	@Test
	public void testConvert(){
		convertDao.convertReportUser();
	}
}
