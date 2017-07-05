package com.chance.remote;


import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.chance.monitor.model.BriefInfoModel;
import com.chance.monitor.model.OtherInfoMode;
import com.chance.monitor.model.OtherInfoSingleResult;
import com.chance.monitor.model.TeamInfoPendingModel;
import com.chance.monitor.model.UserLocation;
import com.chance.monitor.model.BriefInfoSingleResult;
import com.chance.monitor.model.ResultInfo;
import com.chance.util.MonitorConstants;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class RemoteUserHandler {
	private static ObjectMapper objectMapper = new ObjectMapper();

	static{
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.setSerializationInclusion(Include.NON_NULL);
	}
	
	private static final Logger log = LoggerFactory.getLogger(RemoteUserHandler.class);
	
	/**
	 * 获取用户其他信息
	 * @param username
	 * @return
	 * @throws RemoteDataAccessException
	 */
	public OtherInfoMode GetOtherInfo(String username) throws RemoteDataAccessException{
		String url = MonitorConstants.GET_OTHERINFO_URL+"/userName/"+username
				+"/flag/"+MonitorConstants.FLAG_CHANCE_USERNAME+MonitorConstants.PostfixName;
		log.info("【GetOtherInfo URL】："+ url);
		
		String jsonString = RemoteSupport.getJsonStringByKey(url, MonitorConstants.GET_OTHERINFO_KEY);
		OtherInfoSingleResult result;
		try {
			result = objectMapper.readValue(jsonString, OtherInfoSingleResult.class);
		} catch (JsonParseException e) {
			log.error("Json解析时出现异常", e);
			throw new RemoteDataAccessException("Json解析时出现异常", e);
		} catch (JsonMappingException e) {
			log.error("Json映射时出现异常", e);
			throw new RemoteDataAccessException("Json映射时出现异常", e);
		} catch (IOException e) {
			log.error("从服务器获取数据时发生IO异常", e);
			throw new RemoteDataAccessException("从服务器获取数据发生IO异常", e);
		}
		
		if (result != null && result.getStatus() == MonitorConstants.RESULT_STATUS_SUCCESS) {
			return result.getData();
		 }else{
			log.error("获取用户其他信息时出现异常:"+result.getStatus()+result.getDesc()+",username:"+username);
			throw new RemoteDataAccessException(result.getStatus(),"获取用户其他信息时出现异常:"+result.getDesc());
		}
	}
	
	/**
	 * 获取用户其他信息
	 * @param userid
	 * @return
	 * @throws RemoteDataAccessException
	 */
	public OtherInfoMode GetOtherInfo(int userid) throws RemoteDataAccessException{
		String url = MonitorConstants.GET_OTHERINFO_URL+"/userName/"+userid
				+"/flag/"+MonitorConstants.FLAG_CHANCE_USERID+MonitorConstants.PostfixName;
		log.info("【GetOtherInfo URL】："+ url);
		
		String jsonString = RemoteSupport.getJsonStringByKey(url, MonitorConstants.GET_OTHERINFO_KEY);
		OtherInfoSingleResult result;
		try {
			result = objectMapper.readValue(jsonString, OtherInfoSingleResult.class);
		} catch (JsonParseException e) {
			log.error("Json解析时出现异常", e);
			throw new RemoteDataAccessException("Json解析时出现异常", e);
		} catch (JsonMappingException e) {
			log.error("Json映射时出现异常", e);
			throw new RemoteDataAccessException("Json映射时出现异常", e);
		} catch (IOException e) {
			log.error("从服务器获取数据时发生IO异常", e);
			throw new RemoteDataAccessException("从服务器获取数据发生IO异常", e);
		}
		
		if (result != null && result.getStatus() == MonitorConstants.RESULT_STATUS_SUCCESS) {
			return result.getData();
		 }else{
			log.error("获取用户其他信息时出现异常:"+result.getStatus()+result.getDesc()+",userid:"+userid);
			throw new RemoteDataAccessException(result.getStatus(),"获取用户其他信息时出现异常:"+result.getDesc());
		}
	}
	

	 /**
	  * 更新用户的其他信息
	  * @param userid
	  * @param type
	  * @param value
	  * @return
	  * @throws RemoteDataAccessException
	  */
	public void updateOtherInfo(int userid, String type,long value) throws RemoteDataAccessException{
		
		String url = MonitorConstants.UPDATE_OTHERINFO_URL+"/uid/"+userid
				+"/type/"+type+"/value/"+value+MonitorConstants.PostfixName;
		log.info("【updateOtherInfo URL】："+ url);
		
		ResultInfo tempResult = RemoteSupport.getResultInfo(url, MonitorConstants.UPDATE_OTHERINFO_KEY);
		 
		if(tempResult == null || MonitorConstants.RESULT_STATUS_SUCCESS != tempResult.getStatus()){
			 log.error("更新用户的其他信息时出现异常:"+tempResult.getStatus()+tempResult.getDesc()+",userid:"+userid+",type:"+type+",value:"+value);
			throw new RemoteDataAccessException(tempResult.getStatus(),"更新用户的其他信息时出现异常:"+tempResult.getDesc());	 
		 } 
	}
	
	/**
	 * 工具方法
	 * @param url
	 * @return
	 * @throws RemoteDataAccessException
	 */
	private BriefInfoModel getUserBriefInfoByURL(String url) throws RemoteDataAccessException {
		log.info("【getUserBriefInfoByURL URL】："+ url);	
		 
		 String jsonString = RemoteSupport.getJsonStringByKey(url, MonitorConstants.GET_BRIEFINFO_KEY);
		 BriefInfoSingleResult result;
		try {
			result = objectMapper.readValue(jsonString, BriefInfoSingleResult.class);
		} catch (JsonParseException e) {
			log.error("Json解析时出现异常", e);
			throw new RemoteDataAccessException("Json解析时出现异常", e);
		} catch (JsonMappingException e) {
			log.error("Json映射时出现异常", e);
			throw new RemoteDataAccessException("Json映射时出现异常", e);
		} catch (IOException e) {
			log.error("从服务器获取数据时发生IO异常", e);
			throw new RemoteDataAccessException("从服务器获取数据发生IO异常", e);
		}
		
		 if (result.getStatus() == MonitorConstants.RESULT_STATUS_SUCCESS) {
			 //获取图片
			result.getData().setUrl((RemoteSupport.getAvatarPicURL(result.getData().getUrl())));
			return result.getData();	
		}else{
			log.error("获取用户信息时出现异常:"+result.getStatus()+result.getDesc());
			throw new RemoteDataAccessException(result.getStatus(),"获取用户信息时出现异常:"+result.getDesc());
		}
		 
	}

	private void activeUserByURL(String url) throws RemoteDataAccessException {
		
		log.info("【activeUserByURL URL】："+ url);	
		
			 ResultInfo tempResult = RemoteSupport.getResultInfo(url, MonitorConstants.ACTIVE_USER_KEY);
			 
			 if(tempResult == null || MonitorConstants.RESULT_STATUS_SUCCESS != tempResult.getStatus()){
				 log.error("冻结/解冻用户账户时出现异常:"+tempResult.getStatus()+tempResult.getDesc());
				throw new RemoteDataAccessException(tempResult.getStatus(),"冻结/解冻用户账户时出现异常:"+tempResult.getDesc());	 
			 } 
	}




	/**
	 * 获取用户briefinfo
	 * @param loginName
	 * @return
	 */
	public BriefInfoModel getUserBriefInfo(String loginName) throws RemoteDataAccessException {
		String url = MonitorConstants.GET_BRIEFINFO_URL+"/userName/"+loginName
				+"/flag/"+MonitorConstants.FLAG_CHANCE_USERNAME+MonitorConstants.PostfixName;		
		return getUserBriefInfoByURL(url);
	}




	/**
	 * 获取用户briefinfo
	 * @param userid
	 * @return
	 */
	public BriefInfoModel getUserBriefInfo(int uerid) throws RemoteDataAccessException {
		String url = MonitorConstants.GET_BRIEFINFO_URL+"/userName/"+uerid
				+"/flag/"+MonitorConstants.FLAG_CHANCE_USERID+MonitorConstants.PostfixName;
		return getUserBriefInfoByURL(url);
	}




	/**
	 * 激活用户
	 * @param loginName
	 * @return
	 * @throws IOException
	 */
	public void setUserActive(String loginName) throws RemoteDataAccessException {
		String url = MonitorConstants.ACTIVE_USER_URL+"/userName/"+loginName
				+"/active/1"+"/flag/"+MonitorConstants.FLAG_CHANCE_USERNAME+MonitorConstants.PostfixName;
		activeUserByURL(url);
	}




	/**
	 * 激活用户
	 * @param loginName
	 * @return
	 * @throws IOException
	 */
	public void setUserActive(int userid) throws RemoteDataAccessException {
		String url = MonitorConstants.ACTIVE_USER_URL+"/userName/"+userid
				+"/active/1"+"/flag/"+MonitorConstants.FLAG_CHANCE_USERID+MonitorConstants.PostfixName;
		activeUserByURL(url);
	}




	/**
	 * 屏蔽用户
	 * @param loginName
	 * @return
	 * @throws IOException
	 */
	public void setUserNoActive(String loginName) throws RemoteDataAccessException {
		String url = MonitorConstants.ACTIVE_USER_URL+"/userName/"+loginName
				+"/active/0"+"/flag/"+MonitorConstants.FLAG_CHANCE_USERNAME+MonitorConstants.PostfixName;
		activeUserByURL(url);
	}




	/**
	 * 屏蔽用户
	 * @param loginName
	 * @return
	 * @throws IOException
	 */
	public void setUserNoActive(int userid) throws RemoteDataAccessException {
		String url = MonitorConstants.ACTIVE_USER_URL+"/userName/"+userid
				+"/active/0"+"/flag/"+MonitorConstants.FLAG_CHANCE_USERID+MonitorConstants.PostfixName;
		activeUserByURL(url);
	}
	
}
