package com.bootdo.system.domain;
/**
 * 商品详情
 * @author Administrator
 *
 */

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class CommodityDO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2935696943752515027L;
	private Long id;
	private String name;
	private BigDecimal price;//市场价
	private BigDecimal promotionprice;//促销价O
	private Long producerid;
	private String comefrom;//来自
	private Integer ispromotion;//是否促销 0---不是  1；是
	private String intro;//简介
	private Long commoditykindsid;//商品种类
	private String pictureurl;
	private Integer quantity;
	private Date createtime;
	private Date starttime;
	private Date endtime;
	private Integer isclose ;//是否下市 1---下市 0 ----没有
	
	private Integer ishot;//是否热销 0---不是  1；是
	private String specname;
	private String specvalue;
	public String getPictureurl() {
		return pictureurl;
	}
	public void setPictureurl(String pictureurl) {
		this.pictureurl = pictureurl;
	}
	public Integer getIshot() {
		return ishot;
	}
	public void setIshot(Integer ishot) {
		this.ishot = ishot;
	}
	
	public Integer getIspromotion() {
		return ispromotion;
	}
	public void setIspromotion(Integer ispromotion) {
		this.ispromotion = ispromotion;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getPromotionprice() {
		return promotionprice;
	}
	public void setPromotionprice(BigDecimal promotionprice) {
		this.promotionprice = promotionprice;
	}
	
	public String getComefrom() {
		return comefrom;
	}
	public void setComefrom(String comefrom) {
		this.comefrom = comefrom;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getSpecname() {
		return specname;
	}
	public void setSpecname(String specname) {
		this.specname = specname;
	}
	public String getSpecvalue() {
		return specvalue;
	}
	public void setSpecvalue(String specvalue) {
		this.specvalue = specvalue;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public Integer getIsclose() {
		return isclose;
	}
	public void setIsclose(Integer isclose) {
		this.isclose = isclose;
	}
	public Long getProducerid() {
		return producerid;
	}
	public void setProducerid(Long producerid) {
		this.producerid = producerid;
	}
	public Long getCommoditykindsid() {
		return commoditykindsid;
	}
	public void setCommoditykindsid(Long commoditykindsid) {
		this.commoditykindsid = commoditykindsid;
	}
	@Override
	public String toString() {
		return "CommodityDO [id=" + id + ", name=" + name + ", price=" + price + ", promotionprice=" + promotionprice
				+ ", producerid=" + producerid + ", comefrom=" + comefrom + ", ispromotion=" + ispromotion + ", intro="
				+ intro + ", commoditykindsid=" + commoditykindsid + ", pictureurl=" + pictureurl + ", quantity="
				+ quantity + ", createtime=" + createtime + ", starttime=" + starttime + ", endtime=" + endtime
				+ ", isclose=" + isclose + ", ishot=" + ishot + ", specname=" + specname + ", specvalue=" + specvalue
				+ "]";
	}
	
}
