package com.chance.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
import com.chance.remote.RemoteDataAccessException;
import com.chance.remote.RemoteDiaryHandler;
import com.chance.service.DiaryService;
import com.chance.service.ReportDiaryService;
import com.chance.service.impl.BusinessException;

public class TestDiaryServiceImpl {

	private static ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	private static DiaryService diaryService = (DiaryService) ctx.getBean("diaryService");
	private static final Logger log = LoggerFactory.getLogger(TestDiaryServiceImpl.class);
	
	/**
	 * 
	 * 由于只有一个DiaryServiceImpl对象，对应的成员变量idIndexBuffer也只有一份，所有idIndexBuffer是各个线程共享的。
	 * 每个线程访问第一页的时候都会重置idIndexBuffer的值。如线程A、B刚刚各自先后查询并访问了第一页，那么idIndexBuffer中的值就是B查询后的idIndexBuffer值，
	 * 线程A对应的idIndexBuffer取值就没了。此时线程A点击查看第二页，查看到的就是线程B中的第二页的值。
	 * 
	 * 该实例演示了上述断言。
	 */
//	public static void main(String[] args)throws BusinessException{
//		
//			
//			new Thread(new Runnable() {
//				
//				@Override
//				public void run() {
//					Pager<Diary> pager = new Pager<Diary>();
//					try {
//						System.out.println("----首页-----");
//						diaryService.getByPager(pager, "l");
//						System.out.println(pager);
//						
//						try {
//							Thread.sleep(1000);
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//						
//						System.out.println("----下一页-----");
//						pager.setCurrentPage(pager.getCurrentPage()+1);
//						diaryService.getByPager(pager, "l");
//						System.out.println(pager);
//					} catch (BusinessException e) {
//						e.printStackTrace();
//					}
//				}
//			},"thred-name-1").start();
//			
//		new Thread(new Runnable() {
//				
//				@Override
//				public void run() {
//					Pager<Diary> pager = new Pager<Diary>();
//					try {
//						System.out.println("----首页-----");
//						diaryService.getByPager(pager, "a");
//						System.out.println(pager);
//						
//						try {
//							Thread.sleep(1000);
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//						System.out.println("----下一页-----");
//						pager.setCurrentPage(pager.getCurrentPage()+1);
//						diaryService.getByPager(pager, "a");
//						System.out.println(pager);
//					} catch (BusinessException e) {
//						e.printStackTrace();
//					}
//				}
//			},"thred-name-2").start();
//		
//		}
		
	
	
	
	public static void test(String username) {
//		
//		try {
//			System.out.println(diaryService.get(111120, 300));;
//			Pager<Diary> pager = new Pager<Diary>();
//			diaryService.getByPager(pager, "l");
//		} catch (BusinessException e) {
//			System.out.println("【"+e.getMessage()+"】");
//		}
		
//		RemoteDiaryHandler remoteDiaryHandler = (RemoteDiaryHandler)ctx.getBean("remoteDiaryHandler");
//		try {
//			remoteDiaryHandler.GetDiary("l1111", 0, 5);
//			remoteDiaryHandler.deleteSingleDiary(123, 123);
//			remoteDiaryHandler.getSingleDiary(-12, -9);
//		} catch (RemoteDataAccessException e) {
//
//			System.out.println("【"+e.getMessage()+"】");
//		}
		
		
//		Pager<Diary> pager = new Pager<Diary>();
//		try {
//			
//			
//			System.out.println("----首页-----");
//			diaryService.getByPager(pager, username);
//			System.out.println(pager);
//			
//			System.out.println("----下一页-----");
//			pager.setCurrentPage(pager.getCurrentPage()+1);
//			diaryService.getByPager(pager, username);
//			System.out.println(pager);
//			
//			System.out.println("----再下一页-----");
//			pager.setCurrentPage(pager.getCurrentPage()+1);
//			diaryService.getByPager(pager, username);
//			System.out.println(pager);
//			
//			System.out.println("----上一页-----");
//			pager.setCurrentPage(pager.getCurrentPage()-1);
//			diaryService.getByPager(pager, username);
//			System.out.println(pager);
//			
//			System.out.println("----首页-----");
//			diaryService.getByPager(pager, username);
//			pager.setCurrentPage(1);
//			System.out.println(pager);
//			
//			System.out.println("----末页-----");
//			pager.setCurrentPage(pager.getPageCount());
//			diaryService.getByPager(pager, username);
//			System.out.println(pager);
//			
//			System.out.println("----跳转1-----");
//			pager.setCurrentPage(-20);
//			diaryService.getByPager(pager, username);
//			System.out.println(pager);
//			
//			System.out.println("----跳转2-----");
//			pager.setCurrentPage(120);
//			diaryService.getByPager(pager, username);
//			System.out.println(pager);
//			
//		} catch (BusinessException e) {
//			e.printStackTrace();
//		}
	}

}
