package com.chance.service;

import javax.servlet.http.HttpSession;

import com.chance.domain.Diary;
import com.chance.domain.DiaryComment;
import com.chance.domain.Pager;
import com.chance.service.impl.BusinessException;


public interface DiaryCommentService {
	
	/**
	 * 按页获取某个用户某篇日志的批量评论
	 *  @param session session出现在这里只是为了diaryidIndex与session绑定在一起。
	 * @param pager
	 * @param username
	 * @param diaryid
	 * @return
	 * @throws BusinessException
	 */
	Pager<DiaryComment> getByPager(HttpSession session,Pager<DiaryComment> pager,String username,int diaryid) throws BusinessException ;
	
	/**
	 * 获取日志的单条评论
	 * @param userid
	 * @param diaryid
	 * @param cid
	 * @return
	 * @throws BusinessException
	 */
	public DiaryComment get(int userid, int diaryid,int cid)throws BusinessException ;
	
	/**
	 * 删除日志的单条评论
	 * @param userid
	 * @param diaryid
	 * @param cid
	 * @throws BusinessException
	 */
	void delete(int userid, int diaryid,int cid)throws BusinessException ;
}
