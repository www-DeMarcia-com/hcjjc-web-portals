package com.bootdo.system.domain;

import java.io.Serializable;

/**
 * 公司，座机，手机。邮件地址，地址，简介，名称，
 * @author Administrator
 *
 */

public class CompanyDO implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1916729041512890705L;

	private Long id;
	
	private String name;
	
	private String cellphone;//手机
	
	private String phone;//座机
	
	private String email;
	
	private String intro;//简介
	
	private String address;//地址
	
	private String pictureurl;
		 
	private String culture;//企业文化
	
	
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
	@Override
	public String toString() {
		return "CompanyDO [id=" + id + ", name=" + name + ", cellphone=" + cellphone + ", phone=" + phone + ", email="
				+ email + ", intro=" + intro + ", address=" + address + ", pictureurl=" + pictureurl + ", culture="
				+ culture + ", introgg=" + introgg + "]";
	}
	
	
}
