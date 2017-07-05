package com.chance.remote;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.chance.monitor.model.DiaryCommentListResult;
import com.chance.monitor.model.DiaryCommentModel;
import com.chance.monitor.model.DiaryCommentModelAndSize;
import com.chance.monitor.model.DiaryCommentSingleResult;
import com.chance.monitor.model.ResultInfo;
import com.chance.util.MonitorConstants;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class RemoteDiaryCommentHandler {
	private static final Logger log = LoggerFactory.getLogger(RemoteDiaryCommentHandler.class);
	private static ObjectMapper objectMapper = new ObjectMapper();
	static{
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.setSerializationInclusion(Include.NON_NULL);
	}
	
	/**
	 * 获取某个人的某篇日志的批量评论
	 * @param username
	 * @param diaryid
	 * @param startDcid 开始评论的id（0，表示是从最后一条开始）
	 * @param number
	 * @return
	 */
	public DiaryCommentModelAndSize getDiaryComment(String username, int diaryid,
			int startDcid,int number) throws RemoteDataAccessException {
		
		String url = MonitorConstants.GET_DIARYCOMMENT_URL+"/userName/"+username+"/did/"+diaryid+"/startDcid/"+startDcid
				+"/number/"+number+MonitorConstants.PostfixName;
		log.info("【getDiaryComment URL】："+ url);	
	
		String jsonString = RemoteSupport.getJsonStringByKey(url, MonitorConstants.GET_DIARYCOMMENT_KEY);
		DiaryCommentListResult result;
		try {
			result = objectMapper.readValue(jsonString, DiaryCommentListResult.class);
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
			log.error("获取某个人的某篇日志的批量评论时出现异常:"+result.getStatus()+result.getDesc()+",username:"+username+",diaryid:"+diaryid+",startDcid:"+startDcid+",number:"+number);
			 throw new RemoteDataAccessException(result.getStatus(),"获取某个人的某篇日志的批量评论时出现异常:"+result.getDesc());
		}
	}
	

	/**
	 * 获取日志的单条评论
	 * @param uid
	 * @param diaryid
	 * @param cid
	 * @return
	 * @throws RemoteDataAccessException
	 */
	public DiaryCommentModel getSingleDiaryComment(int uid, int diaryid,
			int cid) throws RemoteDataAccessException {
		
		String url = MonitorConstants.GET_SINGLEDIARYCOMMENT_URL+"/uid/"+uid+"/did/"+diaryid+"/cid/"+cid+MonitorConstants.PostfixName;			 
		log.info("【getSingleDiaryComment URL】："+ url);	 
		
		String jsonString = RemoteSupport.getJsonStringByKey(url, MonitorConstants.GET_SINGLEDIARYCOMMENT_KEY);
		DiaryCommentSingleResult result;
		try {
			result = objectMapper.readValue(jsonString, DiaryCommentSingleResult.class);
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
			log.error("获取日志的单条评论时出现异常:"+result.getStatus()+result.getDesc()+",uid:"+uid+",diaryid:"+diaryid+",cid:"+cid);
			 throw new RemoteDataAccessException(result.getStatus(),"获取日志的单条评论时出现异常:"+result.getDesc());
		}
	}

	/**
	 * 删除日志的单条评论
	 * @param uid
	 * @param diaryid
	 * @param cid
	 * @return
	 * @throws IOException
	 */
	public void delSingleDiaryComment(int uid, int diaryid,
			int cid) throws RemoteDataAccessException {
		
		String url = MonitorConstants.DELETE_SINGLEDIARYCOMMENT_URL+"/uid/"+uid+"/did/"+diaryid+"/cid/"+cid+MonitorConstants.PostfixName;
		log.info("【delSingleDiaryComment URL】："+ url);	
		
			 ResultInfo tempResult = RemoteSupport.getResultInfo(url, MonitorConstants.DELETE_SINGLEDIARYCOMMENT_KEY);
			 
			 if(tempResult == null || MonitorConstants.RESULT_STATUS_SUCCESS != tempResult.getStatus()){
				 log.error("删除日志的单条评论时出现异常:"+tempResult.getStatus()+tempResult.getDesc()+",uid:"+uid+",diaryid:"+diaryid+",cid:"+cid);
				throw new RemoteDataAccessException(tempResult.getStatus(),"删除日志的单条评论时出现异常:"+tempResult.getDesc());	 
			 } 
	}

	
	/**
	 * 返回一个长度为totalPage+1的日志志评论id索引数组，索引范围为（0<=index<=totalPage）。
	 * <br>索引为0的位置存放查询的记录总数，索引为1~totalPage的位置依此存放第1~totalPage页的startDcid。
	 * 
	 * @param username
	 * @param diaryid
	 * @return
	 * @throws IOException
	 */
	public List<Integer> getDiaryCommentIdIndex(String username, int diaryid)
			throws RemoteDataAccessException {
		String url = MonitorConstants.GET_DIARYCOMMENT_URL+"/userName/"+username+"/did/"+diaryid+"/startDcid/"+0
				+"/number/"+MonitorConstants.PAGE_SIZE+MonitorConstants.PostfixName;
		
		log.info("【getDiaryComment URL】："+ url);		
	  
		String jsonString = RemoteSupport.getJsonStringByKey(url, MonitorConstants.GET_DIARYCOMMENT_KEY);
		DiaryCommentListResult tempResult;
		try {
			tempResult = objectMapper.readValue(jsonString, DiaryCommentListResult.class);
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
		
		return idList2IdIndex(tempResult.getData().getDiaryCommentIds());	
				
	}
	
	/**
	 * 将id列表转换为一个长度为totalPage+1的日志评论id索引数组
	 * @param idsList
	 * @return
	 */
	public List<Integer> idList2IdIndex(List<Integer> idsList) {
		if(idsList == null || idsList.isEmpty())
			return Collections.emptyList(); 

		//查询总记录数目
		int lotalLength = idsList.size();

		if(lotalLength > 0){		 
			int pageSize = MonitorConstants.PAGE_SIZE;
			//计算一共有多少页
			int totalPage = lotalLength % pageSize == 0 ? lotalLength / pageSize:lotalLength / pageSize + 1;
				 
			//创建一个totalPage+1大小的数组，第一个位置存放总长度，余下totalPage个位置里面存储着每一页的第一条记录对于的diaryid
			List<Integer> diaryidIndex = new ArrayList<Integer>(totalPage+1);
			//第一个位置存放总的长度
			diaryidIndex.add(lotalLength);
				 
			//第1页的startDid应该为0
			diaryidIndex.add(0);
			//后面totalPage个位置分别i个日志id索引
			for(int i = 2 ;i<=totalPage;i++){
				diaryidIndex.add(idsList.get((i-1)*pageSize - 1));
			}
			return diaryidIndex;
		}	
		
		return Collections.emptyList(); 
		 
	}
}
