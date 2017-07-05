package com.chance.domain;

public class ReportBangBang {
	
//	private Integer	C_ReportBangBang_ID;			//主键，自增
//	private Integer	C_ReportBangBang_UserID;		//被举报帮帮所属用户id
//	private String	C_ReportBangBang_UserName;		//被举报帮帮所属用户的昵称
//	private Integer	C_ReportBangBang_ByUserID;		//举报帮帮的用户
//	private	String	C_ReportBangBang_ByUserName;	//举报帮帮所属用户的昵称
//	private	Long	C_ReportBangBang_Time;			//被举报帮帮的时间
//	private Integer	C_ReportBangBang_BangBangID;	//被举报帮帮id
//	
//	private Integer C_ReportBangBang_CheckResult = 0;		//0待处理，1删除对应的帮帮，2忽略对应的帮帮，。
//	private Integer C_ReportBangBang_CheckAdminID;		//处理该帮帮的管理员的ID
//	private String 	C_ReportBangBang_CheckTime;		//处理时间
	

	private Integer	id;				//主键，自增
	private Integer	userId;			//举报用户id
	private String	userName;		//举报用户昵称
	private Integer	byUserId;		//被举报用户id
	private	String	byUserName;		//被举报用户的昵称
	private	Long	time;			//被举报帮帮的时间
	private Integer	bangBangId;		//被举报帮帮id
	private String 	reason;
	
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
	public Integer getBangBangId() {
		return bangBangId;
	}
	public void setBangBangId(Integer bangBangId) {
		this.bangBangId = bangBangId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
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

	
	
	
}