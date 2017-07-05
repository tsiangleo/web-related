package com.chance.domain;
/**
 * 日志评论
 * @author michael
 *
 */
public class DiaryComment {

	private Integer		id;			//主键(一条日志评论也可以由byUserId、diaryId和commentId标志)
	private Long 	commentId;		//评论id
	private Integer diaryId;		//被评论的日志id
	private Integer byUserId;		//被评论的日志的作者的id
	private Integer userId;			//发评论的用户的id
	private String 	userName;		//发评论的用户的昵称
	private String 	content;		//评论内容
	private Long 	time;			//评论时间
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getCommentId() {
		return commentId;
	}
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
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
	public Integer getByUserId() {
		return byUserId;
	}
	public void setByUserId(Integer byUserId) {
		this.byUserId = byUserId;
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
	
//	private	Integer	C_DiaryComment_ID;			//主键，自增
//	private Long	C_DiaryComment_CommentID;	//评论id
//	private	Integer	C_DiaryComment_DiaryID;		//被评论的日志id
//	private	Integer	C_DiaryComment_ByUserID;	//发评论的用户的id
//	private Integer	C_DiaryComment_UserID;		//被评论的日志的作者的id
//	private String	C_DiaryComment_UserName;	//发评论的用户的昵称
//	private	String	C_DiaryComment_Content;		//评论内容
//	private	String	C_DiaryComment_Time;		//评论时间
	
	
	

	
}
