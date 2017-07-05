package com.chance.monitor.model;

import java.util.List;

public class DiaryModelAndSize {
	private int size;
	private List<DiaryModel> dms;
	private List<Integer> diaryIds;
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public List<DiaryModel> getDms() {
		return dms;
	}
	public void setDms(List<DiaryModel> dms) {
		this.dms = dms;
	}
	public List<Integer> getDiaryIds() {
		return diaryIds;
	}
	public void setDiaryIds(List<Integer> diaryIds) {
		this.diaryIds = diaryIds;
	}
}
