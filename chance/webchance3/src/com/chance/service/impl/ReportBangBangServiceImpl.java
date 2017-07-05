package com.chance.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chance.dao.ReportBangBangDao;
import com.chance.dao.impl.DBRuntimeException;
import com.chance.domain.Admin;
import com.chance.domain.Pager;
import com.chance.domain.ReportBangBang;
import com.chance.monitor.model.ReportBangBangModel;
import com.chance.remote.RemoteDataAccessException;
import com.chance.remote.RemoteBangBangHandler;
import com.chance.remote.RemoteReportBangBangHandler;
import com.chance.service.ReportBangBangService;
import com.chance.service.impl.BusinessException;
import com.chance.util.MonitorConstants;


@Service("reportBangBangService")
public class ReportBangBangServiceImpl implements ReportBangBangService {
	private static final Logger log = LoggerFactory.getLogger(ReportBangBangServiceImpl.class);
	@Autowired
	private ReportBangBangDao reportBangBangDao;
	@Autowired
	private RemoteReportBangBangHandler remoteReportBangBangHandler;
	
	@Autowired
	private RemoteBangBangHandler remoteBangBangHandler;
	
	@Override
	public Pager<ReportBangBang> getCheckedByPager(Pager<ReportBangBang> pager)
			throws BusinessException {
		if (pager == null) {
			pager = new Pager<ReportBangBang>();
		}
		pager.setOrderBy("time");
		pager.setPropertyName("checkResult");
		pager.setPropertyValue(MonitorConstants.FLAG_CHECKRESULT_CHECKED);
		Pager<ReportBangBang> resultPager;
		try {
			log.info("[pager in ReportBangBangServiceImpl.getCheckedByPager]"+pager);
			resultPager =  reportBangBangDao.getByPager(pager);
		} catch (DBRuntimeException e) {
			throw new BusinessException(e.getMessage(),e);
		}
		return resultPager;
	}

	@Override
	public Pager<ReportBangBang> getUncheckedByPager(Pager<ReportBangBang> pager)
			throws BusinessException {
		if (pager == null) {
			pager = new Pager<ReportBangBang>();
		}
		pager.setOrderBy("time");
		pager.setPropertyName("checkResult");
		pager.setPropertyValue(MonitorConstants.FLAG_CHECKRESULT_UNCHECKED);
		log.info("[pager in ReportBangBangServiceImpl.getUncheckedByPager]"+pager);
		Pager<ReportBangBang> resultPager =  reportBangBangDao.getByPager(pager);
		return resultPager;
	}

	/**
	 * 检查服务器有无最新的ReportBangBang，有则插入到本地数据库，并返回获取到的最新的数据条数；否则若没有最新的或者发生其他错误则抛出异常。
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public Integer checkLatest() throws BusinessException {

		List<ReportBangBangModel> list = null;
		try {
			list = remoteReportBangBangHandler.getReportBangBang(MonitorConstants.TOTAL_NUMBER_FROM_SERVER,  0L);
		} catch (RemoteDataAccessException e) {
			throw new BusinessException(e.getMessage(),e);
		}
		
		if(list != null && !list.isEmpty()){
			Integer newRecords = 0;
			//去重，然后插入本地
			for (int i = 0; i < list.size(); i++) {
				if(!reportBangBangDao.isExist("time", list.get(i).getTime())){//本地没有就保存
					reportBangBangDao.save(remote2Domain(list.get(i)));
					newRecords ++;
				}
			}
			if(newRecords == 0){
				throw new BusinessException("暂时没有最新的ReportBangBang");
			}else {
				return newRecords;
			}
		}
		else {
			throw new BusinessException("暂时没有最新的ReportBangBang");
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
		reportBangBangDao.update(property, identity);
	}
	
	/**
	 * 工具方法：服务器返回的模型转为本地模型
	 * @param reportBangBangModel
	 * @return
	 */
	private ReportBangBang remote2Domain(ReportBangBangModel reportBangBangModel){
		if (reportBangBangModel == null) {
			return null;
		}
		ReportBangBang reportBangBang = new ReportBangBang();
		reportBangBang.setByUserId(reportBangBangModel.getAuthorId());
		reportBangBang.setByUserName(reportBangBangModel.getAuthorName());
		reportBangBang.setBangBangId(reportBangBangModel.getBangId());
		reportBangBang.setTime(reportBangBangModel.getTime());
		reportBangBang.setUserId(reportBangBangModel.getUserId());
		reportBangBang.setUserName(reportBangBangModel.getUserName());
		reportBangBang.setReason(reportBangBangModel.getReason());
		return reportBangBang;
	}

	
	@Override
	@Transactional
	public void delete(Long time,Integer id, Admin admin) throws BusinessException {
		remoteReportBangBangHandler.deleteReportBangBang(time);
		this.updateToCheckedStatus(id, admin);
	}

	@Override
	@Transactional
	public void deleteReportBangBangAndBangBang(long deleteTime, int reportBangBangid,
			int userid, int diaryid, Admin admin) throws BusinessException {
		this.delete(deleteTime, reportBangBangid, admin);
		remoteBangBangHandler.deleteSingleBangBang(userid, diaryid);
	}

	@Override
	public void deleteChecked(int reportBangBangid) throws BusinessException {
		reportBangBangDao.delete(reportBangBangid);
	}
}
