package com.bootdo.system.domain;

import java.io.Serializable;



/**
 * 新闻类型
 * @author Administrator
 *
 */

public class ProducerDO implements Serializable{



	/**
	 * 
	 */
	private static final long serialVersionUID = 8258890662053531238L;

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
