package com.chance.monitor.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ApkVersion")
public class ApkVersion {
	String  oldVersion;    
	String  newVersion; 
	String  fileSize;  
	String  fileURL; 
	String  updateTime;
	String  updateDescription;
	String  isUpdate;
	String  newVerCode;
	String  updateVersion;
	String  updateVerCode;
	
	public String getOldVersion() {
		return oldVersion;
	}
	@XmlElement
	public void setOldVersion(String oldVersion) {
		this.oldVersion = oldVersion;
	}
	public String getNewVersion() {
		return newVersion;
	}
	@XmlElement
	public void setNewVersion(String newVersion) {
		this.newVersion = newVersion;
	}
	public String getFileSize() {
		return fileSize;
	}
	@XmlElement
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileURL() {
		return fileURL;
	}
	@XmlElement
	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	@XmlElement
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateDescription() {
		return updateDescription;
	}
	@XmlElement
	public void setUpdateDescription(String updateDescription) {
		this.updateDescription = updateDescription;
	}
	public String getIsUpdate() {
		return isUpdate;
	}
	@XmlElement
	public void setIsUpdate(String isUpdate) {
		this.isUpdate = isUpdate;
	}
	public String getNewVerCode() {
		return newVerCode;
	}
	@XmlElement
	public void setNewVerCode(String newVerCode) {
		this.newVerCode = newVerCode;
	}
	public String getUpdateVersion() {
		return updateVersion;
	}
	@XmlElement
	public void setUpdateVersion(String updateVersion) {
		this.updateVersion = updateVersion;
	}
	public String getUpdateVerCode() {
		return updateVerCode;
	}
	@XmlElement
	public void setUpdateVerCode(String updateVerCode) {
		this.updateVerCode = updateVerCode;
	}
}
