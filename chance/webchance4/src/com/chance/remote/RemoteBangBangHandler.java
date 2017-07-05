package com.chance.remote;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.chance.monitor.model.BangBangListResult;
import com.chance.monitor.model.BangBangModel;
import com.chance.monitor.model.BangBangSingleResult;
import com.chance.monitor.model.ResultInfo;
import com.chance.util.MonitorConstants;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class RemoteBangBangHandler {
	private static final Logger log = LoggerFactory.getLogger(RemoteBangBangHandler.class);
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	static{
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.setSerializationInclusion(Include.NON_NULL);
	}
	/**
	 * 获取单篇帮帮
	 * @param userid
	 * @param banbangid
	 */
	public BangBangModel getSingleBangBang(int userid, int banbangid)  throws RemoteDataAccessException{
		String url = MonitorConstants.GET_SINGLEBANGBANG_URL+"/uid/"+userid+"/bid/"+banbangid+MonitorConstants.PostfixName;			 
		log.info("【getSingleBangBang URL】："+ url);	
		
		String jsonString = RemoteSupport.getJsonStringByKey(url, MonitorConstants.GET_SINGLEBANGBANG_KEY);
		BangBangSingleResult result;
		try {
			result = objectMapper.readValue(jsonString, BangBangSingleResult.class);
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
			result.getData().setUrl(RemoteSupport.getChatPicURL(result.getData().getUrl()));
			return result.getData();
		}else {
			log.error("获取单篇帮帮时出现异常:"+result.getStatus()+result.getDesc()+",userid:"+userid+",banbangid:"+banbangid);
			throw new RemoteDataAccessException(result.getStatus(),"获取单篇帮帮时出现异常:"+result.getDesc());
		}
				
	}

	/**
	 * 删除单篇帮帮
	 * @param userid
	 * @param banbangid
	 */
	public void deleteSingleBangBang(int userid, int banbangid)  throws RemoteDataAccessException {
		String url = MonitorConstants.DELETE_SINGLEBANGBANG_URL+"/uid/"+userid+"/bid/"+banbangid+MonitorConstants.PostfixName;
		log.info("【deleteSingleBangBang URL】："+ url);	
		
			 ResultInfo tempResult = RemoteSupport.getResultInfo(url, MonitorConstants.DELETE_SINGLEBANGBANG_KEY);
			 
			 if(tempResult == null || MonitorConstants.RESULT_STATUS_SUCCESS != tempResult.getStatus()){
				 log.error("删除单篇帮帮时出现异常:"+tempResult.getStatus()+tempResult.getDesc()+",userid:"+userid+",banbangid:"+banbangid);
				throw new RemoteDataAccessException(tempResult.getStatus(),"删除单篇帮帮时出现异常:"+tempResult.getDesc());	 
			 } 
	}


	/**
	 * 获取某个人的批量帮帮
	 * @param username 帮帮所属人的登录名
	 * @param startBid 帮帮开始的id（0，默认从最后一篇帮帮开始）
	 * @param number 获取的条数
	 * @return
	 * @throws RemoteDataAccessException
	 */
	public List<BangBangModel> GetBangBang(String username, int startDid, int number)throws RemoteDataAccessException {
		String url = MonitorConstants.GET_BANGBANG_URL+"/userName/"+username+"/startBid/"+startDid
				+"/number/"+number+MonitorConstants.PostfixName;
		log.info("【GetBangBang URL】："+ url);	
		String jsonString = RemoteSupport.getJsonStringByKey(url, MonitorConstants.GET_BANGBANG_KEY);
		BangBangListResult result = null;
		try {
			result = objectMapper.readValue(jsonString, BangBangListResult.class);
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
			List<BangBangModel>  dataList = result.getData();
			for (int i = 0; i < dataList.size(); i++) {
				dataList.get(i).setUrl(RemoteSupport.getChatPicURL(dataList.get(i).getUrl()));
			}
			result.setData(dataList);
			
			return result.getData();
		}else {
			 log.error("获取某个人的批量帮帮时出现异常:"+result.getStatus()+result.getDesc()+",username:"+username+",startDid:"+startDid+",number:"+number);
			 throw new RemoteDataAccessException(result.getStatus(),"获取某个人的批量帮帮时出现异常:"+result.getDesc());
		}
		
			
	}
		
	
	/**
	 * 返回帮帮id索引数组（第i页的第一个帮帮ID在索引为i的数组元素中，索引为0的位置存放查询的记录总数）
	 * @param username
	 * @return
	 * @throws IOException
	 */
	public List<Integer> getBangBangidIndex(String username)throws RemoteDataAccessException {
		String url = MonitorConstants.GET_BANGBANG_URL+"/userName/"+username+"/startBid/"+0
				+"/number/"+50000+MonitorConstants.PostfixName;
		log.info("【GetBangBang URL】："+ url);	
	
		String jsonString = RemoteSupport.getJsonStringByKey(url, MonitorConstants.GET_BANGBANG_KEY);
		BangBangListResult tempResult;
		try {
			tempResult = objectMapper.readValue(jsonString, BangBangListResult.class);
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
	
		 //获取data对应的数据
		 if(tempResult != null && MonitorConstants.RESULT_STATUS_SUCCESS == tempResult.getStatus()){
			 List<BangBangModel> dataList = tempResult.getData();
			 	 
				 int pageSize = MonitorConstants.PAGE_SIZE;
					//查询总记录数目
				 int lotalLength = dataList.size();	
					//计算一共有多少页
				 int totalPage = lotalLength % pageSize == 0 ? lotalLength / pageSize:lotalLength / pageSize + 1;
	
				 //创建一个totalPage+1大小的数组，第一个位置存放总长度，余下totalPage个位置里面存储着每一页的第一条记录对于的bangbangId
				 List<Integer> bangbangIdIndex = new ArrayList<Integer>(totalPage+1);
				 //第一个位置存放总的长度
				 bangbangIdIndex.add(lotalLength);
				 //后面totalPage个位置分别i个日志id索引
				 for(int i = 0 ;i<totalPage;i++)
					 bangbangIdIndex.add(dataList.get(i*pageSize).getBangbangId());
				 return bangbangIdIndex;		 
		 }
		 else{
			 log.error("获取帮帮id索引数组时出现异常:"+tempResult.getStatus()+tempResult.getDesc()+",username:"+username);
			 throw new RemoteDataAccessException(tempResult.getStatus(),"获取帮帮id索引数组时出现异常:"+tempResult.getDesc());
		 }
	}
}
