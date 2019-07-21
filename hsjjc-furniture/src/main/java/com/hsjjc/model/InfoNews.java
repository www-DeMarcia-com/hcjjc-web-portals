package com.hsjjc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * 新闻内容
 * @author Administrator
 *
 */
@Entity
@Table(name="infonews")
public class InfoNews implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3492514202864988515L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column
	private String title;//标题
	@Column
	private Date time;//发布时间
	@Column
	private String tags;//标签
	@Basic(fetch = FetchType.LAZY)
	@Column(length=6000)
	private String content;//如果有图片的话要把图片转化为硬盘地址
	@Column
	private String contentgg;
	@ManyToOne
	@JoinColumn(name = "infonewskindsid")
	@NotFound(action=NotFoundAction.IGNORE)
	private InfoNewsKinds infonewskinds;
	//private String contentDecode;
	
	/*public String getContentDecode() {
		try {
			contentDecode= new String(content,"Utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contentDecode;
	}*/
	/*public void setContentDecode(String contentDecode) {
		this.contentDecode = contentDecode;
	}*/
	
	
	public Long getId() {
		return id;
	}
	public String getContentgg() {
		return contentgg;
	}
	public void setContentgg(String contentgg) {
		this.contentgg = contentgg;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	
/*	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}*/
	public InfoNewsKinds getInfonewskinds() {
		return infonewskinds;
	}
	public void setInfonewskinds(InfoNewsKinds infonewskinds) {
		this.infonewskinds = infonewskinds;
	}
	
	
	
}
