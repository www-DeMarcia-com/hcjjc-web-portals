package com.hsjjc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Picture implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8818878886573339568L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column
	private String url;//图片路径
	@Column(name="commodityid")
	private Long  commodityid;//商品图片
	@Column
	private Integer rank;//图片排序
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrl() {
		return url/*==null?"":url.trim()+""*/;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Long getCommodityid() {
		return commodityid;
	}
	public void setCommodityid(Long commodityid) {
		this.commodityid = commodityid;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	
}
