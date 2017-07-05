package com.chance.dao;


import java.util.Map;

import com.chance.domain.ReportDiary;


/**
 * 
 * @author michael
 *
 */
public interface ReportDiaryDao extends BaseDao<ReportDiary,Integer>{
	
	/**
	 * 被举报用户排行榜
	 * @return 返回一个map，key为被举报用户的id，value为被举报次数。
	 */
	Map<Integer,Integer> reporteeTopList();
	
	
	/**
	 * 查询某个用户的各篇日志被举报的次数。
	 * @param byUserId 被举报用户id
	 * @return 返回一个map，key为被举报日志的id，value为被举报次数。
	 */
	Map<Integer,Integer> diaryList(Integer byUserId);


	/**
	 * 查询某个用户的某篇日志都有哪些用户举报。
	 * @param byUserId 被举报用户id
	 * @param diaryId 被举报日志id
	 * @return  返回一个map，key为举报者的id，value为被举报次数。
	 */
	Map<Integer,Integer> reporterList(Integer byUserId,Integer diaryId);
	
	
	/**
	 * 举报者排行榜
	 * @return 返回一个map，key举报者id，value为该举报者举报他人日志的总次数。
	 */
	Map<Integer,Integer> reporterTopList();
	
	/**
	 * 查询某个用户都举报了哪些用户。
	 * @param UserId 举报者id
	 * @return 返回一个map，key为被举报用户的Id，value为被举报次数。
	 */
	Map<Integer,Integer> reporteeList(Integer UserId);

	/**
	 * 查询某个用户都举报了指定用户的哪些日志。
	 * @param UserId 举报者id
	 * @param byUserId 被举报用户id
	 * @return 返回一个map，key为被举报日志的Id，value为被举报次数。
	 */
	Map<Integer,Integer> diaryList(Integer UserId,Integer byUserId);
	

}
