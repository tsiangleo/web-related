package com.chance.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.chance.domain.ReportBangBang;

@Repository("convertDao")
public class ConvertDao {
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	@Autowired
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	

	
	public void convertFeedBack(){
		
		System.out.println(" in convertFeedBack ...");
		String sql = "select C_FeedBack_ID,C_FeedBack_CheckTime from c_feedback where  C_FeedBack_CheckTime is  not  null order by C_FeedBack_ID desc";
		
		SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(sql);
		
		Map<Integer,String> result = new LinkedHashMap<Integer,String>();
		while(sqlRowSet.next()){
			result.put(sqlRowSet.getInt(1), sqlRowSet.getString(2));
		}
		
		for (Integer key : result.keySet()) {
			String valueString = result.get(key);
			System.out.println("id:"+key+",timestring:"+valueString);
			
			Long timestamp = this.String2Long(valueString);
			
			String updateSqlString = "update c_feedback set C_FeedBack_CheckTimeNew=? where C_FeedBack_ID = ?";
			this.jdbcTemplate.update(updateSqlString, timestamp,key);
		}
		 
	}
	
	public void convertReportDiary(){
		System.out.println(" in convertReportDiary ...");
		
		String sql = "select C_ReportDiary_ID,C_ReportDiary_CheckTime from c_reportdiary where  C_ReportDiary_CheckTime is  not  null order by C_ReportDiary_ID desc";
		
		SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(sql);
		
		Map<Integer,String> result = new LinkedHashMap<Integer,String>();
		while(sqlRowSet.next()){
			result.put(sqlRowSet.getInt(1), sqlRowSet.getString(2));
		}
		
		for (Integer key : result.keySet()) {
			String valueString = result.get(key);
			System.out.println("id:"+key+",timestring:"+valueString);
			
			Long timestamp = this.String2Long(valueString);
			
			String updateSqlString = "update c_reportdiary set C_ReportDiary_CheckTimeNew=? where C_ReportDiary_ID = ?";
			this.jdbcTemplate.update(updateSqlString, timestamp,key);
		}
		
	}
	
	public void convertReportBangBang(){
		System.out.println(" in convertReportBangBang ...");
		
		String sql = "select C_ReportBangBang_ID,C_ReportBangBang_CheckTime from c_reportbangbang where  C_ReportBangBang_CheckTime is  not  null order by C_ReportBangBang_ID desc";
		
		SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(sql);
		
		Map<Integer,String> result = new LinkedHashMap<Integer,String>();
		while(sqlRowSet.next()){
			result.put(sqlRowSet.getInt(1), sqlRowSet.getString(2));
		}
		
		for (Integer key : result.keySet()) {
			String valueString = result.get(key);
			System.out.println("id:"+key+",timestring:"+valueString);
			
			Long timestamp = this.String2Long(valueString);
			
			String updateSqlString = "update c_reportbangbang set C_ReportBangBang_CheckTimeNew=? where C_ReportBangBang_ID = ?";
			this.jdbcTemplate.update(updateSqlString, timestamp,key);
		}
		
	}
	public void convertReportUser(){
		System.out.println(" in convertReportUser ...");
		
		String sql = "select C_ReportUser_ID,C_ReportUser_CheckTime from c_reportuser where  C_ReportUser_CheckTime is  not  null order by C_ReportUser_ID desc";
		
		SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(sql);
		
		Map<Integer,String> result = new LinkedHashMap<Integer,String>();
		while(sqlRowSet.next()){
			result.put(sqlRowSet.getInt(1), sqlRowSet.getString(2));
		}
		
		for (Integer key : result.keySet()) {
			String valueString = result.get(key);
			System.out.println("id:"+key+",timestring:"+valueString);
			
			Long timestamp = this.String2Long(valueString);
			
			String updateSqlString = "update c_reportuser set C_ReportUser_CheckTimeNew=? where C_ReportUser_ID = ?";
			this.jdbcTemplate.update(updateSqlString, timestamp,key);
		}
		
	}
	
	Long String2Long(String stringTime){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        Long timestamp = null;
		try {
			timestamp = sdf.parse(stringTime).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}  
     
        System.out.println("转换前String："+stringTime);
        System.out.println("转换后Long："+timestamp);
        System.out.println("转换后String："+sdf.format(new Date(timestamp)));
        
        return timestamp;
	}
}
