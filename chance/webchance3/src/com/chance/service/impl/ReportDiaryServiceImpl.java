package com.chance.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chance.dao.ReportDiaryDao;
import com.chance.dao.impl.DBRuntimeException;
import com.chance.domain.Admin;
import com.chance.domain.Pager;
import com.chance.domain.ReportDiary;
import com.chance.monitor.model.ReportDiaryModel;
import com.chance.remote.RemoteDataAccessException;
import com.chance.remote.RemoteDiaryHandler;
import com.chance.remote.RemoteReportDiaryHandler;
import com.chance.service.ReportDiaryService;
import com.chance.service.impl.BusinessException;
import com.chance.util.MonitorConstants;


@Service("reportDiaryService")
public class ReportDiaryServiceImpl implements ReportDiaryService {
	private static final Logger log = LoggerFactory.getLogger(ReportDiaryServiceImpl.class);
	@Autowired
	private ReportDiaryDao reportDiaryDao;
	@Autowired
	private RemoteReportDiaryHandler remoteReportDiaryHandler;
	
	@Autowired
	private RemoteDiaryHandler remoteDiaryHandler;
	
	@Override
	public Pager<ReportDiary> getCheckedByPager(Pager<ReportDiary> pager)
			throws BusinessException {
		if (pager == null) {
			pager = new Pager<ReportDiary>();
		}
		pager.setOrderBy("time");
		pager.setPropertyName("checkResult");
		pager.setPropertyValue(MonitorConstants.FLAG_CHECKRESULT_CHECKED);
		Pager<ReportDiary> resultPager;
		try {
			log.info("[pager in ReportDiaryServiceImpl.getCheckedByPager]"+pager);
			resultPager =  reportDiaryDao.getByPager(pager);
		} catch (DBRuntimeException e) {
			throw new BusinessException(e.getMessage(),e);
		}
		return resultPager;
	}

	@Override
	public Pager<ReportDiary> getUncheckedByPager(Pager<ReportDiary> pager)
			throws BusinessException {
		if (pager == null) {
			pager = new Pager<ReportDiary>();
		}
		pager.setOrderBy("time");
		pager.setPropertyName("checkResult");
		pager.setPropertyValue(MonitorConstants.FLAG_CHECKRESULT_UNCHECKED);
		log.info("[pager in ReportDiaryServiceImpl.getUncheckedByPager]"+pager);
		Pager<ReportDiary> resultPager =  reportDiaryDao.getByPager(pager);
		return resultPager;
	}

	/**
	 * 检查服务器有无最新的ReportDiary，有则插入到本地数据库，并返回获取到的最新的数据条数；否则若没有最新的或者发生其他错误则抛出异常。
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public Integer checkLatest() throws BusinessException {

		List<ReportDiaryModel> list = null;
		try {
			list = remoteReportDiaryHandler.getReportDiary(MonitorConstants.TOTAL_NUMBER_FROM_SERVER,  0L);
		} catch (RemoteDataAccessException e) {
//			throw new BusinessException("向服务器获取最新的ReportDiary时发生异常",e);
			throw new BusinessException(e.getMessage(),e);
		}
		
		if(list != null && !list.isEmpty()){
			Integer newRecords = 0;
			//去重，然后插入本地
			for (int i = 0; i < list.size(); i++) {
				if(!reportDiaryDao.isExist("time", list.get(i).getTime())){//本地没有就保存
					reportDiaryDao.save(remote2Domain(list.get(i)));
					newRecords ++;
				}
			}
			if(newRecords == 0){
				throw new BusinessException("暂时没有最新的ReportDiary");
			}else {
				return newRecords;
			}
		}
		else {
			throw new BusinessException("暂时没有最新的ReportDiary");
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
		reportDiaryDao.update(property, identity);
	}
	
	/**
	 * 工具方法：服务器返回的模型转为本地模型
	 * @param reportDiaryModel
	 * @return
	 */
	private ReportDiary remote2Domain(ReportDiaryModel reportDiaryModel){
		if (reportDiaryModel == null) {
			return null;
		}
		ReportDiary reportDiary = new ReportDiary();
		reportDiary.setByUserId(reportDiaryModel.getAuthorId());
		reportDiary.setByUserName(reportDiaryModel.getAuthorName());
		reportDiary.setDiaryId(reportDiaryModel.getDiaryId());
		reportDiary.setTime(reportDiaryModel.getTime());
		reportDiary.setUserId(reportDiaryModel.getUserId());
		reportDiary.setUserName(reportDiaryModel.getUserName());
		return reportDiary;
	}

	
	@Override
	@Transactional
	public void delete(Long time,Integer id, Admin admin) throws BusinessException {
		remoteReportDiaryHandler.deleteReportDiary(time);
		this.updateToCheckedStatus(id, admin);
	}

	@Override
	@Transactional
	public void deleteReportDiaryAndDiary(long deleteTime, int reportDiaryid,
			int userid, int diaryid, Admin admin) throws BusinessException {
		this.delete(deleteTime, reportDiaryid, admin);
		remoteDiaryHandler.deleteSingleDiary(userid, diaryid);
	}

	@Override
	public Map<Integer, Integer> reporteeTopList() {
		return reportDiaryDao.reporteeTopList();
	}

	@Override
	public Map<Integer, Integer> diaryList(Integer byUserId) {
		return reportDiaryDao.diaryList(byUserId);
	}

	@Override
	public Map<Integer, Integer> reporterList(Integer byUserId, Integer diaryId) {
		return reportDiaryDao.reporterList(byUserId, diaryId);
	}

	@Override
	public Map<Integer, Integer> reporterTopList() {
		return reportDiaryDao.reporterTopList();
	}

	@Override
	public Map<Integer, Integer> reporteeList(Integer UserId) {
		return reportDiaryDao.reporteeList(UserId);
	}

	@Override
	public Map<Integer, Integer> diaryList(Integer UserId, Integer byUserId) {
		return reportDiaryDao.diaryList(UserId, byUserId);
	}

	@Override
	public void deleteChecked(int reportDiaryid) throws BusinessException {
		reportDiaryDao.delete(reportDiaryid);
	}
}
