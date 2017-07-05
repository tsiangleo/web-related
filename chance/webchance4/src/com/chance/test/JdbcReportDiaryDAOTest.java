package com.chance.test;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;




import java.util.Map;

import javax.swing.event.ListSelectionEvent;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.chance.dao.BaseDao;
import com.chance.dao.ReportDiaryDao;
import com.chance.dao.impl.ResultNotUniqueException;
import com.chance.domain.Pager;
import com.chance.domain.ReportDiary;

public class JdbcReportDiaryDAOTest {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

	private BaseDao  baseDao = (BaseDao) ctx.getBean("reportDiaryDao");

	@Test
	public void testjdbcBookDAO(){
		
//		//insert List
//		ReportDiary entity = new ReportDiary();
//		entity.setByUserId(123);
//		entity.setByUserName("hha");
//		entity.setCheckAdminId(123);
//		entity.setCheckResult(0);
//		entity.setCheckTime(System.currentTimeMillis());
//		entity.setDiaryId(99);
//		entity.setTime(System.currentTimeMillis());
//		entity.setUserId(987);
//		entity.setUserName("jck");
//		ReportDiary entity2 = new ReportDiary();
//		entity2.setByUserId(2123);
//		entity2.setByUserName("2hha");
//		entity2.setCheckAdminId(2123);
//		entity2.setCheckResult(20);
//		entity2.setCheckTime(System.currentTimeMillis());
//		entity2.setDiaryId(299);
//		entity2.setTime(System.currentTimeMillis());
//		entity2.setUserId(2987);
//		entity2.setUserName("2jck");
//		List entityList = new ArrayList<>();
//		entityList.add(entity);
//		entityList.add(entity2);
//		baseDao.save(entityList);
		
		
		//update
//		ReportDiary updatedentity = new ReportDiary();
//		updatedentity.setId(2);
//		updatedentity.setByUserId(999123);
//		updatedentity.setByUserName("pppphha");
//		updatedentity.setCheckAdminId(999123);
//		updatedentity.setCheckResult(99990);
//		updatedentity.setCheckTime(System.currentTimeMillis());
//		updatedentity.setDiaryId(9999999);
//		updatedentity.setTime(System.currentTimeMillis());
//		updatedentity.setUserId(9999987);
//		updatedentity.setUserName("pppppjck");
//		baseDao.update(updatedentity);

		//update
//		Map<String, Object> property = new HashMap<String, Object>();
//		property.put("time", 88888888);
//		property.put("checkAdminId", 123);
//		property.put("checkTime", 888888881);
//		property.put("checkResult", 0);
//		
//		Map<String,Object> where =new HashMap<>();
//		where.put("id", 1);
//		baseDao.update(property, where);
		
		//getbypager & get[ids]
//		Pager pager = new Pager();
//		pager.setPropertyName("checkResult");
//		pager.setPropertyValue(1);
//		pager.setOrderBy("time");
//		Map<String, Object> queries = new HashMap<String, Object>();
//		queries.put("userId", 271436);
//		pager.setQueries(queries);
//		pager = baseDao.getByPager(pager);
//		System.out.println(pager);
//		System.out.println(pager.getTotalCount());
//		System.out.println(pager.getDataList());
//		System.out.println(pager.getPageCount());
		
//		try {
//			ReportDiary reportDiary = (ReportDiary) baseDao.get("userName", "KFC");
//			
//			if(reportDiary != null){
//				System.out.println(reportDiary.getByUserId());
//			}
//		} catch (ResultNotUniqueException  e) {
//			System.out.println("service采取另外的措施处理数据不唯一");
//		}
		
		
		//delete[ids]
//		baseDao.delete(new Integer[]{2,3});
		
	}
	
}
