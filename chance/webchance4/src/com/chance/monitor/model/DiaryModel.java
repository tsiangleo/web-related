package com.chance.monitor.model;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DiaryModel")
public class DiaryModel {
	private Integer 	diaryId;	//日志id
	private Integer 	authorId;	//日志所属用户id
	private String 	name;		//日志所属用户昵称
	private String 	content;	//日志内容
	private String 	url;		//日志图片url
	private String 	address;	//发表日志的地址
	private Long 		time;		//发表日志的时间
	private Integer 	type;		//发表日志的类型（说说，链接等等）
	private Integer 	property;	//发表日志的权限（公开，私有）
	public Integer getDiaryId() {
		return diaryId;
	}
	public void setDiaryId(Integer diaryId) {
		this.diaryId = diaryId;
	}
	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	
	
}
