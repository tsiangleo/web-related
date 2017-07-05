package com.chance.monitor.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserLocation {
	private String administrative_area_level_1;
	private String country;
	private String locality;
	private String sublocality;
	private String route;
	private double lat;
	private double lng;
	private int cid;
	public String toString(){
		StringBuilder sb = new StringBuilder();
		if(country != null)
			sb.append(country);
		if(administrative_area_level_1 != null)
			sb.append(administrative_area_level_1);
		if(locality != null)
			sb.append(locality);
		if(sublocality != null)
			sb.append(sublocality);
		if(route != null)
			sb.append(route);
		return sb.toString();

	}
	public String getAdministrative_area_level_1() {
		return administrative_area_level_1;
	}
	public void setAdministrative_area_level_1(String administrative_area_level_1) {
		this.administrative_area_level_1 = administrative_area_level_1;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}

	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}


	public String getSublocality() {
		return sublocality;
	}
	public void setSublocality(String sublocality) {
		this.sublocality = sublocality;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	
	/**
	 * 2015-7-13新增
	 * @param json
	 * @return
	 */
	public static UserLocation tencentJsonToLocation(String json){
		Logger log = Logger.getLogger(UserLocation.class);
		UserLocation userLocation = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			System.out.println(json);
			TencentResult tencentResult = objectMapper.readValue(json, TencentResult.class);
			if(0 != tencentResult.getStatus()){
				throw new IOException("status is :"+tencentResult.getStatus());
			}
			userLocation = new UserLocation();
			userLocation.setAdministrative_area_level_1(tencentResult.getResult().getAddress_component().getProvince());
			userLocation.setCountry(tencentResult.getResult().getAddress_component().getNation());
			userLocation.setLocality(tencentResult.getResult().getAddress_component().getCity());
			userLocation.setSublocality(tencentResult.getResult().getAddress_component().getDistrict());
			userLocation.setRoute(tencentResult.getResult().getAddress_component().getStreet()+tencentResult.getResult().getAddress_component().getStreet_number());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			log.warn(e.getMessage(), e);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			log.warn(e.getMessage(), e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.warn(e.getMessage(), e);
		}
		return userLocation;
	}
	
	public static UserLocation JsonToLocation(String json){
		Logger log = Logger.getLogger(UserLocation.class);
		UserLocation userLocation = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			System.out.println(json);
			BaiduResult baiduResult = objectMapper.readValue(json, BaiduResult.class);
			if(0 != baiduResult.getStatus()){
				throw new IOException("status is :"+baiduResult.getStatus());
			}
			if(baiduResult.getResult().getFormatted_address() == null || baiduResult.getResult().getFormatted_address().trim().equals("")){
				log.warn("format address is null");
			}
			userLocation = new UserLocation();
			userLocation.setAdministrative_area_level_1(baiduResult.getResult().getAddressComponent().getProvince());
			userLocation.setCountry("中国");
			userLocation.setLocality(baiduResult.getResult().getAddressComponent().getCity());
			userLocation.setSublocality(baiduResult.getResult().getAddressComponent().getDistrict());
			userLocation.setRoute(baiduResult.getResult().getAddressComponent().getStreet()+baiduResult.getResult().getAddressComponent().getStreet_number());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			log.warn(e.getMessage(), e);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			log.warn(e.getMessage(), e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.warn(e.getMessage(), e);
		}
		return userLocation;
	}
	public static UserLocation XmlToLocation(InputStream is) throws DocumentException{
		SAXReader reader = new SAXReader();  
		Document doc = reader.read(is);  
		Element root = doc.getRootElement();  
		List<? extends Node> statuss = root.selectNodes("//status");
		
		if(statuss == null || statuss.size() == 0){
			Logger.getLogger(UserLocation.class).info(root.getText());
			return null;
		}
		//判断返回的状态是否正常
		Node node  = statuss.get(0);
		if(!node.getText().toLowerCase().contains("ok")){
			Logger.getLogger(UserLocation.class).info(node.getText());
			return null;
		}else{
			Logger.getLogger(UserLocation.class).info(node.getText());
		}

		UserLocation location = new UserLocation();

		//获取country
		List<? extends Node> 	results = root.selectNodes("result[type=\"country\"]");
		if(results != null && results.size() != 0){
			node  = results.get(0);
			if(node != null){
				location.setCountry(((Element)node.selectNodes("address_component[type=\"country\"]").get(0)).elementText("long_name"));
			}
		}
		


		//获取administrative_area_level_1
		results = root.selectNodes("result[type=\"administrative_area_level_1\"]");
		if(results == null || results.size() != 0){
			node  = results.get(0);
			if(node != null){
				location.setAdministrative_area_level_1(((Element)node.selectNodes("address_component[type=\"administrative_area_level_1\"]").get(0)).elementText("long_name"));
				//获取基于省的近似gps坐标
				location.setLat(Float.parseFloat(((Element)node.selectNodes("geometry[location_type=\"APPROXIMATE\"]/location").get(0)).elementText("lat")));
				location.setLng(Float.parseFloat(((Element)node.selectNodes("geometry[location_type=\"APPROXIMATE\"]/location").get(0)).elementText("lng")));
			}           
		}
		
		//如果有locality获取locality 
		results = root.selectNodes("result[type=\"locality\"]");

		if(results != null && results.size() != 0){
			node  = results.get(0);
			if(node != null){
				location.setLocality(((Element)node.selectNodes("address_component[type=\"locality\"]").get(0)).elementText("long_name"));
			}

		}

		//如果有locality获取locality 
		results = root.selectNodes("result[type=\"sublocality\"]");

		if(results != null && results.size() != 0){
			node  = results.get(0);
			if(node != null){
				location.setSublocality(((Element)node.selectNodes("address_component[type=\"sublocality\"]").get(0)).elementText("long_name"));
			}

		}

		//如果有route获取route 
		results = root.selectNodes("result[type=\"route\"]");

		if(results != null && results.size() != 0){
			node  = results.get(0);
			if(node != null){
				location.setRoute(((Element)node.selectNodes("address_component[type=\"route\"]").get(0)).elementText("long_name"));
			}

		}

		return location;
	}
	
}
