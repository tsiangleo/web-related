package com.chance.service;


import com.chance.domain.UserBriefInfo;
import com.chance.domain.UserOtherInfo;
import com.chance.service.impl.BusinessException;


public interface UserService {
	
	/**
	 * 更新用户的其他信息
	 * @param userid
	 * @param type
	 * @param value
	 * @throws BusinessException
	 */
	void updateUserOtherInfo(int userid, String type,long value)throws BusinessException ;
	
	/**
	 * 获取用户的其他信息
	 * @param loginName
	 * @return
	 * @throws BusinessException
	 */
	UserOtherInfo getUserOtherInfo(String loginName)throws BusinessException ;
	
	/**
	 * 获取用户的其他信息
	 * @param userid
	 * @return
	 * @throws BusinessException
	 */
	UserOtherInfo getUserOtherInfo(int userid)throws BusinessException ;
	
	/**
	 * 查询用户信息
	 * @param loginName
	 * @return
	 * @throws BusinessException
	 */
	UserBriefInfo getUserBriefInfo(String loginName) throws BusinessException ;
	
	/**
	 * 查询用户信息
	 * @param userid
	 * @return
	 * @throws BusinessException
	 */
	UserBriefInfo getUserBriefInfo(int userid) throws BusinessException ;
	
	/**
	 * 屏蔽账号
	 */
	void setAccountNoActive(String loginName)throws BusinessException ;
	
	/**
	 * 屏蔽账号
	 */
	void setAccountNoActive(int userid)throws BusinessException ;
	
	/**
	 * 激活账号
	 */
	void setAccountActive(String loginName)throws BusinessException ;
	
	/**
	 * 激活账号
	 */
	void setAccountActive(int userid)throws BusinessException ;
}
