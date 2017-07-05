package com.chance.service;

import java.util.List;

import com.chance.domain.ShopInfoPending;
import com.chance.service.impl.BusinessException;

public interface ShopInfoPendingService {

	/**
	 * 获取待审核的小组
	 */
	List<ShopInfoPending> getList(int number) throws BusinessException;

	/**
	 * 审核
	 */
	void audit(int teamid, int isPass, String reason)throws BusinessException;
}
