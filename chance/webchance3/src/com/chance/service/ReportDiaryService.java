package com.chance.service;
import java.util.List;
import java.util.Map;

import com.chance.domain.Admin;
import com.chance.domain.Pager;
import com.chance.domain.ReportDiary;
import com.chance.service.impl.BusinessException;
/**
 * ReportDiary的服务接口
 * @author michael
 *
 */
public interface ReportDiaryService {
//	public void deleteSingleReportDiaryAndRelatedDiary(Long deleteTime,
//			int reportDiaryid,int userid, int diaryid,int adminid) throws IOException;
//	/**
//	 * 按页查询
//	 * @param page
//	 * @return
//	 */
//	List<ReportDiaryVO> queryByPage(Page page);
//
//
//	/**
//	 * 按照主键修改ReportDiary的状态
//	 * @param id 本地数据库的主键
//	 * @param checkResult
//	 * @param checkAdminID
//	 * @return 返回受影响的记录数
//	 */
//	int updateById(Integer id, Integer checkResult, Integer checkAdminID);
//
//	/**
//	 * 保存多个ReportDiary
//	 * @param reportDiaryList
//	 */
//	void save(List<ReportDiaryVO> reportDiaryList);
//
//	/**
//	 * 批量修改ReportDiary的状态
//	 * @param checkList 本地数据库的主键集合
//	 * @param checkResult
//	 * @param checkAdminID
//	 * @return 返回受影响的记录数
//	 */
//	int updateByIds(List<Integer> checkList, Integer checkResult, Integer checkAdminID);
//
//	/**
//	 * 返回本地数据库中的ReportDiary记录的条数
//	 * @return
//	 */
//	int getRecordsLength();
//	
//	/**
//	 * 返回本地数据库中已处理的ReportDiary记录的条数
//	 * @return
//	 */
//	int getDealedRecordsLength();
//
//	/**
//	 *  批量获取ReportDiary记录
//	 * <br>从服务器获取数据，然后插入到本地数据库。
//	 * @return
//	 * @throws IOException
//	 */
//	List<ReportDiaryVO> getReportDiary() throws IOException;
//	
//	
//	/**
//	 * 获取所有日志的举报时间
//	 * @param containsDealed 为true则返回所有的被举报日志的举报时间（包括已经处理了的）；为false则返回未处理的。
//	 * @return
//	 */
//	List<Long> getReportTime(boolean containsDealed);
//	
//	/**
//	 * 批量获取本地已处理过的ReportDiary
//	 * @param page
//	 * @return
//	 */
//	List<ReportDiaryVO> getLocalDealedReportDiary(Page page);
	
	


	/**
	 * 按页获取已经处理过的ReportDiary
	 * @param pager
	 * @return
	 * @throws BusinessException
	 */
	Pager<ReportDiary> getCheckedByPager(Pager<ReportDiary> pager) throws BusinessException ;
	
	/**
	 * 按页获取待处理的ReportDiary
	 * @param pager pager中必须包含当前页码，如果pager中含有总的记录数更好，系统将不会再次去查找总的记录数。
	 * @return
	 * @throws BusinessException
	 */
	Pager<ReportDiary> getUncheckedByPager(Pager<ReportDiary> pager) throws BusinessException ;

	/**
	 * 检查服务器有无最新的ReportDiary，有则插入到本地数据库，并返回获取到的最新的数据条数；否则若没有最新的或者发生其他错误则抛出异常。
	 * @return
	 * @throws BusinessException
	 */
	Integer checkLatest() throws BusinessException ;

	/**
	 * 将指定id的ReportDiary标记为已处理状态
	 * @param idList ReportDiary在本地数据库中的主键
	 * @param admin 管理员
	 * @return
	 * @throws BusinessException
	 */
	void updateToCheckedStatus(List<Integer> idList, Admin admin) throws BusinessException ;
	
	/**
	 * 将指定id的ReportDiary标记为已处理状态
	 * @param id ReportDiary在本地数据库中的主键
	 * @param admin 管理员
	 * @return
	 * @throws BusinessException
	 */
	void updateToCheckedStatus(Integer id, Admin admin) throws BusinessException ;
	
	
	/**
	 * 删除一条ReportDiary。<br>
	 * 这个操作会产生两个动作：将本地的该条ReportDiary改为Checked状态，同时向服务器发送删除这条ReportDiary的消息。
	 * @param time 该条ReportDiary在服务器的ID
	 * @param id 该条ReportDiary在本地数据库的ID
	 * @param admin
	 * @throws BusinessException
	 */
	void delete(Long time,Integer id, Admin admin) throws BusinessException ;

	
	/**
	 * 同时删除一条ReportDiary和对应的Diary。
	 * @param deleteTime  该条ReportDiary在服务器的ID
	 * @param reportDiaryid 该条ReportDiary在本地数据库的ID
	 * @param userid 
	 * @param diaryid
	 * @param admin
	 * @throws BusinessException
	 */
	void deleteReportDiaryAndDiary(long deleteTime, int reportDiaryid,int userid, int diaryid, Admin admin) throws BusinessException ;

	/**
	 * 
	 * 统计1：篇日志的举报情况（收到举报的情况）
	 * 一篇日志由userid和diaryid标志。
	 * 所以应该先查询：哪个人被举报得最多（），
	 * 然后再查询该用户的哪些日志被举报得最多？
	 * 最后再查询这篇日志都有哪些用户举报（有可能同一个用户多次举报某个用户的某篇日志）
	 * 
	 * 步骤1：
	 * 哪个人被举报得最多
	 * SELECT C_ReportDiary_ByUserID,C_ReportDiary_ByUserName,COUNT(C_ReportDiary_ByUserID)AS frequency FROM c_reportdiary GROUP BY C_ReportDiary_ByUserID  ORDER BY frequency DESC
	 * 
	 * 步骤2：查询该用户的哪些日志被举报得最多
	 * SELECT C_ReportDiary_DiaryID,COUNT(C_ReportDiary_DiaryID)AS frequency FROM c_reportdiary WHERE C_ReportDiary_ByUserID = 272450  GROUP BY C_ReportDiary_DiaryID  ORDER BY frequency DESC
	 * 
	 * 步骤3：查询这篇日志都有哪些用户举报
	 * SELECT C_ReportDiary_UserID,COUNT(C_ReportDiary_UserID)AS frequency FROM c_reportdiary WHERE C_ReportDiary_ByUserID = 272450 AND C_ReportDiary_DiaryID = 102 GROUP BY C_ReportDiary_UserID  ORDER BY frequency DESC

	 * 
	 * 统计2：某个用户的举报情况 （发出举报的情况）
	 * 哪个人最爱举报别人？排行榜
	 * SELECT C_ReportDiary_UserID,C_ReportDiary_UserName,COUNT(C_ReportDiary_UserID)AS frequency FROM c_reportdiary GROUP BY C_ReportDiary_UserID  ORDER BY frequency DESC
	 * 
	 * 都举报了哪些用户？
	 * SELECT C_ReportDiary_ByUserID,C_ReportDiary_ByUserName , COUNT(C_ReportDiary_ByUserID) AS frequency FROM c_reportdiary WHERE C_ReportDiary_UserID = 272452 GROUP BY C_ReportDiary_ByUserID ORDER BY frequency DESC
	 * 
	 * 的哪些日志？
	 * SELECT C_ReportDiary_DiaryID,COUNT(C_ReportDiary_DiaryID) AS frequency FROM c_reportdiary WHERE C_ReportDiary_UserID = 272452 AND C_ReportDiary_ByUserID=272450 GROUP BY C_ReportDiary_DiaryID ORDER BY frequency DESC
	 * 
	 */
	
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

	/**
	 * 从本地数据库中删除一条已经处理过的ReportDiary。
	 * @param reportDiaryid
	 */
	void deleteChecked(int reportDiaryid)throws BusinessException ;
	
	
}
