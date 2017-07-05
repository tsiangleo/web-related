package com.chance.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chance.dao.FeedBackDao;
import com.chance.dao.impl.DBRuntimeException;
import com.chance.domain.Admin;
import com.chance.domain.Pager;
import com.chance.domain.FeedBack;
import com.chance.monitor.model.FeedbackModel;
import com.chance.remote.RemoteDataAccessException;
import com.chance.remote.RemoteFeedBackHandler;
import com.chance.service.FeedBackService;
import com.chance.service.impl.BusinessException;
import com.chance.util.MonitorConstants;


@Service("feedBackService")
public class FeedBackServiceImpl implements FeedBackService {
	private static final Logger log = LoggerFactory.getLogger(FeedBackServiceImpl.class);
	@Autowired
	private FeedBackDao feedBackDao;
	@Autowired
	private RemoteFeedBackHandler remoteFeedBackHandler;
	
	@Override
	public Pager<FeedBack> getCheckedByPager(Pager<FeedBack> pager)
			throws BusinessException {
		if (pager == null) {
			pager = new Pager<FeedBack>();
		}
		pager.setOrderBy("time");
		pager.setPropertyName("checkResult");
		pager.setPropertyValue(MonitorConstants.FLAG_CHECKRESULT_CHECKED);
		Pager<FeedBack> resultPager;
		try {
			log.info("[pager in FeedBackServiceImpl.getCheckedByPager]"+pager);
			resultPager =  feedBackDao.getByPager(pager);
		} catch (DBRuntimeException e) {
			throw new BusinessException(e.getMessage(),e);
		}
		return resultPager;
	}

	@Override
	public Pager<FeedBack> getUncheckedByPager(Pager<FeedBack> pager)
			throws BusinessException {
		if (pager == null) {
			pager = new Pager<FeedBack>();
		}
		pager.setOrderBy("time");
		pager.setPropertyName("checkResult");
		pager.setPropertyValue(MonitorConstants.FLAG_CHECKRESULT_UNCHECKED);
		log.info("[pager in FeedBackServiceImpl.getUncheckedByPager]"+pager);
		Pager<FeedBack> resultPager =  feedBackDao.getByPager(pager);
		return resultPager;
	}

	/**
	 * 检查服务器有无最新的FeedBack，有则插入到本地数据库，并返回获取到的最新的数据条数；否则若没有最新的或者发生其他错误则抛出异常。
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public Integer checkLatest() throws BusinessException {

		List<FeedbackModel> list = null;
		try {
			list = remoteFeedBackHandler.getFeedBack(MonitorConstants.TOTAL_NUMBER_FROM_SERVER,  0L);
		} catch (RemoteDataAccessException e) {
			throw new BusinessException(e.getMessage(),e);
		}
		
		if(list != null && !list.isEmpty()){
			Integer newRecords = 0;
			//去重，然后插入本地
			for (int i = 0; i < list.size(); i++) {
				if(!feedBackDao.isExist("time", list.get(i).getTime())){//本地没有就保存
					feedBackDao.save(remote2Domain(list.get(i)));
					newRecords ++;
				}
			}
			if(newRecords == 0){
				throw new BusinessException("暂时没有最新的FeedBack");
			}else {
				return newRecords;
			}
		}
		else {
			throw new BusinessException("暂时没有最新的FeedBack");
		}
		
	}

	@Override
	public void updateToCheckedStatus(List<Integer> idList, Admin admin)
			throws BusinessException {
		for (int i = 0; i < idList.size(); i++) {
			updateToCheckedStatus(idList.get(i),admin);
		}
	}

	@Override
	public void updateToCheckedStatus(Integer id, Admin admin)
			throws BusinessException {
		Map<String, Object> property = new HashMap<String, Object>();
		property.put("checkResult", MonitorConstants.FLAG_CHECKRESULT_CHECKED);
		property.put("checkAdminId",admin.getId());
		property.put("checkTime", System.currentTimeMillis());
		Map<String, Object> identity = new HashMap<String, Object>();
		identity.put("id", id);
		feedBackDao.update(property, identity);
	}
	
	/**
	 * 工具方法：服务器返回的模型转为本地模型
	 * @param feedBackModel
	 * @return
	 */
	private FeedBack remote2Domain(FeedbackModel feedBackModel){
		if (feedBackModel == null) {
			return null;
		}
		FeedBack feedBack = new FeedBack();
		feedBack.setContent(feedBackModel.getContent());
		feedBack.setTime(feedBackModel.getTime());
		feedBack.setUserId(feedBackModel.getUserId());
		feedBack.setUserName(feedBackModel.getUserName());
		return feedBack;
	}

	
	@Override
	@Transactional
	public void delete(Long time,Integer id, Admin admin) throws BusinessException {
		remoteFeedBackHandler.deleteFeedBack(time);
		this.updateToCheckedStatus(id, admin);
	}

	@Override
	public void deleteChecked(int feedBackid) throws BusinessException {
		feedBackDao.delete(feedBackid);
	}
}
