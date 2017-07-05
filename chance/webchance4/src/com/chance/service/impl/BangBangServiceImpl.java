package com.chance.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chance.domain.BangBang;
import com.chance.monitor.model.BangBangModel;
import com.chance.remote.RemoteDataAccessException;
import com.chance.remote.RemoteBangBangHandler;
import com.chance.service.BangBangService;
import com.chance.service.impl.BusinessException;

@Service("bangBangService")
public class BangBangServiceImpl  implements BangBangService{

	private static final Logger log = LoggerFactory.getLogger(BangBangServiceImpl.class);
	
	@Autowired
	private RemoteBangBangHandler remoteBangBangHandler;
	
	@Override
	public BangBang get(Integer userid, Integer BangBangid) throws BusinessException {
		BangBangModel BangBangModel = null;
		try {
			BangBangModel = remoteBangBangHandler.getSingleBangBang(userid, BangBangid);
		} catch (RemoteDataAccessException e) {
			throw new BusinessException(e.getMessage(),e);
		}
		return remote2Domain(BangBangModel);
	}
	
	@Override
	public void delete(Integer userid, Integer BangBangid)
			throws BusinessException {
		try {
			remoteBangBangHandler.deleteSingleBangBang(userid, BangBangid);
		} catch (RemoteDataAccessException e) {
			throw new BusinessException(e.getMessage(),e);
		}
		
	}

	/**
	 * 工具方法：服务器返回的模型转为本地模型
	 * @return
	 */
	private BangBang remote2Domain(BangBangModel remoteModel){
		if (remoteModel == null) {
			return null;
		}
		BangBang result = new BangBang();
		result.setContent(remoteModel.getContent());
		result.setBangBangId(remoteModel.getBangbangId());
		result.setPicUrl(remoteModel.getUrl());
		result.setUserId(remoteModel.getAuthorId());
		result.setUserName(remoteModel.getName());
		result.setStartTime(remoteModel.getStartTime());
		result.setEndTime(remoteModel.getEndTime());
		result.setIsActive(remoteModel.isActive());
		return result;
	}



}
