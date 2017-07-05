package com.chance.domain;

/**
 * 帮帮
 * @author michael
 *
 */
public class BangBang {

//	private	Integer	C_BangBang_ID;			//主键，自增
//	private Integer	C_BangBang_BangBangID;	//帮帮id
//	private	Integer	C_BangBang_UserID;		//帮帮所属用户id
//	private	String	C_BangBang_UserName;	//帮帮所属用户昵称
//	private String	C_BangBang_Content;		//帮帮内容
//	private String	C_BangBang_PicUrl;		//帮帮图片url
//	private	String	C_BangBang_StartTime;	//发表帮帮的时间
//	private	String	C_BangBang_EndTime;		//
//	private	String	C_BangBang_IsActive;	//
	
	private Integer	id;			//主键(一条帮帮也可以由BangBangid和userId标志)
	private Integer bangBangId;	//帮帮id
    private Integer userId;		//帮帮所属用户id
    private String 	userName;	//帮帮所属用户昵称
    private String 	content;	//帮帮内容
    private String 	picUrl;		//帮帮图片url
    private Long 	startTime;	//发表帮帮的时间
    private Long 	endTime;
    private Boolean isActive;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBangBangId() {
		return bangBangId;
	}
	public void setBangBangId(Integer bangBangId) {
		this.bangBangId = bangBangId;
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
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
    
    
}
