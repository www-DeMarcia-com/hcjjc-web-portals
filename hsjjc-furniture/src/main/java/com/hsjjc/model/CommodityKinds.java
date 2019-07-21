package com.hsjjc.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商品种类-----床，衣柜，桌子
 * @author Administrator
 *
 */
@Entity
@Table(name="commoditykinds")
public class CommodityKinds implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2953453685211528011L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column
	private String name;
	/*@Column
	private String picture;*///图片地址
	/*@OneToMany(mappedBy ="commoditykinds",cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
	private Set<Commodity> commoditys;*/
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
/*	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}*/
	/*public Set<Commodity> getCommoditys() {
		return commoditys;
	}
	public void setCommoditys(Set<Commodity> commoditys) {
		this.commoditys = commoditys;
	}*/
	
	
}
