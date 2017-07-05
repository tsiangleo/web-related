package com.chance.monitor.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DiaryCommentModel")
public class DiaryCommentListResult {
	private int status;
	private String desc;
//	private List<DiaryCommentModel> data;
	
	private DiaryCommentModelAndSize data;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public DiaryCommentModelAndSize getData() {
		return data;
	}

	public void setData(DiaryCommentModelAndSize data) {
		this.data = data;
	}
	
	
	

}
