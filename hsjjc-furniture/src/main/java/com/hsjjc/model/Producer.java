package com.hsjjc.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 生产商
 * @author Administrator
 *
 */
@Entity
public class Producer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3006637699073413877L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column
	private String name;
	/*@OneToMany(mappedBy ="producer",cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
	private Set <Commodity>commoditys;*/
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
	/*public Set<Commodity> getCommoditys() {
		return commoditys;
	}
	public void setCommoditys(Set<Commodity> commoditys) {
		this.commoditys = commoditys;
	}*/
	
	
	
}
