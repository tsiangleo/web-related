package com.chance.domain;

import java.util.List;
public class TeamInfoPending {
	private int 			ownCid;		//等待审核的组所有人id
	private String 			ownName;	//等待审核的组所有人的昵称
	private int 			teamId;		//等待审核的组id
	private String 			teamName;	//等待审核的组的名称
    private String 			icon;		//等待审核的组的图标
    private List<String>    tags;   	//等待审核的组标签
    private String          desc;   	//等待审核的组描述
    private long            time; 		//等待审核的组创建时间
    private int        	 	level;  	//等待审核的组等级
	public int getOwnCid() {
		return ownCid;
	}
	public void setOwnCid(int ownCid) {
		this.ownCid = ownCid;
	}
	public String getOwnName() {
		return ownName;
	}
	public void setOwnName(String ownName) {
		this.ownName = ownName;
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}

    
    
}
