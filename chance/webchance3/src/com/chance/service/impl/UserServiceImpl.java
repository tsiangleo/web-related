package com.chance.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chance.domain.Diary;
import com.chance.domain.UserBriefInfo;
import com.chance.domain.UserOtherInfo;
import com.chance.monitor.model.BriefInfoModel;
import com.chance.monitor.model.DiaryModel;
import com.chance.monitor.model.OtherInfoMode;
import com.chance.remote.RemoteDataAccessException;
import com.chance.remote.RemoteSupport;
import com.chance.remote.RemoteUserHandler;
import com.chance.service.UserService;
import com.chance.service.impl.BusinessException;
import com.chance.util.TypeTransfer;

@Service("userService")
public class UserServiceImpl  implements UserService{

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private RemoteUserHandler remoteUserHandler;

	@Override
	public void updateUserOtherInfo(int userid, String type, long value) throws BusinessException {
		try {
			remoteUserHandler.updateOtherInfo(userid, type, value);
		} catch (RemoteDataAccessException e) {
			throw new BusinessException(e.getMessage(),e);
		}
	}

	@Override
	public UserOtherInfo getUserOtherInfo(String loginName)
			throws BusinessException {
		OtherInfoMode remote = null;
		try {
			remote = remoteUserHandler.GetOtherInfo(loginName);
		} catch (RemoteDataAccessException e) {
			throw new BusinessException(e.getMessage(),e);
		}
		return remote2Domain(remote);
	}

	@Override
	public UserOtherInfo getUserOtherInfo(int userid) throws BusinessException {
		OtherInfoMode remote = null;
		try {
			remote = remoteUserHandler.GetOtherInfo(userid);
		} catch (RemoteDataAccessException e) {
			throw new BusinessException(e.getMessage(),e);
		}
		return remote2Domain(remote);
	}

	@Override
	public UserBriefInfo getUserBriefInfo(String loginName)
			throws BusinessException {
		BriefInfoModel remote = null;
		try {
			remote = remoteUserHandler.getUserBriefInfo(loginName);
		} catch (RemoteDataAccessException e) {
			throw new BusinessException(e.getMessage(),e);
		}
		return remote2Domain(remote);
	}

	@Override
	public UserBriefInfo getUserBriefInfo(int userid) throws BusinessException {
		BriefInfoModel remote = null;
		try {
			remote = remoteUserHandler.getUserBriefInfo(userid);
		} catch (RemoteDataAccessException e) {
			throw new BusinessException(e.getMessage(),e);
		}
		return remote2Domain(remote);
	}

	@Override
	public void setAccountNoActive(String loginName) throws BusinessException {
		try {
			remoteUserHandler.setUserNoActive(loginName);
		} catch (RemoteDataAccessException e) {
			throw new BusinessException(e.getMessage(),e);
		}
		
	}

	@Override
	public void setAccountNoActive(int userid) throws BusinessException {
		try {
			remoteUserHandler.setUserNoActive(userid);
		} catch (RemoteDataAccessException e) {
			throw new BusinessException(e.getMessage(),e);
		}
		
	}

	@Override
	public void setAccountActive(String loginName) throws BusinessException {
		try {
			remoteUserHandler.setUserActive(loginName);
		} catch (RemoteDataAccessException e) {
			throw new BusinessException(e.getMessage(),e);
		}
		
	}

	@Override
	public void setAccountActive(int userid) throws BusinessException {
		try {
			remoteUserHandler.setUserActive(userid);
		} catch (RemoteDataAccessException e) {
			throw new BusinessException(e.getMessage(),e);
		}
		
	}
	
	private UserBriefInfo remote2Domain(BriefInfoModel remoteModel){
		if (remoteModel == null) {
			return null;
		}
		UserBriefInfo result = new UserBriefInfo();
		result.setActive(remoteModel.isActive());
		result.setAddress(RemoteSupport.getAddress(remoteModel.getLatitude(),remoteModel.getLongitude()));
		result.setAndroidSdk(remoteModel.getAndroidSdk());
		result.setApkVersion(remoteModel.getApkVersion());
		result.setBirthday(remoteModel.getBirthday());
		result.setLastLoginTime(remoteModel.getLastLoginTime());
		result.setLastMsgTime(remoteModel.getLastMsgTime());
		result.setLoginName(remoteModel.getLongName());
		result.setMobileType(remoteModel.getMobileType());
		result.setNickName(remoteModel.getNickName());
		result.setOnline(remoteModel.isOnline());
		result.setOnlineTime(formatTime(remoteModel.getOnlineTime()));
		result.setRegisterAddress(RemoteSupport.getAddress(remoteModel.getRegisterLatitude(),remoteModel.getRegisterLongitude()));
		result.setRegisterTime(remoteModel.getRegisterTime());
		result.setSex(remoteModel.getSex());
		result.setStatus(remoteModel.getStatus());
		result.setUrl(remoteModel.getUrl());
		result.setUserId(remoteModel.getUserId());
		return result;
	}
	
	/**
	 * 将毫秒转为xx天xx小时xx分xx秒xx毫秒
	 * @param ms
	 * @return
	 */
	private static String formatTime(long ms){
		int ss = 1000;  
        int mi = ss * 60;  
        int hh = mi * 60;  
        int dd = hh * 24;  

        long day = ms / dd;  
        long hour = (ms - day * dd) / hh;  
        long minute = (ms - day * dd - hour * hh) / mi;  
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;  
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;  

        StringBuffer sb = new StringBuffer();  
        if(day > 0) {  
            sb.append(day+"天");  
        }  
        if(hour > 0) {  
            sb.append(hour+"小时");  
        }  
        if(minute > 0) {  
            sb.append(minute+"分");  
        }  
        if(second > 0) {  
            sb.append(second+"秒");  
        }  
        if(milliSecond > 0) {  
            sb.append(milliSecond+"毫秒");  
        }  
        return sb.toString(); 
	}
	
	private UserOtherInfo remote2Domain(OtherInfoMode remoteModel){
		if (remoteModel == null) {
			return null;
		}
		UserOtherInfo result = new UserOtherInfo();
		result.setAttenTeamLimitNum(remoteModel.getAttenTeamLimitNum());
		result.setAttentionLimitNum(remoteModel.getAttentionLimitNum());
		result.setBangbangCount(remoteModel.getBangbangCount());
		result.setCollectTradeCount(remoteModel.getCollectTradeCount());
		result.setCreateTeamLimitNum(remoteModel.getCreateTeamLimitNum());
		result.setDiaryCount(remoteModel.getDiaryCount());
		result.setDiarySerial(remoteModel.getDiarySerial());
		result.setFreshNewsCount(remoteModel.getFreshNewsCount());
		result.setFriendLimitNum(remoteModel.getFriendLimitNum());
		result.setLastLoveTime(remoteModel.getLastLoveTime());
		result.setLoveLimitNum(remoteModel.getLoveLimitNum());
		result.setMarkLimitNum(remoteModel.getMarkLimitNum());
		result.setMeetMemoryCount(remoteModel.getMeetMemoryCount());
		result.setRecvDiscountcardCount(remoteModel.getRecvDiscountcardCount());
		result.setSleepLoveTime(remoteModel.getSleepLoveTime());
		result.setTradeConsumeRecordCount(remoteModel.getTradeConsumeRecordCount());
		return result;
	}

}
