package com.chance.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.chance.domain.Admin;
import com.chance.domain.Diary;
import com.chance.domain.Pager;
import com.chance.domain.ReportDiary;
import com.chance.remote.RemoteDiaryHandler;
import com.chance.service.ReportDiaryService;
import com.chance.service.impl.BusinessException;

public class TestReportDiaryServiceImpl {

	private ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	private ReportDiaryService reportDiaryService = (ReportDiaryService) ctx.getBean("reportDiaryService");
	private static final Logger log = LoggerFactory.getLogger(TestReportDiaryServiceImpl.class);
	@Test
	public void test() {
		
		System.out.println(reportDiaryService.reporteeTopList());
		
//		Pager<ReportDiary> pager = new Pager<ReportDiary>();
//		try {
//			
//			
//			System.out.println("----首页-----");
//			reportDiaryService.getCheckedByPager(pager);
//			System.out.println(pager);
//			
//			System.out.println("----下一页-----");
//			pager.setCurrentPage(pager.getCurrentPage()+1);
//			reportDiaryService.getCheckedByPager(pager);
//			System.out.println(pager);
//			
//			System.out.println("----再下一页-----");
//			pager.setCurrentPage(pager.getCurrentPage()+1);
//			reportDiaryService.getCheckedByPager(pager);
//			System.out.println(pager);
//			
//			System.out.println("----上一页-----");
//			pager.setCurrentPage(pager.getCurrentPage()-1);
//			reportDiaryService.getCheckedByPager(pager);
//			System.out.println(pager);
//			
//			System.out.println("----首页-----");
//			pager.setCurrentPage(1);
//			reportDiaryService.getCheckedByPager(pager);
//			System.out.println(pager);
//			
//			System.out.println("----末页-----");
//			pager.setCurrentPage(pager.getPageCount());
//			reportDiaryService.getCheckedByPager(pager);
//			System.out.println(pager);
//			
//			System.out.println("----跳转1-----");
//			pager.setCurrentPage(-20);
//			reportDiaryService.getCheckedByPager(pager);
//			System.out.println(pager);
//			
//			System.out.println("----跳转2-----");
//			pager.setCurrentPage(120);
//			reportDiaryService.getCheckedByPager(pager);
//			System.out.println(pager);
//			
//		} catch (BusinessException e) {
//			System.out.println("【"+e.getMessage()+"】");
//			System.out.println(pager);
//		}
		
		
		

		
//		try {
//			System.out.println(reportDiaryService.checkLatest());
//		} catch (BusinessException e) {
//			e.printStackTrace();
//		}
		
		
//		Pager<ReportDiary> pager = new Pager<ReportDiary>();
//		pager.setCurrentPage(2);
//		pager.setPageSize(3);
//		try {
//			System.out.println(reportDiaryService.getUncheckedByPager(pager));;
//		} catch (BusinessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		List<Integer> idList = new ArrayList<Integer>();
//		idList.add(88);
//		idList.add(89);
//		Admin admin = new Admin();
//		admin.setId(99999);
//		try {
//			reportDiaryService.updateToCheckedStatus(idList, admin);
//		} catch (BusinessException e) {
//			e.printStackTrace();
//		}
	}

}
