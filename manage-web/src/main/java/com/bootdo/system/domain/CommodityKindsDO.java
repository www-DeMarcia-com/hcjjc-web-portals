package com.bootdo.system.domain;

import java.io.Serializable;


/**
 * 商品种类-----床，衣柜，桌子
 * @author Administrator
 *
 */

public class CommodityKindsDO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -726177698047589221L;

	private Long id;
	
	private String name;

	
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
	
	
	
}
