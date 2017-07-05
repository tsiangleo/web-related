package com.chance.monitor.model;

public class OtherInfoMode {
	private long lastLoveTime;//上一次取消暗恋的时间
	private int attentionLimitNum;//关注个数上限
	private int loveLimitNum;//暗恋个数上限
	private int markLimitNum;//标记个数上限
	private int friendLimitNum;//好友个数上限
	private long sleepLoveTime;//暗恋的冷冻时间
	private int createTeamLimitNum;//创建小组的上限
	private int attenTeamLimitNum;//参加小组的上限
	
	
	private int diaryCount;//发表日志数
	private int freshNewsCount;//产生新鲜事数
	private int meetMemoryCount;//书写记忆的个数
	private int bangbangCount;//发出的帮帮个数
	private long diarySerial;//上一篇日志是时间
	
	private int recvDiscountcardCount;//接受到优惠券的个数
	private int collectTradeCount;//收藏的商店个数
	private int tradeConsumeRecordCount;//与商家交易个数
	
	
	public long getLastLoveTime() {
		return lastLoveTime;
	}
	public void setLastLoveTime(long lastLoveTime) {
		this.lastLoveTime = lastLoveTime;
	}
	public int getAttentionLimitNum() {
		return attentionLimitNum;
	}
	public void setAttentionLimitNum(int attentionLimitNum) {
		this.attentionLimitNum = attentionLimitNum;
	}
	public int getLoveLimitNum() {
		return loveLimitNum;
	}
	public void setLoveLimitNum(int loveLimitNum) {
		this.loveLimitNum = loveLimitNum;
	}
	public int getMarkLimitNum() {
		return markLimitNum;
	}
	public void setMarkLimitNum(int markLimitNum) {
		this.markLimitNum = markLimitNum;
	}
	public int getFriendLimitNum() {
		return friendLimitNum;
	}
	public void setFriendLimitNum(int friendLimitNum) {
		this.friendLimitNum = friendLimitNum;
	}
	public long getSleepLoveTime() {
		return sleepLoveTime;
	}
	public void setSleepLoveTime(long sleepLoveTime) {
		this.sleepLoveTime = sleepLoveTime;
	}
	public int getCreateTeamLimitNum() {
		return createTeamLimitNum;
	}
	public void setCreateTeamLimitNum(int createTeamLimitNum) {
		this.createTeamLimitNum = createTeamLimitNum;
	}
	public int getAttenTeamLimitNum() {
		return attenTeamLimitNum;
	}
	public void setAttenTeamLimitNum(int attenTeamLimitNum) {
		this.attenTeamLimitNum = attenTeamLimitNum;
	}
	public int getDiaryCount() {
		return diaryCount;
	}
	public void setDiaryCount(int diaryCount) {
		this.diaryCount = diaryCount;
	}
	public int getFreshNewsCount() {
		return freshNewsCount;
	}
	public void setFreshNewsCount(int freshNewsCount) {
		this.freshNewsCount = freshNewsCount;
	}
	public int getMeetMemoryCount() {
		return meetMemoryCount;
	}
	public void setMeetMemoryCount(int meetMemoryCount) {
		this.meetMemoryCount = meetMemoryCount;
	}
	public int getBangbangCount() {
		return bangbangCount;
	}
	public void setBangbangCount(int bangbangCount) {
		this.bangbangCount = bangbangCount;
	}
	public long getDiarySerial() {
		return diarySerial;
	}
	public void setDiarySerial(long diarySerial) {
		this.diarySerial = diarySerial;
	}
	public int getRecvDiscountcardCount() {
		return recvDiscountcardCount;
	}
	public void setRecvDiscountcardCount(int recvDiscountcardCount) {
		this.recvDiscountcardCount = recvDiscountcardCount;
	}
	public int getCollectTradeCount() {
		return collectTradeCount;
	}
	public void setCollectTradeCount(int collectTradeCount) {
		this.collectTradeCount = collectTradeCount;
	}
	public int getTradeConsumeRecordCount() {
		return tradeConsumeRecordCount;
	}
	public void setTradeConsumeRecordCount(int tradeConsumeRecordCount) {
		this.tradeConsumeRecordCount = tradeConsumeRecordCount;
	}
}
