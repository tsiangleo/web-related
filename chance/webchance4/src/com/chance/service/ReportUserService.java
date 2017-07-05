package com.chance.service;

import java.util.List;

import com.chance.domain.Admin;
import com.chance.domain.Pager;
import com.chance.domain.ReportUser;
import com.chance.service.impl.BusinessException;

/**
 * ReportUser的服务接口
 * @author michael
 *
 */
public interface ReportUserService {
	/**
	 * 按页获取已经处理过的ReportUser
	 * @param pager
	 * @return
	 * @throws BusinessException
	 */
	Pager<ReportUser> getCheckedByPager(Pager<ReportUser> pager) throws BusinessException ;
	
	/**
	 * 按页获取待处理的ReportUser
	 * @param pager pager中必须包含当前页码，如果pager中含有总的记录数更好，系统将不会再次去查找总的记录数。
	 * @return
	 * @throws BusinessException
	 */
	Pager<ReportUser> getUncheckedByPager(Pager<ReportUser> pager) throws BusinessException ;

	/**
	 * 检查服务器有无最新的ReportUser，有则插入到本地数据库，并返回获取到的最新的数据条数；否则若没有最新的或者发生其他错误则抛出异常。
	 * @return
	 * @throws BusinessException
	 */
	Integer checkLatest() throws BusinessException ;

	/**
	 * 将指定id的ReportUser标记为已处理状态
	 * @param idList ReportUser在本地数据库中的主键
	 * @param admin 管理员
	 * @return
	 * @throws BusinessException
	 */
	void updateToCheckedStatus(List<Integer> idList, Admin admin) throws BusinessException ;
	
	/**
	 * 将指定id的ReportUser标记为已处理状态
	 * @param id ReportUser在本地数据库中的主键
	 * @param admin 管理员
	 * @return
	 * @throws BusinessException
	 */
	void updateToCheckedStatus(Integer id, Admin admin) throws BusinessException ;
	
	
	/**
	 * 删除一条ReportUser。<br>
	 * 这个操作会产生两个动作：将本地的该条ReportUser改为Checked状态，同时向服务器发送删除这条ReportUser的消息。
	 * @param time 该条ReportUser在服务器的ID
	 * @param id 该条ReportUser在本地数据库的ID
	 * @param admin
	 * @throws BusinessException
	 */
	void delete(Long time,Integer id, Admin admin) throws BusinessException ;

	/**
	 * 从本地数据库中删除一条已经处理过的ReportUser。
	 * @param reportUserid
	 */
	void deleteChecked(int reportUserid)throws BusinessException ;

	/**
	 * 查看一条ReportUser的详细信息，包括ReportChatMsg。
	 * @param id ReportUser在本地数据库中的主键
	 */
	ReportUser getDetail(int id)throws BusinessException ;
	
	
	
}
