package com.chance.remote;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.chance.monitor.model.DiaryListResult;
import com.chance.monitor.model.DiaryModel;
import com.chance.monitor.model.DiaryModelAndSize;
import com.chance.monitor.model.DiarySingleResult;
import com.chance.monitor.model.ResultInfo;
import com.chance.monitor.model.WebsocketConstants;
import com.chance.util.MonitorConstants;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class RemoteDiaryHandler{
	private static final Logger log = LoggerFactory.getLogger(RemoteDiaryHandler.class);
	private static ObjectMapper objectMapper = new ObjectMapper();
	static{
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.setSerializationInclusion(Include.NON_NULL);
	}
	
	/**
	 * 推送日志
	 * @param userid
	 * @param diaryid
	 * @param type
	 * @param desc
	 * @param passwd
	 * @throws RemoteDataAccessException
	 */
	public void pushChance(int userid, int diaryid,int type, String desc, String passwd) throws RemoteDataAccessException{
		String url = MonitorConstants.PUSH_CHANCE_URL+"/uid/"+userid+"/diaryid/"+diaryid+"/type/"+type+"/desc/"+desc+"/passwd/"+passwd+MonitorConstants.PostfixName;			 
		log.info("【pushChance URL】："+ url);	
		
		ResultInfo tempResult = RemoteSupport.getResultInfo(url, MonitorConstants.PUSH_CHANCE_KEY);
		 
		 if(tempResult == null || MonitorConstants.RESULT_STATUS_SUCCESS != tempResult.getStatus()){
			 log.error("推送Chance时出现异常,请求的URL为："+url);
			throw new RemoteDataAccessException(tempResult.getStatus(),"推送Chance时出现异常:"+tempResult.getDesc());	 
		 } 
	}
	
	
	public static boolean isChancePrivateID(int id){
		boolean flag = false;
		if (WebsocketConstants.CHANCE_PRIVATE_START_ID < id && id < WebsocketConstants.CHANCE_PRIVATE_END_ID) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 获取单篇日志
	 * @param userid
	 * @param diaryid
	 * @return
	 */
	public DiaryModel getSingleDiary(int userid, int diaryid) throws RemoteDataAccessException {
		String url = MonitorConstants.GET_SINGLEDIARY_URL+"/uid/"+userid+"/did/"+diaryid+MonitorConstants.PostfixName;			 
		log.info("【getSingleDiary URL】："+ url);	
		String jsonString = RemoteSupport.getJsonStringByKey(url, MonitorConstants.GET_SINGLEDIARY_KEY);
		DiarySingleResult result;
		try {
			result = objectMapper.readValue(jsonString, DiarySingleResult.class);
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
			//下载图片
			result.getData().setUrl(RemoteSupport.getPicURL(userid, diaryid, result.getData().getUrl()));
			return result.getData();
		}
		else {
			log.error("获取单篇日志时出现异常,请求的URL为："+url);
			 throw new RemoteDataAccessException(result.getStatus(),"获取单篇日志时出现异常:"+result.getDesc());
		}
	}
	
	
	/**
	 * 删除单篇日志
	 * @param userid
	 * @param diaryid
	 */
	public void deleteSingleDiary(int userid, int diaryid)  throws RemoteDataAccessException {
		String url = MonitorConstants.DELETE_SINGLEDIARY_URL+"/uid/"+userid+"/did/"+diaryid+MonitorConstants.PostfixName;
		log.info("【deleteSingleDiary URL】："+ url);	
		
			 ResultInfo tempResult = RemoteSupport.getResultInfo(url, MonitorConstants.DELETE_SINGLEDIARY_KEY);
			 
			 if(tempResult == null || MonitorConstants.RESULT_STATUS_SUCCESS != tempResult.getStatus()){
				 log.error("删除单篇日志时出现异常,请求的URL为："+url);
				throw new RemoteDataAccessException(tempResult.getStatus(),"删除单篇日志时出现异常:"+tempResult.getDesc());	 
			 }
	}
	
	/**
	 * 获取某个人的批量日志(不获取图片)
	 * @param username 日志所属人的登录名
	 * @param startDid 日志开始的id（0，默认从最后一篇日志开始）
	 * @param number 获取的条数
	 * @return
	 * @throws IOException
	 */
	public DiaryModelAndSize GetDiary(String username, int startDid, int number)throws RemoteDataAccessException {
		String url = MonitorConstants.GET_DIARY_URL+"/userName/"+username+"/startDid/"+startDid
				+"/number/"+number+MonitorConstants.PostfixName;
		log.info("【GetDiary URL】："+ url);	
		
		String jsonString = RemoteSupport.getJsonStringByKey(url, MonitorConstants.GET_DIARY_KEY);
		DiaryListResult result;
		try {
			result = objectMapper.readValue(jsonString, DiaryListResult.class);
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
		}
		else {
			log.error("获取某个人的批量日志时出现异常,请求的URL为："+url);
			throw new RemoteDataAccessException(result.getStatus(),result.getDesc());
		}
	}
	
}
