package com.chance.remote;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.chance.monitor.model.ResultInfo;
import com.chance.monitor.model.TeamInfoPendingListResult;
import com.chance.monitor.model.TeamInfoPendingModel;
import com.chance.util.MonitorConstants;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class RemoteTeamInfoPendingHandler{
	private static final Logger log = LoggerFactory.getLogger(RemoteTeamInfoPendingHandler.class);
	private static ObjectMapper objectMapper = new ObjectMapper();

	static{
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.setSerializationInclusion(Include.NON_NULL);
	}
	
	
	/**
	 * 获取待审核的小组
	 * @param number
	 * @return
	 * @throws RemoteDataAccessException
	 */
	public List<TeamInfoPendingModel> getTeamInfoPending(int number) throws RemoteDataAccessException {

		String url = MonitorConstants.GET_TEAMINFOPENDING_URL+"/number/"+number+MonitorConstants.PostfixName;
	log.info("【getTeamInfoPending URL】："+ url);	
	
		 
		 String after = RemoteSupport.getJsonStringByKey(url, MonitorConstants.GET_TEAMINFOPENDING_KEY);
		 TeamInfoPendingListResult result;
		try {
			result = objectMapper.readValue(after, TeamInfoPendingListResult.class);
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
			List<TeamInfoPendingModel>  dataList = result.getData();
			for (int i = 0; i < dataList.size(); i++) {
				dataList.get(i).setIcon(RemoteSupport.getAvatarPicURL(dataList.get(i).getIcon()));
			}
			result.setData(dataList);
			return result.getData();
		 }else{
				log.error("获取待审核的小组时出现异常:"+result.getStatus()+result.getDesc()+",number:"+number);
				throw new RemoteDataAccessException(result.getStatus(),"获取待审核的小组时出现异常:"+result.getDesc());
		}
	}

	/**
	 * 通过审核小组
	 * @param teamid 审核的小组id
	 * @param isPass 1通过，2不通过
	 * @param reason 原因
	 * @return
	 * @throws RemoteDataAccessException
	 */
	public void audiaTeamInfoPending(int teamid, int isPass, String reason)
			throws RemoteDataAccessException {
		String url = MonitorConstants.AUDIA_TEAMINFOPENDING_URL+"/teamid/"+teamid+"/pass/"+isPass+"/reason/"+reason+MonitorConstants.PostfixName;
		log.info("【audiaTeamInfoPending URL】："+ url);	
		
			 ResultInfo tempResult = RemoteSupport.getResultInfo(url, MonitorConstants.AUDIA_TEAMINFOPENDING_KEY);
			 
			 if(tempResult == null || MonitorConstants.RESULT_STATUS_SUCCESS != tempResult.getStatus()){
				 log.error("审核小组信息时出现异常:"+tempResult.getStatus()+tempResult.getDesc()+",teamid:"+teamid+",isPass:"+isPass+",reason:"+reason);
				throw new RemoteDataAccessException(tempResult.getStatus(),"审核小组信息时出现异常:"+tempResult.getDesc());	 
			 } 
	}
	
}
