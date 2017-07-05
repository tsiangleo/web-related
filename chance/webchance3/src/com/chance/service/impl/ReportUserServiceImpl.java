package com.chance.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chance.dao.ReportUserDao;
import com.chance.dao.impl.DBRuntimeException;
import com.chance.domain.Admin;
import com.chance.domain.Pager;
import com.chance.domain.ReportChatMsg;
import com.chance.domain.ReportUser;
import com.chance.monitor.model.ReportClientModel;
import com.chance.remote.RemoteDataAccessException;
import com.chance.remote.RemoteReportUserHandler;
import com.chance.remote.RemoteSupport;
import com.chance.service.ReportUserService;
import com.chance.service.impl.BusinessException;
import com.chance.util.MonitorConstants;


@Service("reportUserService")
public class ReportUserServiceImpl implements ReportUserService {
	private static final Logger log = LoggerFactory.getLogger(ReportUserServiceImpl.class);
	@Autowired
	private ReportUserDao reportUserDao;
	@Autowired
	private RemoteReportUserHandler remoteReportUserHandler;
	
	@Override
	public Pager<ReportUser> getCheckedByPager(Pager<ReportUser> pager)
			throws BusinessException {
		if (pager == null) {
			pager = new Pager<ReportUser>();
		}
		pager.setOrderBy("time");
		pager.setPropertyName("checkResult");
		pager.setPropertyValue(MonitorConstants.FLAG_CHECKRESULT_CHECKED);
		Pager<ReportUser> resultPager;
		try {
			log.info("[pager in ReportUserServiceImpl.getCheckedByPager]"+pager);
			resultPager =  reportUserDao.getByPager(pager);
		} catch (DBRuntimeException e) {
			throw new BusinessException(e.getMessage(),e);
		}
		return resultPager;
	}

	@Override
	public Pager<ReportUser> getUncheckedByPager(Pager<ReportUser> pager)
			throws BusinessException {
		if (pager == null) {
			pager = new Pager<ReportUser>();
		}
		pager.setOrderBy("time");
		pager.setPropertyName("checkResult");
		pager.setPropertyValue(MonitorConstants.FLAG_CHECKRESULT_UNCHECKED);
		log.info("[pager in ReportUserServiceImpl.getUncheckedByPager]"+pager);
		Pager<ReportUser> resultPager =  reportUserDao.getByPager(pager);
		return resultPager;
	}

	/**
	 * 检查服务器有无最新的ReportUser，有则插入到本地数据库，并返回获取到的最新的数据条数；否则若没有最新的或者发生其他错误则抛出异常。
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public Integer checkLatest() throws BusinessException {

		List<ReportClientModel> list = null;
		try {
			list = remoteReportUserHandler.getReportUser(MonitorConstants.TOTAL_NUMBER_FROM_SERVER,  0L);
		} catch (RemoteDataAccessException e) {
			throw new BusinessException(e.getMessage(),e);
		}
		
		if(list != null && !list.isEmpty()){
			Integer newRecords = 0;
			//去重，然后插入本地
			for (int i = 0; i < list.size(); i++) {
				if(!reportUserDao.isExist("time", list.get(i).getTime())){//本地没有就保存
					reportUserDao.save(remote2Domain(list.get(i)));
					newRecords ++;
				}
			}
			if(newRecords == 0){
				throw new BusinessException("暂时没有最新的ReportUser");
			}else {
				return newRecords;
			}
		}
		else {
			throw new BusinessException("暂时没有最新的ReportUser");
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
		reportUserDao.update(property, identity);
	}
	
	/**
	 * 工具方法：服务器返回的模型转为本地模型
	 */
	private ReportUser remote2Domain(ReportClientModel remote){
		if (remote == null) {
			return null;
		}
		ReportUser reportUser = new ReportUser();
		reportUser.setByUserId(remote.getByReportUserId());
		reportUser.setByUserName(remote.getByReportUserName());
		reportUser.setTime(remote.getTime());
		reportUser.setUserId(remote.getUserId());
		reportUser.setUserName(remote.getUserName());
		reportUser.setReason(remote.getReportReason());
		reportUser.setType(remote.getReportType());
		reportUser.setMsgs(remote2Domain(remote.getMsgs()));
		return reportUser;
	}
	
	/**
	 * 工具方法：服务器返回的模型转为本地模型
	 */
	private ReportChatMsg remote2Domain(com.chance.monitor.model.ReportChatMsg remote){
		if(remote == null){
			return null;
		}
		ReportChatMsg result = new ReportChatMsg();
		result.setMsgContent(remote.getMsgCnt());
		result.setReceiveCID(remote.getRcvCID());
		result.setSendCID(remote.getSndCID());
		result.setSendTime(remote.getSndTime());
		result.setType(remote.getType());
		return result;
	}
	/**
	 * 工具方法：服务器返回的模型转为本地模型
	 */
	private List<ReportChatMsg> remote2Domain(List<com.chance.monitor.model.ReportChatMsg> remote){
		if(remote == null)
			return null;
		List<ReportChatMsg> result = new ArrayList<ReportChatMsg>();
		for (int i = 0; i < remote.size(); i++) {
			result.add(remote2Domain(remote.get(i)));
		}
		return result;
	}
	
	@Override
	@Transactional
	public void delete(Long time,Integer id, Admin admin) throws BusinessException {
		remoteReportUserHandler.deleteReportUser(time);
		this.updateToCheckedStatus(id, admin);
	}

	@Override
	public void deleteChecked(int reportUserid) throws BusinessException {
		reportUserDao.delete(reportUserid);
	}

	@Override
	public ReportUser getDetail(int id) throws BusinessException {
		ReportUser reportUser = reportUserDao.get(id, true);
		if(reportUser.getMsgs() != null && !reportUser.getMsgs().isEmpty()){
			List<ReportChatMsg> msgList = reportUser.getMsgs();
			for (int i = 0; i < msgList.size(); i++) {
				ReportChatMsg msg = msgList.get(i);
				if(msg.getType() == 1){	//1图片
					msg.setMsgContent(RemoteSupport.getChatPicURL(msg.getMsgContent()));
				} else if(msg.getType() == 2){	//2音频消息
//					msg.setMsgContent(RemoteSupport.getChatPicURL(msg.getMsgContent()));
				}else if(msg.getType() == 3){	//3位置消息
					String[] address = msg.getMsgContent().split(",");
					if(address.length >= 2){
						Double la =  Double.parseDouble(address[address.length-1]); //维度
						Double lo =  Double.parseDouble(address[address.length-2]);	//经度
						log.info("维度:"+la+",经度:"+lo);
						msg.setMsgContent(RemoteSupport.getAddress(la, lo));
					}
				}				
			}
		}
		return reportUser;
	}
}
