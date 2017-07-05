package com.chance.monitor.model;

public class TencentResult {
	private int status;
	private String message;
	private TencentResultDetail result;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public TencentResultDetail getResult() {
		return result;
	}
	public void setResult(TencentResultDetail result) {
		this.result = result;
	}
	
	
}
class TencentResultDetail{
	private TencentLocation location;
	private String address;
	private FormattedAddress formatted_address;
	private TencentAddressComponent address_component;
	private AdInfo ad_info;
	private AddressReference address_reference;
	public TencentLocation getLocation() {
		return location;
	}
	public void setLocation(TencentLocation location) {
		this.location = location;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public FormattedAddress getFormatted_address() {
		return formatted_address;
	}
	public void setFormatted_address(FormattedAddress formatted_address) {
		this.formatted_address = formatted_address;
	}
	public TencentAddressComponent getAddress_component() {
		return address_component;
	}
	public void setAddress_component(TencentAddressComponent address_component) {
		this.address_component = address_component;
	}
	public AdInfo getAd_info() {
		return ad_info;
	}
	public void setAd_info(AdInfo ad_info) {
		this.ad_info = ad_info;
	}
	public AddressReference getAddress_reference() {
		return address_reference;
	}
	public void setAddress_reference(AddressReference address_reference) {
		this.address_reference = address_reference;
	}
	
	
}

class TencentLocation{
	private double lng;
	private double lat;
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	
}

class FormattedAddress{
	private String recommend;
	private String rough;
	public String getRecommend() {
		return recommend;
	}
	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
	public String getRough() {
		return rough;
	}
	public void setRough(String rough) {
		this.rough = rough;
	}
}

class AdInfo{
	private String adcode;
	private String name;
	private TencentLocation location;
	private String nation;
	private String province;
	private String city;
	private String district;
	public String getAdcode() {
		return adcode;
	}
	public void setAdcode(String adcode) {
		this.adcode = adcode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TencentLocation getLocation() {
		return location;
	}
	public void setLocation(TencentLocation location) {
		this.location = location;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	
	
}

class AddressReference{
	private AddressReferenceDetail crossroad;
	private AddressReferenceDetail street;
	private AddressReferenceDetail street_number;
	private AddressReferenceDetail landmark_l2;
	public AddressReferenceDetail getCrossroad() {
		return crossroad;
	}
	public void setCrossroad(AddressReferenceDetail crossroad) {
		this.crossroad = crossroad;
	}
	public AddressReferenceDetail getStreet() {
		return street;
	}
	public void setStreet(AddressReferenceDetail street) {
		this.street = street;
	}
	public AddressReferenceDetail getStreet_number() {
		return street_number;
	}
	public void setStreet_number(AddressReferenceDetail street_number) {
		this.street_number = street_number;
	}
	public AddressReferenceDetail getLandmark_l2() {
		return landmark_l2;
	}
	public void setLandmark_l2(AddressReferenceDetail landmark_l2) {
		this.landmark_l2 = landmark_l2;
	}
	
	
}

class AddressReferenceDetail{
	private String title;
	private TencentLocation location;
	private double _distance;
	private String _dir_desc;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public TencentLocation getLocation() {
		return location;
	}
	public void setLocation(TencentLocation location) {
		this.location = location;
	}
	public double get_distance() {
		return _distance;
	}
	public void set_distance(double _distance) {
		this._distance = _distance;
	}
	public String get_dir_desc() {
		return _dir_desc;
	}
	public void set_dir_desc(String _dir_desc) {
		this._dir_desc = _dir_desc;
	}
	
	
}

class TencentAddressComponent{
	private String nation;
	private String province;
	private String city;
	private String district;
	private String street;
	private String street_number;
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getStreet_number() {
		return street_number;
	}
	public void setStreet_number(String street_number) {
		this.street_number = street_number;
	}
	
}
