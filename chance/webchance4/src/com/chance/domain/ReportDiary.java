package com.chance.domain;

import com.chance.util.MonitorConstants;

/**
 * 被举报日志 实体
 * @author michael
 *
 */
public class ReportDiary {
	
//	private Integer	C_ReportDiary_ID;			//主键，自增
//	private Integer	C_ReportDiary_UserID;		//被举报日志所属用户id
//	private String	C_ReportDiary_UserName;		//被举报日志所属用户的昵称
//	private Integer	C_ReportDiary_ByUserID;		//举报日志的用户
//	private	String	C_ReportDiary_ByUserName;	//举报日志所属用户的昵称
//	private	Long	C_ReportDiary_Time;			//被举报日志的时间
//	private Integer	C_ReportDiary_DiaryID;		//被举报日志id
//	
//	private Integer C_ReportDiary_CheckResult = 0;		//0待处理，1删除对应的日志，2忽略对应的日志，。
//	private Integer C_ReportDiary_CheckAdminID;		//处理该日志的管理员的ID
//	private String 	C_ReportDiary_CheckTime;		//处理时间
	
	
	private Integer	id;				//主键，自增
	private Integer	userId;			//举报用户id
	private String	userName;		//举报用户昵称
	private Integer	byUserId;		//被举报用户id
	private	String	byUserName;		//被举报用户的昵称
	private	Long	time;			//被举报日志的时间
	private Integer	diaryId;		//被举报日志id
	
	private Integer checkResult = MonitorConstants.FLAG_CHECKRESULT_UNCHECKED;	//0待处理，1已处理。
	private Integer checkAdminId = 0;		//处理该日志的管理员的ID
	private Long 	checkTime= 0L;			//处理时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getByUserId() {
		return byUserId;
	}
	public void setByUserId(Integer byUserId) {
		this.byUserId = byUserId;
	}
	public String getByUserName() {
		return byUserName;
	}
	public void setByUserName(String byUserName) {
		this.byUserName = byUserName;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public Integer getDiaryId() {
		return diaryId;
	}
	public void setDiaryId(Integer diaryId) {
		this.diaryId = diaryId;
	}
	public Integer getCheckResult() {
		return checkResult;
	}
	public void setCheckResult(Integer checkResult) {
		this.checkResult = checkResult;
	}
	public Integer getCheckAdminId() {
		return checkAdminId;
	}
	public void setCheckAdminId(Integer checkAdminId) {
		this.checkAdminId = checkAdminId;
	}
	public Long getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Long checkTime) {
		this.checkTime = checkTime;
	}
	@Override
	public String toString() {
		return "ReportDiary [id=" + id + ", userId=" + userId + ", userName="
				+ userName + ", byUserId=" + byUserId + ", byUserName="
				+ byUserName + ", time=" + time + ", diaryId=" + diaryId
				+ ", checkResult=" + checkResult + ", checkAdminId="
				+ checkAdminId + ", checkTime=" + checkTime + "]";
	}
	
}
