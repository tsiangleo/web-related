package com.chance.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chance.dao.ReportUserDao;
import com.chance.domain.ReportChatMsg;
import com.chance.domain.ReportUser;

public class JdbcReportUserTest {

	private ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

	private ReportUserDao  reportUserDao = (ReportUserDao) ctx.getBean("reportUserDao");

//	@Test
//	public void testSave(){
//		ReportUser entity = new ReportUser();
//		entity.setByUserId(123);
//		entity.setByUserName("byUserName");
//		entity.setCheckAdminId(2);
//		entity.setCheckResult(1);
//		entity.setCheckTime(System.currentTimeMillis());
//		entity.setReason("reason");
//		entity.setTime(System.currentTimeMillis());
//		entity.setType(1);
//		entity.setUserId(201223);
//		entity.setUserName("userName");
//		
//		List<ReportChatMsg> msgs = new ArrayList<ReportChatMsg>();
//		ReportChatMsg m1 = new ReportChatMsg();
//		m1.setMsgContent("msgContent");
//		m1.setReceiveCID(123123);
//		m1.setSendCID(34534);
//		m1.setSendTime(System.currentTimeMillis());
//		m1.setType(1);
//		msgs.add(m1);
//		
//		ReportChatMsg m2 = new ReportChatMsg();
//		m2.setMsgContent("msgContent2");
//		m2.setReceiveCID(1231232);
//		m2.setSendCID(345342);
//		m2.setSendTime(System.currentTimeMillis());
//		m2.setType(12);
//		msgs.add(m2);
//		
//		ReportChatMsg m3 = new ReportChatMsg();
//		m3.setMsgContent("msgContent3");
//		m3.setReceiveCID(1231233);
//		m3.setSendCID(345343);
//		m3.setSendTime(System.currentTimeMillis());
//		m3.setType(13);
//		msgs.add(m3);
//		
//		entity.setMsgs(msgs);
//		reportUserDao.save(entity);
//		
//	}
	
	@Test
	public void testSave(){
		System.out.println(reportUserDao.get(53, false));
	}
}
