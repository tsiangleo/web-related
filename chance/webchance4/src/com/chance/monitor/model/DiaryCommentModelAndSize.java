package com.chance.monitor.model;

import java.util.List;

public class DiaryCommentModelAndSize {
	private int size;
	private List<DiaryCommentModel> dcms;
	private List<Integer> diaryCommentIds;
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public List<DiaryCommentModel> getDcms() {
		return dcms;
	}
	public void setDcms(List<DiaryCommentModel> dcms) {
		this.dcms = dcms;
	}
	public List<Integer> getDiaryCommentIds() {
		return diaryCommentIds;
	}
	public void setDiaryCommentIds(List<Integer> diaryCommentIds) {
		this.diaryCommentIds = diaryCommentIds;
	}

}
