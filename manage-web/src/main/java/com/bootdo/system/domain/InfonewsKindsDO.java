package com.bootdo.system.domain;

import java.io.Serializable;



/**
 * 新闻类型
 * @author Administrator
 *
 */

public class InfonewsKindsDO implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 5333649043713205879L;

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
	@Override
	public String toString() {
		return "InfonewsKindsDO [id=" + id + ", name=" + name + "]";
	}

	
	
}
