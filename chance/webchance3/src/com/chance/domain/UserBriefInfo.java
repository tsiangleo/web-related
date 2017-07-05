package com.chance.domain;


public class UserBriefInfo {
	private int userId;//用户的id
	private String nickName;//用户的昵称
	private String loginName;//用户的登录名
	private int sex;//用户性别
	private String url;//用户头像地址
	private String status;//用户签名
//	private double latitude;//用户的所在位置纬度
//	private double longitude;//用户所在地址的经度
	private String address;
	private long birthday;//用户的生日
	private long lastLoginTime;//用户上一次的登录时间
	private long lastMsgTime;//用户上一次的发消息时间
	
	//2015.6.3新加
	private boolean online;//是否在线
	private String onlineTime;//总在线时长 (毫秒)
	private int apkVersion;//客户端版本号
//	private double registerLatitude;//注册的纬度(为0提示）
//	private double registerLongitude;//注册的经度（为0提示）
	private String registerAddress;
	private long registerTime;//注册的时间（为0）
	private String mobileType;//使用的手机型号
	private String androidSdk;//安卓的版本号
	//2015.6.5新加
	private boolean active;//是否被屏蔽， 为true代表激活状态，为false代表屏蔽状态
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getBirthday() {
		return birthday;
	}
	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}
	public long getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public long getLastMsgTime() {
		return lastMsgTime;
	}
	public void setLastMsgTime(long lastMsgTime) {
		this.lastMsgTime = lastMsgTime;
	}
	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	public String getOnlineTime() {
		return onlineTime;
	}
	public void setOnlineTime(String onlineTime) {
		this.onlineTime = onlineTime;
	}
	public int getApkVersion() {
		return apkVersion;
	}
	public void setApkVersion(int apkVersion) {
		this.apkVersion = apkVersion;
	}
	public String getRegisterAddress() {
		return registerAddress;
	}
	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
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
	public String getAndroidSdk() {
		return androidSdk;
	}
	public void setAndroidSdk(String androidSdk) {
		this.androidSdk = androidSdk;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
}
