package com.chance.domain;

/**
 * 日志
 * @author michael
 *
 */
public class Diary {

//	private	Integer	C_Diary_ID;			//主键，自增
//	private Integer	C_Diary_DiaryID;	//日志id
//	private	Integer	C_Diary_UserID;		//日志所属用户id
//	private	String	C_Diary_UserName;	//日志所属用户昵称
//	private String	C_Diary_Content;	//日志内容
//	private String	C_Diary_PicUrl;		//日志图片url
//	private	String	C_Diary_Address;	//发表日志的地址
//	private	String	C_Diary_Time;		//发表日志的时间
//	private	String	C_Diary_Type;		//发表日志的类型（说说，链接等等）
//	private	String	C_Diary_Property; //= WebsocketConstants.FLAG_DIARY_PROPERTY_PRIVATE;	//发表日志的权限（公开，私有）
	
	private Integer		id;			//主键(一条日志也可以由diaryId和userId标志)
	private Integer 	diaryId;	//日志id
	private Integer 	userId;		//日志所属用户id
	private String 		userName;	//日志所属用户昵称
	private String 		content;	//日志内容
	private String 		picUrl;		//日志图片url
	private String 		address;	//发表日志的地址
	private Long 		time;		//发表日志的时间
	private Integer 	type;		//发表日志的类型（说说，链接等等） 0图片，1文本，2链接，3贴纸
	private Integer 	property;	//发表日志的权限（公开，私有） 0私有，1公开，2所有人
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDiaryId() {
		return diaryId;
	}
	public void setDiaryId(Integer diaryId) {
		this.diaryId = diaryId;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public Integer getProperty() {
		return property;
	}
	public void setProperty(Integer property) {
		this.property = property;
	}
	@Override
	public String toString() {
		return "Diary [id=" + id + ", diaryId=" + diaryId + ", userId="
				+ userId + ", userName=" + userName + ", content=" + content
				+ ", picUrl=" + picUrl + ", address=" + address + ", time="
				+ time + ", type=" + type + ", property=" + property + "]";
	}
	
	
	
}

