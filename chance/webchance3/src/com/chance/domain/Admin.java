package com.chance.domain;


/**
 * 管理员
 * @author michael
 *
 */
public class Admin {

	private	Integer	id;			//主键，自增
	private	String	loginName;	//登录名
	private	String	password;	//登陆密码 ,应该是摘要
	private	String	name;		//管理员姓名
	private Integer	level;		//管理员等级
	private Boolean isLocked;	//是否锁定
	private Long 	createTime;	 //创建时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Boolean getIsLocked() {
		return isLocked;
	}
	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	
	
//	private	Integer	C_Admin_ID;			//主键，自增
//	private	String	C_Admin_LoginName;	//登录名
//	private	String	C_Admin_Pwd;		//登陆密码 ,应该是摘要
//	private	String	C_Admin_Name;		//管理员姓名
//	private Integer	C_Admin_Level;		//管理员等级
//
//	private String 	C_Admin_CreateTime;
//	private Integer C_Admin_Delete = 0;
	
	

	
}
