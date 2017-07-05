package com.chance.remote;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.chance.monitor.model.ReportClientListResult;
import com.chance.monitor.model.ReportClientModel;
import com.chance.monitor.model.ResultInfo;
import com.chance.util.MonitorConstants;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class RemoteReportUserHandler {
	
	private static final Logger log = LoggerFactory.getLogger(RemoteReportUserHandler.class);
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	static{
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.setSerializationInclusion(Include.NON_NULL);
	}	
	
	/**
	 * 批量获取ReportUser
	 * @param number 
	 * @param offset 
	 * @return
	 */
	public List<ReportClientModel> getReportUser(int number, Long offset) throws RemoteDataAccessException {
		
		String url = MonitorConstants.GET_REPORTCLIENT_URL+"/number/"+number
				+"/offset/"+offset+MonitorConstants.PostfixName;
		
	log.info("【getReportUser URL】："+ url);	
	 
		 String jsonString = RemoteSupport.getJsonStringByKey(url, MonitorConstants.GET_REPORTCLIENT_KEY);
		 ReportClientListResult result;
		try {
			result = objectMapper.readValue(jsonString, ReportClientListResult.class);
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
			List<ReportClientModel> resultList =  result.getData();
			return resultList;
		}
		else{
			log.error("批量获取ReportUser时出现异常:"+result.getStatus()+result.getDesc()+",number:"+number+",offset:"+offset);
			throw new RemoteDataAccessException(result.getStatus(),"批量获取ReportUser时出现异常:"+result.getDesc());
		}	
	}

	/**
	 * 删除单条ReportUser
	 * @param time
	 * @return
	 * @throws RemoteDataAccessException
	 */
	public void deleteReportUser(Long time) throws RemoteDataAccessException {
		String url = MonitorConstants.DELETE_REPORTCLIENT_URL+"/time/"+time+MonitorConstants.PostfixName;
		log.info("【deleteReportUser URL】："+ url);	
		
			 ResultInfo tempResult = RemoteSupport.getResultInfo(url, MonitorConstants.DELETE_REPORTCLIENT_KEY);
			 
			 if(tempResult == null || MonitorConstants.RESULT_STATUS_SUCCESS != tempResult.getStatus()){
				 log.error("删除单条ReportUser时出现异常:"+tempResult.getStatus()+tempResult.getDesc()+",time:"+time);
				throw new RemoteDataAccessException(tempResult.getStatus(),"删除单条ReportUser时出现异常:"+tempResult.getDesc());	 
			 } 
	}	
}
