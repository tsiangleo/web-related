package com.chance.domain;

public class ReportChatMsg {
	
	private Integer	id;				//主键，自增
	private Integer	reportUserId;	//外键：ReportUser的主键
	
    private Integer	sendCID; 	//发送者CID
    private Integer	receiveCID; //接收者CID
    private String	msgContent; //消息内容
    private Integer	type;   	//消息类型(区分是文字消息0，图片消息1，音频消息2，位置消息3)
    private Long	sendTime; 	//消息发送时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getReportUserId() {
		return reportUserId;
	}
	public void setReportUserId(Integer reportUserId) {
		this.reportUserId = reportUserId;
	}
	public Integer getSendCID() {
		return sendCID;
	}
	public void setSendCID(Integer sendCID) {
		this.sendCID = sendCID;
	}
	public Integer getReceiveCID() {
		return receiveCID;
	}
	public void setReceiveCID(Integer receiveCID) {
		this.receiveCID = receiveCID;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getSendTime() {
		return sendTime;
	}
	public void setSendTime(Long sendTime) {
		this.sendTime = sendTime;
	}
	@Override
	public String toString() {
		return "ReportChatMsg [id=" + id + ", reportUserId=" + reportUserId
				+ ", sendCID=" + sendCID + ", receiveCID=" + receiveCID
				+ ", msgContent=" + msgContent + ", type=" + type
				+ ", sendTime=" + sendTime + "]";
	}
    
    
	
}
