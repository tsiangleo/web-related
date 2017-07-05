package com.chance.service;

import java.util.List;

import com.chance.domain.Admin;
import com.chance.domain.Pager;
import com.chance.domain.FeedBack;
import com.chance.service.impl.BusinessException;

public interface FeedBackService {
	/**
	 * 按页获取已经处理过的FeedBack
	 * @param pager
	 * @return
	 * @throws BusinessException
	 */
	Pager<FeedBack> getCheckedByPager(Pager<FeedBack> pager) throws BusinessException ;
	
	/**
	 * 按页获取待处理的FeedBack
	 * @param pager pager中必须包含当前页码，如果pager中含有总的记录数更好，系统将不会再次去查找总的记录数。
	 * @return
	 * @throws BusinessException
	 */
	Pager<FeedBack> getUncheckedByPager(Pager<FeedBack> pager) throws BusinessException ;

	/**
	 * 检查服务器有无最新的FeedBack，有则插入到本地数据库，并返回获取到的最新的数据条数；否则若没有最新的或者发生其他错误则抛出异常。
	 * @return
	 * @throws BusinessException
	 */
	Integer checkLatest() throws BusinessException ;

	/**
	 * 将指定id的FeedBack标记为已处理状态
	 * @param idList FeedBack在本地数据库中的主键
	 * @param admin 管理员
	 * @return
	 * @throws BusinessException
	 */
	void updateToCheckedStatus(List<Integer> idList, Admin admin) throws BusinessException ;
	
	/**
	 * 将指定id的FeedBack标记为已处理状态
	 * @param id FeedBack在本地数据库中的主键
	 * @param admin 管理员
	 * @return
	 * @throws BusinessException
	 */
	void updateToCheckedStatus(Integer id, Admin admin) throws BusinessException ;
	
	
	/**
	 * 删除一条FeedBack。<br>
	 * 这个操作会产生两个动作：将本地的该条FeedBack改为Checked状态，同时向服务器发送删除这条FeedBack的消息。
	 * @param time 该条FeedBack在服务器的ID
	 * @param id 该条FeedBack在本地数据库的ID
	 * @param admin
	 * @throws BusinessException
	 */
	void delete(Long time,Integer id, Admin admin) throws BusinessException ;

	/**
	 * 从本地数据库中删除一条已经处理过的FeedBack。
	 * @param feedBackid
	 */
	void deleteChecked(int feedBackid)throws BusinessException ;
	
	
}
