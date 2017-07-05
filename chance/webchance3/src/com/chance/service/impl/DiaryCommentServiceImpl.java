package com.chance.service.impl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chance.domain.DiaryComment;
import com.chance.domain.Pager;
import com.chance.monitor.model.DiaryCommentModel;
import com.chance.monitor.model.DiaryCommentModelAndSize;
import com.chance.remote.RemoteDataAccessException;
import com.chance.remote.RemoteDiaryCommentHandler;
import com.chance.service.DiaryCommentService;
import com.chance.service.impl.BusinessException;

@Service("diaryCommentService")
public class DiaryCommentServiceImpl  implements DiaryCommentService{

	private static final Logger log = LoggerFactory.getLogger(DiaryCommentServiceImpl.class);

	@Autowired
	private RemoteDiaryCommentHandler remoteDiaryCommentHandler;

	@Override
	public DiaryComment get(int userid, int diaryid, int cid)
			throws BusinessException {
		DiaryCommentModel remote = null;
		try {
			remote = remoteDiaryCommentHandler.getSingleDiaryComment(userid, diaryid, cid);
		} catch (RemoteDataAccessException e) {
			throw new BusinessException(e.getMessage(),e);
		}
		return remote2Domain(remote);
	}

	@Override
	public void delete(int userid, int diaryid, int cid)
			throws BusinessException {
		try {
			remoteDiaryCommentHandler.delSingleDiaryComment(userid, diaryid, cid);
		} catch (RemoteDataAccessException e) {
			throw new BusinessException(e.getMessage(),e);
		}
		
	}
	
	/**
	 * 获取当前页码对应的开始偏移量
	 * @return
	 */
	private Integer getCurrentStartId(Pager<DiaryComment> pager,HttpSession session) {
		int index = pager.getCurrentPage()-1;
		if(index < 0)
			index = 0;
		List<Integer> idIndexBuffer = (List<Integer>) session.getAttribute("diaryCommentidIndex");
		if(index >= idIndexBuffer.size())
			index = idIndexBuffer.size() - 1;
		return idIndexBuffer.get(index);
	}
	
	@Override
	public Pager<DiaryComment> getByPager(HttpSession session,Pager<DiaryComment> pager, String username, int diaryid) throws BusinessException {

		log.info("[pager in DiaryServiceImpl.getByPager]"+pager);
		if(pager.getCurrentPage() == 1){ //在第一页

			DiaryCommentModelAndSize diaryCommentModelAndSize = null;
				try {
					diaryCommentModelAndSize = remoteDiaryCommentHandler.getDiaryComment(username, diaryid, 0, pager.getPageSize());
				} catch (RemoteDataAccessException e) {
					throw new BusinessException(e.getMessage(),e);
				}
				//缓存DiaryID索引
				List<Integer> ids = idList2IdIndex(diaryCommentModelAndSize.getDiaryCommentIds(),pager.getPageSize());
				session.setAttribute("diaryCommentidIndex", ids);	//缓存到session中
				
				//设置总记录数
				pager.setTotalCount(diaryCommentModelAndSize.getSize());
				//获取Diary，并设置到pager的dataList
				List<DiaryCommentModel> diaryCommentModel = diaryCommentModelAndSize.getDcms();
				List<DiaryComment> diaryCommentList = new ArrayList<DiaryComment>();
				for (int i = 0; i < diaryCommentModel.size(); i++) {
					diaryCommentList.add(remote2Domain(diaryCommentModel.get(i)));
				}
				pager.setDataList(diaryCommentList);			
		}
		else {
			//获取Diary，并设置到pager的dataList
			DiaryCommentModelAndSize diaryCommentModelAndSize = null;
			try{
				diaryCommentModelAndSize = remoteDiaryCommentHandler.getDiaryComment(username, diaryid, getCurrentStartId(pager,session), pager.getPageSize());
						
			} catch (RemoteDataAccessException e) {
				throw new BusinessException(e.getMessage(),e);
			}
			
			//获取Diary，并设置到pager的dataList
			List<DiaryCommentModel> diaryCommentModel = diaryCommentModelAndSize.getDcms();
			List<DiaryComment> diaryCommentList = new ArrayList<DiaryComment>();
			for (int i = 0; i < diaryCommentModel.size(); i++) {
				diaryCommentList.add(remote2Domain(diaryCommentModel.get(i)));
			}
			pager.setDataList(diaryCommentList);
		}	
		
		return pager;
	}
	
	
	/**
	 * 工具方法：服务器返回的模型转为本地模型
	 * @return
	 */
	private DiaryComment remote2Domain(DiaryCommentModel remoteModel){
		if (remoteModel == null) {
			return null;
		}
		DiaryComment result = new DiaryComment();
		result.setByUserId(remoteModel.getAuthorId());
		result.setCommentId(remoteModel.getCommentId());
		result.setContent(remoteModel.getContent());
		result.setDiaryId(remoteModel.getDiaryId());
		result.setTime(remoteModel.getTime());
		result.setUserId(remoteModel.getByUserId());
		result.setUserName(remoteModel.getByUserName());
		return result;
	}
	
	/**
	 * 工具方法：将id列表转换为一个长度为totalPage+1的日志评论id索引数组
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
}
