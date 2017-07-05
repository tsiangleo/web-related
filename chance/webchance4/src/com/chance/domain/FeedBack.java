package com.chance.domain;

/**
 * 反馈
 * @author michael
 *
 */
public class FeedBack {

//	private	Integer	C_FeedBack_ID;		//主键
//	private	Integer	C_FeedBack_UserID;	//反馈用户id
//	private	String	C_FeedBack_UserName;//反馈用户昵称
//	private	String	C_FeedBack_Cotent;	//反馈内容
//	private	Long	C_FeedBack_Time;	//反馈时间
//	
//	private Integer C_FeedBack_CheckResult = 0;		
//	private Integer C_FeedBack_CheckAdminID;	//处理该反馈的管理员的ID
//	private String 	C_FeedBack_CheckTime;		//处理时间
	
	
	private Integer	id;				//主键，自增
	private Integer	userId;			//反馈用户id
	private String	userName;		//反馈用户昵称
	private	String	content;			//反馈内容
	private	Long	time;			//反馈时间
	
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
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
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
