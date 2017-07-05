package com.chance.monitor.model;
public class ReportChatMsg {
    private int    sndCID; //发送者CID
    private int    rcvCID; //接收者CID
    private String msgCnt; //消息内容
    private int    type;   //消息类型(区分是文字消息0，图片消息1，音频消息2，位置消息3)
    private long   sndTime; //消息发送时间
    
    
	public int getSndCID() {
		return sndCID;
	}
	public void setSndCID(int sndCID) {
		this.sndCID = sndCID;
	}
	public int getRcvCID() {
		return rcvCID;
	}
	public void setRcvCID(int rcvCID) {
		this.rcvCID = rcvCID;
	}
	public String getMsgCnt() {
		return msgCnt;
	}
	public void setMsgCnt(String msgCnt) {
		this.msgCnt = msgCnt;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getSndTime() {
		return sndTime;
	}
	public void setSndTime(long sndTime) {
		this.sndTime = sndTime;
	}
    
    
}
