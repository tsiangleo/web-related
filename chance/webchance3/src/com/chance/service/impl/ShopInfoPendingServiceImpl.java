package com.chance.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chance.domain.ShopInfoPending;
import com.chance.monitor.model.ShopInfoPendingModel;
import com.chance.remote.RemoteDataAccessException;
import com.chance.remote.RemoteShopInfoPendingHandler;
import com.chance.service.ShopInfoPendingService;


@Service("shopInfoPendingService")
public class ShopInfoPendingServiceImpl  implements ShopInfoPendingService{

	private static final Logger log = LoggerFactory.getLogger(ShopInfoPendingServiceImpl.class);
	
	@Autowired
	private RemoteShopInfoPendingHandler remoteShopInfoPendingHandler;

	@Override
	public List<ShopInfoPending> getList(int number)throws BusinessException{
		try {
			return remote2Domain(remoteShopInfoPendingHandler.getShopInfoPending(number));
		} catch (RemoteDataAccessException e) {
			throw new BusinessException(e.getMessage(),e);
		}
	}

	@Override
	public void audit(int teamid, int isPass, String reason) throws BusinessException{
		try {
			remoteShopInfoPendingHandler.audiaShopInfoPending(teamid, isPass, reason);
		} catch (RemoteDataAccessException e) {
			throw new BusinessException(e.getMessage(),e);
		}
		
	}
	private ShopInfoPending remote2Domain(ShopInfoPendingModel remoteModel){
		if (remoteModel == null) {
			return null;
		}
		ShopInfoPending result = new ShopInfoPending();
		result.setId(remoteModel.getId());
		result.setShopAddr(remoteModel.getShopAddr());
		result.setShopDesc(remoteModel.getShopDesc());
		result.setShopName(remoteModel.getShopName());
		result.setShopPhone(remoteModel.getShopPhone());
		result.setShopTags(remoteModel.getShopTags());
		result.setShopType(remoteModel.getShopType());
		result.setSlogan(remoteModel.getSlogan());
		result.setStatus(remoteModel.getStatus());
		result.setTime(remoteModel.getTime());
		
		//获取各类图片
		if(remoteModel.getIdCard() != null && !remoteModel.getIdCard().isEmpty()){
			List<String> tempList = remoteModel.getIdCard();
			for(int i = 0;i<tempList.size();i++)
				tempList.set(i, com.chance.remote.RemoteSupport.getTradeLCPicURL(tempList.get(i)));
			result.setIdCard(tempList);
		}
		
		if(remoteModel.getShopLicense() != null && !remoteModel.getShopLicense().isEmpty()){
			List<String> tempList = remoteModel.getShopLicense();
			for(int i = 0;i<tempList.size();i++)
				tempList.set(i, com.chance.remote.RemoteSupport.getTradeLCPicURL(tempList.get(i)));
			result.setShopLicense(tempList);
		}
		
		if(remoteModel.getShopLogo() != null && !remoteModel.getShopLogo().isEmpty()){
			List<String> tempList = remoteModel.getShopLogo();
			for(int i = 0;i<tempList.size();i++)
				tempList.set(i, com.chance.remote.RemoteSupport.getTradeLCPicURL(tempList.get(i)));
			result.setShopLogo(tempList);
		}
		
		if(remoteModel.getShopDeco() != null && !remoteModel.getShopDeco().isEmpty()){
			List<String> tempList = remoteModel.getShopDeco();
			for(int i = 0;i < tempList.size();i++)
				tempList.set(i, com.chance.remote.RemoteSupport.getTradeImgPicURL(tempList.get(i)));
			result.setShopDeco(tempList);
		}
		
		return result;
	}
	
	private List<ShopInfoPending> remote2Domain(List<ShopInfoPendingModel> remoteModel){
		if (remoteModel == null) {
			return null;
		}
		List<ShopInfoPending> result = new ArrayList<ShopInfoPending>();
		for (ShopInfoPendingModel remote : remoteModel) {
			result.add(remote2Domain(remote));
		}
		return result;
	}
	
	

}
