package com.chance.monitor.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Latlng")
public class Latlng {

	private int userId;
	private double lat;
	private double lng;
	public double getLat() {
		return lat;
	}
	@XmlElement
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	@XmlElement
	public void setLng(double lng) {
		this.lng = lng;
	}
	public int getUserId() {
		return userId;
	}
	@XmlElement
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
