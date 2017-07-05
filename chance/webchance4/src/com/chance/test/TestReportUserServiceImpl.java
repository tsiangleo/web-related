package com.chance.test;

import static org.junit.Assert.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chance.service.ReportUserService;
import com.chance.service.impl.BusinessException;

public class TestReportUserServiceImpl {

	private ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	private ReportUserService reportUserService = (ReportUserService) ctx.getBean("reportUserService");
	private static final Logger log = LoggerFactory.getLogger(TestReportUserServiceImpl.class);
	
	@Test
	public void test() {
		try {
			System.out.println(reportUserService.checkLatest());
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
	}

}
