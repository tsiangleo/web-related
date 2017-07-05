package com.chance.remote;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.chance.monitor.model.ReportBangBangListResult;
import com.chance.monitor.model.ReportBangBangModel;
import com.chance.monitor.model.ResultInfo;
import com.chance.util.MonitorConstants;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class RemoteReportBangBangHandler {
	private static final Logger log = LoggerFactory
			.getLogger(RemoteReportBangBangHandler.class);
	private static ObjectMapper objectMapper = new ObjectMapper();
	static {
		objectMapper.configure(
				DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.setSerializationInclusion(Include.NON_NULL);
	}

	// 返回本地数据库中的所有时间id
	// private List<Long> getLocalTimeList(){
	// ReportBangBangService reportBangBangService = new
	// ReportBangBangServiceImpl();
	// return reportBangBangService.getReportTime(true);
	// }
	/**
	 * 批量获取ReportBangBang
	 * 
	 * @param number
	 *            批量获取反馈的个数
	 * @param offset
	 *            偏移
	 * @return
	 * @throws RemoteDataAccessException
	 */
	public List<ReportBangBangModel> getReportBangBang(int number, Long offset)
			throws RemoteDataAccessException {
		String url = MonitorConstants.GET_REPORTBANGBANG_URL + "/number/"
				+ number + "/offset/" + offset + MonitorConstants.PostfixName;
		log.info("【getReportBangBang URL】：" + url);

		String jsonString = RemoteSupport.getJsonStringByKey(url,
				MonitorConstants.GET_REPORTBANGBANG_KEY);
		ReportBangBangListResult result;
		try {
			result = objectMapper.readValue(jsonString,
					ReportBangBangListResult.class);
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

		if (result != null
				&& result.getStatus() == MonitorConstants.RESULT_STATUS_SUCCESS) {
			// List<ReportBangBangModel> dataList = result.getData();
			// List<ReportBangBangModel> newdataList = new
			// ArrayList<ReportBangBangModel>();
			// List<Long> localTimeList = getLocalTimeList();
			// for (int i = 0; i < dataList.size(); i++) {
			// ReportBangBangModel temp = dataList.get(i);
			// long time = temp.getTime();
			// if(!localTimeList.contains(time)) { //去掉本地已经有的数据
			// newdataList.add(temp);
			// }
			// }
			// result.setData(newdataList);
			return result.getData();
		} else {
			log.error("批量获取ReportBangBang时出现异常:" + result.getStatus()
					+ result.getDesc() + ",number:" + number + ",offset:"
					+ offset);
			throw new RemoteDataAccessException(result.getStatus(),
					"批量获取ReportBangBang时出现异常:" + result.getDesc());
		}
	}

	/**
	 * 删除单条ReportBangBang
	 * 
	 * @param time
	 * @return
	 * @throws IOException
	 */
	public void deleteReportBangBang(Long time) throws RemoteDataAccessException {
		String url = MonitorConstants.DELETE_REPORTBANGBANG_URL + "/time/"
				+ time + MonitorConstants.PostfixName;
		log.info("【deleteReportBangBang URL】：" + url);

		ResultInfo tempResult = RemoteSupport.getResultInfo(url,
				MonitorConstants.DELETE_REPORTBANGBANG_KEY);

		if (tempResult == null
				|| MonitorConstants.RESULT_STATUS_SUCCESS != tempResult
						.getStatus()) {
			log.error("删除单条ReportBangBang时出现异常:" + tempResult.getStatus()
					+ tempResult.getDesc() + ",time:" + time);
			throw new RemoteDataAccessException(tempResult.getStatus(),
					"删除单条ReportBangBang时出现异常:" + tempResult.getDesc());
		}
	}

}
