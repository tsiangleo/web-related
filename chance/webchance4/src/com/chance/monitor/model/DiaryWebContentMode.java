package com.chance.monitor.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "DiaryWebContentMode")
public class DiaryWebContentMode {

	private String nickname;
	private String address;
	private String avatarUrl;
	private String picUrl;
	private List<String> withAvatarsUrl;
	private int praiseNum;
	private int commentNum;
	private String cotent;
	
	public String getNickname() {
		return nickname;
	}
	@XmlElement
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getAddress() {
		return address;
	}
	@XmlElement
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	@XmlElement
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public String getPicUrl() {
		return picUrl;
	}
	@XmlElement
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public List<String> getWithAvatarsUrl() {
		return withAvatarsUrl;
	}
	@XmlElement
	public void setWithAvatarsUrl(List<String> withAvatarsUrl) {
		this.withAvatarsUrl = withAvatarsUrl;
	}
	public int getPraiseNum() {
		return praiseNum;
	}
	@XmlElement
	public void setPraiseNum(int praiseNum) {
		this.praiseNum = praiseNum;
	}
	public int getCommentNum() {
		return commentNum;
	}
	@XmlElement
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	public String getCotent() {
		return cotent;
	}
	@XmlElement
	public void setCotent(String cotent) {
		this.cotent = cotent;
	}
}
