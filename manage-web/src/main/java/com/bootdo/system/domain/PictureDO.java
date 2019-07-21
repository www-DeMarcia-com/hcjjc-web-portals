package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * 新闻内容
 * @author Administrator
 *
 */

public class PictureDO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5968099010759696600L;
	private Long id;
	private String url;//图片路径
	private Long  commodityid;//商品图片
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
	@Override
	public String toString() {
		return "PictureDO [id=" + id + ", url=" + url + ", commodityid=" + commodityid + ", rank=" + rank + "]";
	}
	
	
	
	
	
}
