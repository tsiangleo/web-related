package com.chance.domain;


import java.util.List;

public class ShopInfoPending {
	private Integer id;			//商家的id,为负数
    private String shopName;   //店铺名称（必填）
    private String shopDesc;   //店铺描述
    private String shopType;   //店铺类型（必填）
    private List<String> shopTags; //店铺的标签(必填)，与上面的Type共同想当于下面上传的shopCatg
    private String  shopPhone;  //店铺电话
    private List<String> shopLogo;   //店铺商标(必填)
    private List<String> shopLicense; //店铺营业执照(必填)
    private List<String> idCard;    //个人身份证(必填)
    private List<String> shopDeco;   //店铺装饰（必填）
    private String shopAddr; //商铺地址（必填）
    private Integer status;//商店是否被认证的状态
    private String slogan; //商店的标语
    private Long time;//商店创建的时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopDesc() {
		return shopDesc;
	}
	public void setShopDesc(String shopDesc) {
		this.shopDesc = shopDesc;
	}
	public String getShopType() {
		return shopType;
	}
	public void setShopType(String shopType) {
		this.shopType = shopType;
	}
	public List<String> getShopTags() {
		return shopTags;
	}
	public void setShopTags(List<String> shopTags) {
		this.shopTags = shopTags;
	}
	public String getShopPhone() {
		return shopPhone;
	}
	public void setShopPhone(String shopPhone) {
		this.shopPhone = shopPhone;
	}
	public List<String> getShopLogo() {
		return shopLogo;
	}
	public void setShopLogo(List<String> shopLogo) {
		this.shopLogo = shopLogo;
	}
	public List<String> getShopLicense() {
		return shopLicense;
	}
	public void setShopLicense(List<String> shopLicense) {
		this.shopLicense = shopLicense;
	}
	public List<String> getIdCard() {
		return idCard;
	}
	public void setIdCard(List<String> idCard) {
		this.idCard = idCard;
	}
	public List<String> getShopDeco() {
		return shopDeco;
	}
	public void setShopDeco(List<String> shopDeco) {
		this.shopDeco = shopDeco;
	}
	public String getShopAddr() {
		return shopAddr;
	}
	public void setShopAddr(String shopAddr) {
		this.shopAddr = shopAddr;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getSlogan() {
		return slogan;
	}
	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}

	 
    
}
