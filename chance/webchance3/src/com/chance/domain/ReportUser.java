package com.chance.domain;

import java.util.List;

/**
 * 被举报用户
 * @author michael
 *
 */
public class ReportUser {
	
//	private Integer	C_ReportUser_ID;			//主键，自增
//	private Integer	C_ReportUser_UserID;		//被举报用户的id
//	private String	C_ReportUser_UserName;		//被举报用户的昵称
//	private Integer	C_ReportUser_ByUserID;		//举报用户的ID
//	private	String	C_ReportUser_ByUserName;	//举报用户的昵称
//	private	Long	C_ReportUser_Time;			//被举报的时间
//	private String	C_ReportUser_Type;			//被举报的类型
//	private String	C_ReportUser_Reason;		//举报原因 
//	
//	private Integer C_ReportUser_CheckResult = 0;		//0待处理，1删除对应的日志，2忽略对应的日志，。
//	private Integer C_ReportUser_CheckAdminID;		//处理该日志的管理员的ID
//	private String 	C_ReportUser_CheckTime;			//处理时间
	
	
	private Integer	id;				//主键，自增
	private Integer	userId;			//举报用户id
	private String	userName;		//举报用户昵称
	private Integer	byUserId;		//被举报用户id
	private	String	byUserName;		//被举报用户的昵称
	private	Long	time;			//被举报日志的时间
	private Integer	type;			//被举报的类型
	private String	reason;			//被举报原因 
	private List<ReportChatMsg> msgs;//举报时候附带的聊天信息
	
	private Integer checkResult = 0;	//0待处理，1删除对应的日志，2忽略对应的日志，。
	private Integer checkAdminId = 0;		//处理该日志的管理员的ID
	private Long 	checkTime = 0L;			//处理时间
	
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public List<ReportChatMsg> getMsgs() {
		return msgs;
	}
	public void setMsgs(List<ReportChatMsg> msgs) {
		this.msgs = msgs;
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
		return "ReportUser [id=" + id + ", userId=" + userId + ", userName="
				+ userName + ", byUserId=" + byUserId + ", byUserName="
				+ byUserName + ", time=" + time + ", type=" + type
				+ ", reason=" + reason + ", msgs=" + msgs + ", checkResult="
				+ checkResult + ", checkAdminId=" + checkAdminId
				+ ", checkTime=" + checkTime + "]";
	}
	
	
}
