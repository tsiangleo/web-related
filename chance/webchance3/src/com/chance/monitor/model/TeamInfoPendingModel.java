package com.chance.monitor.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "TeamInfoPendingModel")
public class TeamInfoPendingModel {
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
	@XmlElement
	public void setOwnCid(int ownCid) {
		this.ownCid = ownCid;
	}
	public String getOwnName() {
		return ownName;
	}
	@XmlElement
	public void setOwnName(String ownName) {
		this.ownName = ownName;
	}
	public int getTeamId() {
		return teamId;
	}
	@XmlElement
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	@XmlElement
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getIcon() {
		return icon;
	}
	@XmlElement
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public List<String> getTags() {
		return tags;
	}
	@XmlElement
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public String getDesc() {
		return desc;
	}
	@XmlElement
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public long getTime() {
		return time;
	}
	@XmlElement
	public void setTime(long time) {
		this.time = time;
	}
	public int getLevel() {
		return level;
	}
	@XmlElement
	public void setLevel(int level) {
		this.level = level;
	}
}
