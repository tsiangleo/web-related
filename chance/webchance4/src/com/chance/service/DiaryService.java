package com.chance.service;
import javax.servlet.http.HttpSession;

import com.chance.domain.Diary;
import com.chance.domain.Pager;
import com.chance.service.impl.BusinessException;

/**
 * Diary的服务接口
 * @author michael
 *
 */
public interface DiaryService {
	/**
	 * 获取单条日志
	 * @param userid
	 * @param diaryid
	 * @return
	 * @throws BusinessException
	 */
	Diary get(Integer userid, Integer diaryid)throws BusinessException ;
	
	/**
	 * 按页获取某个用户的日志
	 * @param session session出现在这里只是为了diaryidIndex与session绑定在一起。
	 * @param pager
	 * @param username
	 * @return
	 * @throws BusinessException
	 */
	Pager<Diary> getByPager(HttpSession session ,Pager<Diary> pager,String username)throws BusinessException ;
	
	
	/**
	 * 推送日志
	 */
	void pushDiary(int userid, int diaryid,int type, String desc, String passwd) throws BusinessException ;

	/**
	 * 删除单条日志，向服务获取发送删除单条Diary请求
	 * @param userid
	 * @param diaryid
	 */
	void delete(Integer userid, Integer diaryid)throws BusinessException ;

}
