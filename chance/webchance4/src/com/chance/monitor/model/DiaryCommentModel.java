package com.chance.monitor.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DiaryCommentModel")
public class DiaryCommentModel {
	private long commentId;
	private int diaryId;
	private int authorId;	//被评论的日志的作者的id
	private int byUserId;	//发表评论的用户ID
	private String byUserName;	//发表评论的用户昵称
	private String content;
	private long time;
	
	public long getCommentId() {
		return commentId;
	}
	@XmlElement
	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}
	public int getDiaryId() {
		return diaryId;
	}
	@XmlElement
	public void setDiaryId(int diaryId) {
		this.diaryId = diaryId;
	}
	public int getAuthorId() {
		return authorId;
	}
	@XmlElement
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public int getByUserId() {
		return byUserId;
	}
	@XmlElement
	public void setByUserId(int byUserId) {
		this.byUserId = byUserId;
	}
	public String getByUserName() {
		return byUserName;
	}
	@XmlElement
	public void setByUserName(String byUserName) {
		this.byUserName = byUserName;
	}
	public String getContent() {
		return content;
	}
	@XmlElement
	public void setContent(String content) {
		this.content = content;
	}
	public long getTime() {
		return time;
	}
	@XmlElement
	public void setTime(long time) {
		this.time = time;
	}
}
