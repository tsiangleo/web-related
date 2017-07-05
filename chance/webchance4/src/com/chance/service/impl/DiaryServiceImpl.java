package com.chance.service.impl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chance.domain.Diary;
import com.chance.domain.Pager;
import com.chance.monitor.model.DiaryModel;
import com.chance.monitor.model.DiaryModelAndSize;
import com.chance.remote.RemoteDataAccessException;
import com.chance.remote.RemoteDiaryHandler;
import com.chance.service.DiaryService;
import com.chance.service.impl.BusinessException;

@Service("diaryService")
public class DiaryServiceImpl  implements DiaryService{

	private static final Logger log = LoggerFactory.getLogger(DiaryServiceImpl.class);
	
	@Autowired
	private RemoteDiaryHandler remoteDiaryHandler;
	
	@Override
	public Diary get(Integer userid, Integer diaryid) throws BusinessException {
		DiaryModel diaryModel = null;
		try {
			diaryModel = remoteDiaryHandler.getSingleDiary(userid, diaryid);
		} catch (RemoteDataAccessException e) {
			throw new BusinessException(e.getMessage(),e);
		}
		return remote2Domain(diaryModel);
	}

	/**
	 * 获取当前页码对应的开始偏移量
	 * @return
	 */
	private Integer getCurrentStartId(Pager<Diary> pager,HttpSession session) {
		int index = pager.getCurrentPage()-1;
		if(index < 0)
			index = 0;
		List<Integer> idIndexBuffer = (List<Integer>) session.getAttribute("diaryidIndex");
		if(index >= idIndexBuffer.size())
			index = idIndexBuffer.size() - 1;
		return idIndexBuffer.get(index);
	}
	
	/**
	 * 分析：
	 * 由于只有一个DiaryServiceImpl对象，对应的成员变量idIndexBuffer也只有一份，所有idIndexBuffer是各个线程共享的。
	 * 每个线程访问第一页的时候都会重置idIndexBuffer的值。如线程A、B刚刚各自先后查询并访问了第一页，那么idIndexBuffer中的值就是B查询后的idIndexBuffer值，
	 * 线程A对应的idIndexBuffer取值就没了。此时线程A点击查看第二页，查看到的就是线程B中的第二页的值。
	 * 
	 * 解决方案：每个线程各自一份idIndexBuffer。
	 * 
	 * 问题：一个用户在使用该系统时可以发送多个请求，这些请求可能由同一个线程处理或者不同的线程处理（取决于系统负载），也就是说一个用户可能对于多个线程。
	 * 并不是说一个用户对于一个唯一的线程。所以上述解决方案有问题。
	 * 
	 * 终极方案：每个session一份idIndexBuffer。
	 */
	@Override
	public Pager<Diary> getByPager(HttpSession session,Pager<Diary> pager,String username) throws BusinessException{

		log.info("[pager in DiaryServiceImpl.getByPager]"+pager);
		if(pager.getCurrentPage() == 1){ //在第一页

				DiaryModelAndSize diaryModelAndSize = null;
				try {
					diaryModelAndSize = remoteDiaryHandler.GetDiary(username, 0, pager.getPageSize());
				} catch (RemoteDataAccessException e) {
					throw new BusinessException(e.getMessage(),e);
				}
				//缓存DiaryID索引
				List<Integer> ids = idList2IdIndex(diaryModelAndSize.getDiaryIds(),pager.getPageSize());
				session.setAttribute("diaryidIndex", ids);	//缓存到session中
				
				//设置总记录数
				pager.setTotalCount(diaryModelAndSize.getSize());
				//获取Diary，并设置到pager的dataList
				List<DiaryModel> diaryModelList = diaryModelAndSize.getDms();
				List<Diary> diaryList = new ArrayList<Diary>();
				for (int i = 0; i < diaryModelList.size(); i++) {
					diaryList.add(remote2Domain(diaryModelList.get(i)));
				}
				pager.setDataList(diaryList);			
		}
		else {
			//获取Diary，并设置到pager的dataList
			DiaryModelAndSize diaryModelAndSize = null;
			try{
				diaryModelAndSize = remoteDiaryHandler.GetDiary(username, getCurrentStartId(pager,session), pager.getPageSize());
			} catch (RemoteDataAccessException e) {
				throw new BusinessException(e.getMessage(),e);
			}
			List<DiaryModel> diaryModelList = diaryModelAndSize.getDms();
			List<Diary> diaryList = new ArrayList<Diary>();
			for (int i = 0; i < diaryModelList.size(); i++) {
				diaryList.add(remote2Domain(diaryModelList.get(i)));
			}
			pager.setDataList(diaryList);
		}	
		return pager;
	}

	@Override
	public void pushDiary(int userid, int diaryid, int type, String desc,
			String passwd) throws BusinessException {
		try {
			remoteDiaryHandler.pushChance(userid, diaryid, type, desc, passwd);
		} catch (RemoteDataAccessException e) {
			throw new BusinessException(e.getMessage(),e);
		}
	}
	
	/**
	 * 工具方法：服务器返回的模型转为本地模型
	 * @return
	 */
	private Diary remote2Domain(DiaryModel remoteModel){
		if (remoteModel == null) {
			return null;
		}
		Diary result = new Diary();
		result.setAddress(remoteModel.getAddress());
		result.setContent(remoteModel.getContent());
		result.setDiaryId(remoteModel.getDiaryId());
		result.setPicUrl(remoteModel.getUrl());
		result.setProperty(remoteModel.getProperty());
		result.setTime(remoteModel.getTime());
		result.setType(remoteModel.getType());
		result.setUserId(remoteModel.getAuthorId());
		result.setUserName(remoteModel.getName());
		return result;
	}
	
	/**
	 * 工具方法：将id列表转换为一个长度为totalPage的日志id索引数组
	 * @param idsList
	 * @return
	 */
	private List<Integer> idList2IdIndex(List<Integer> idsList,int pageSize ) {
		if(idsList == null || idsList.isEmpty())
			return Collections.emptyList(); 

		//查询总记录数目
		int lotalLength = idsList.size();

		if(lotalLength > 0){
			//计算一共有多少页
			int totalPage = lotalLength % pageSize == 0 ? lotalLength / pageSize:lotalLength / pageSize + 1;
				 
			//创建一个totalPage+1大小的数组，第一个位置存放总长度，余下totalPage个位置里面存储着每一页的第一条记录对于的diaryid
			List<Integer> diaryidIndex = new ArrayList<Integer>(totalPage+1);;
				 
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

	@Override
	public void delete(Integer userid, Integer diaryid)
			throws BusinessException {
		try {
			remoteDiaryHandler.deleteSingleDiary(userid, diaryid);
		} catch (RemoteDataAccessException e) {
			throw new BusinessException(e.getMessage(),e);
		}
		
	}

}
