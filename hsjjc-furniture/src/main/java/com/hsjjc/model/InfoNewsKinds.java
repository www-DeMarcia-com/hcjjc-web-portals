package com.hsjjc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 新闻类型
 * @author Administrator
 *
 */
@Entity
@Table(name="infonewskinds")
public class InfoNewsKinds implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2842079283913410538L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column
	private String name;
	/*@OneToMany(mappedBy ="infonewskinds",cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
	private Set<InfoNews> infonews;*/
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
	/*public Set<InfoNews> getInfonews() {
		return infonews;
	}
	public void setInfonews(Set<InfoNews> infonews) {
		this.infonews = infonews;
	}*/
	
	
}
