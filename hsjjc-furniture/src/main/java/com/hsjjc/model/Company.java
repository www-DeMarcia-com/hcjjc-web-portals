package com.hsjjc.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 公司，座机，手机。邮件地址，地址，简介，名称，
 * @author Administrator
 *
 */
@Entity
@Table(name="company")
public class Company implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8016985458415083195L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column
	private String name;
	@Column
	private String cellphone;//手机
	@Column
	private String phone;//座机
	@Column
	private String email;
	@Basic(fetch = FetchType.LAZY)
	@Column (length=6000)
	private String intro;//简介
	@Column
	private String address;//地址
	@Column
	private String pictureurl;
	@Basic(fetch = FetchType.LAZY)
	@Column (length=300)
	private String culture;//企业文化
	@Basic(fetch = FetchType.LAZY)
	@Column (length=500)
	private String introgg;//简介
	
	
	public String getIntrogg() {
		return introgg;
	}
	public void setIntrogg(String introgg) {
		this.introgg = introgg;
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
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPictureurl() {
		return pictureurl;
	}
	public void setPictureurl(String pictureurl) {
		this.pictureurl = pictureurl;
	}
	public String getCulture() {
		return culture;
	}
	public void setCulture(String culture) {
		this.culture = culture;
	}
	
	
	
	
	
}
