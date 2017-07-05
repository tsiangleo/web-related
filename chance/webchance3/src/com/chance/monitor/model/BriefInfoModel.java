package com.chance.monitor.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "BriefInfoModel")
public class BriefInfoModel {
	private int userId;//用户的id
	private String nickName;//用户的昵称
	private String longName;//用户的登录名
	private int sex;//用户性别
	private String url;//用户头像地址
	private String status;//用户签名
	private double latitude;//用户的所在位置纬度
	private double longitude;//用户所在地址的经度
	private long birthday;//用户的生日
	private long lastLoginTime;//用户上一次的登录时间
	private long lastMsgTime;//用户上一次的发消息时间
	
	//2015.6.3新加
	private boolean online;//是否在线
	private long onlineTime;//总在线时长 (毫秒)
	private int apkVersion;//客户端版本号
	private double registerLatitude;//注册的纬度(为0提示）
	private double registerLongitude;//注册的经度（为0提示）
	private long registerTime;//注册的时间（为0）
	private String mobileType;//使用的手机型号
	private String androidSdk;//安卓的版本号
	//2015.6.5新加
	private boolean active;//是否被屏蔽

	public int getUserId() {
		return userId;
	}
	@XmlElement
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getNickName() {
		return nickName;
	}
	@XmlElement
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getLongName() {
		return longName;
	}
	@XmlElement
	public void setLongName(String longName) {
		this.longName = longName;
	}
	public int getSex() {
		return sex;
	}
	@XmlElement
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getUrl() {
		return url;
	}
	@XmlElement
	public void setUrl(String url) {
		this.url = url;
	}
	public String getStatus() {
		return status;
	}
	@XmlElement
	public void setStatus(String status) {
		this.status = status;
	}
	public double getLatitude() {
		return latitude;
	}
	@XmlElement
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	@XmlElement
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public long getBirthday() {
		return birthday;
	}
	@XmlElement
	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}
	
	public long getLastLoginTime() {
		return lastLoginTime;
	}
	@XmlElement
	public void setLastLoginTime(long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public long getLastMsgTime() {
		return lastMsgTime;
	}
	@XmlElement
	public void setLastMsgTime(long lastMsgTime) {
		this.lastMsgTime = lastMsgTime;
	}
	
	
	public double getRegisterLatitude() {
		return registerLatitude;
	}
	public void setRegisterLatitude(double registerLatitude) {
		this.registerLatitude = registerLatitude;
	}
	public double getRegisterLongitude() {
		return registerLongitude;
	}
	public void setRegisterLongitude(double registerLongitude) {
		this.registerLongitude = registerLongitude;
	}
	public long getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(long registerTime) {
		this.registerTime = registerTime;
	}
	public String getMobileType() {
		return mobileType;
	}
	public void setMobileType(String mobileType) {
		this.mobileType = mobileType;
	}
	public int getApkVersion() {
		return apkVersion;
	}
	public void setApkVersion(int apkVersion) {
		this.apkVersion = apkVersion;
	}
	public String getAndroidSdk() {
		return androidSdk;
	}
	public void setAndroidSdk(String androidSdk) {
		this.androidSdk = androidSdk;
	}
	public long getOnlineTime() {
		return onlineTime;
	}
	public void setOnlineTime(long onlineTime) {
		this.onlineTime = onlineTime;
	}
	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

	
	
}
