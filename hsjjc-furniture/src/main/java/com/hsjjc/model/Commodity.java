package com.hsjjc.model;
/**
 * 商品详情
 * @author Administrator
 *
 */

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="commodity")
public class Commodity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6090067159831466827L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column
	private String name;
	@Column
	private BigDecimal price;//市场价
	@Column
	private BigDecimal promotionprice;//促销价O
	//	@Column
	//	private String producerid;//生产商

	@ManyToOne
	@JoinColumn(name = "producerid")
	private Producer producer;
	@Column
	private String comefrom;//来自
	@Column(length=1)
	private Integer ispromotion;//是否促销 0---不是  1；是
	//	@Column
	//	private String specificationid;//规格
	/*@ManyToOne
	@JoinColumn(name = "specificationid")
	private Specification specification;*/
	@Column(length=6000)
	@Basic(fetch = FetchType.LAZY)
	private String intro;//简介
/*	@Column
	private String remark;//备注
*/	@ManyToOne
	@JoinColumn(name = "commoditykindsid")
	private CommodityKinds commoditykinds;//商品种类
	/*@Column
	private String pictureid;//图片地址
*/	/*@OneToMany(mappedBy ="commodityid",cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
	private Set<Picture> pictures;*/
	@Column
	private String pictureurl;
	@Column
	private Integer quantity;
	@Column
	private Date createtime;
	@Column
	private Date starttime;
	@Column
	private Date endtime;
	@Column(length=1)
	private Integer isclose ;//是否下市 1---下市 0 ----没有
	
	@Column(length=1)
	private Integer ishot;//是否热销 0---不是  1；是
	@Column(length=1000)
	@Basic(fetch = FetchType.LAZY)
	private String specname;
	@Column(length=3000)
	@Basic(fetch = FetchType.LAZY)
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
	public Producer getProducer() {
		return producer;
	}
	public void setProducer(Producer producer) {
		this.producer = producer;
	}
	
	public String getComefrom() {
		return comefrom;
	}
	public void setComefrom(String comefrom) {
		this.comefrom = comefrom;
	}
	/*public Specification getSpecification() {
		return specification;
	}
	public void setSpecification(Specification specification) {
		this.specification = specification;
	}*/
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	
/*	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}*/
	
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
	public CommodityKinds getCommoditykinds() {
		return commoditykinds;
	}
	public void setCommoditykinds(CommodityKinds commoditykinds) {
		this.commoditykinds = commoditykinds;
	}
    /*public String getPictureid() {
		return pictureid;
	}
	public void setPictureid(String pictureid) {
		this.pictureid = pictureid;
	}*/
	/*public Set<Picture> getPictures() {
		return pictures;
	}
	public void setPictures(Set<Picture> pictures) {
		this.pictures = pictures;
	}*/
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
}
