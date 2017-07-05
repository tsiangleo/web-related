package com.chance.service;

import com.chance.domain.BangBang;
import com.chance.service.impl.BusinessException;

/**
 * BangBang的服务接口
 * @author michael
 *
 */
public interface BangBangService {

	/**
	 * 获取单条帮帮
	 * @param userid
	 * @param bangBangid
	 * @return
	 * @throws BusinessException
	 */
	BangBang get(Integer userid, Integer bangBangid)throws BusinessException ;

	/**
	 * 删除单条帮帮，向服务获取发送删除单条BangBang请求
	 * @param userid
	 * @param bangBangid
	 */
	void delete(Integer userid, Integer bangBangid)throws BusinessException ;
	
	
}
