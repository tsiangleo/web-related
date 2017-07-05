package com.chance.service;

import java.util.List;

import com.chance.domain.TeamInfoPending;
import com.chance.service.impl.BusinessException;

public interface TeamInfoPendingService {

	/**
	 * 获取待审核的小组
	 */
	List<TeamInfoPending> getList(int number) throws BusinessException;

	/**
	 * 审核
	 */
	void audit(int teamid, int isPass, String reason)throws BusinessException;
}
