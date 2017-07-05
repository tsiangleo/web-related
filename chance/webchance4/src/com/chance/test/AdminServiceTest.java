package com.chance.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chance.dao.AdminDao;
import com.chance.dao.BaseDao;
import com.chance.domain.Admin;
import com.chance.service.AdminService;
import com.chance.service.impl.BusinessException;

public class AdminServiceTest {
	
	private ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

	private AdminService  adminService = (AdminService) ctx.getBean("adminService");

	@Test
	public void teseAdd(){
		Admin admin  = new Admin();
		admin.setLoginName("admin");
		admin.setPassword("admin");
		admin.setName("creator");
		admin.setLevel(1);	//超级管理员
		try {
			adminService.add(admin);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
}
