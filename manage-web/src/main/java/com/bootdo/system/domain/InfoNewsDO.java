package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 新闻内容
 * @author Administrator
 *
 */

public class InfoNewsDO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -562830074578443611L;
	private Long id;
	private String title;//标题
	private Date time;//发布时间
	private String tags;//标签
	private String content;//如果有图片的话要把图片转化为硬盘地址
	private String contentgg;	
	private Long infonewskindsid;
	
	public Long getInfonewskindsid() {
		return infonewskindsid;
	}
	public void setInfonewskindsid(Long infonewskindsid) {
		this.infonewskindsid = infonewskindsid;
	}
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
	/*public InfoNewsKinds getInfonewskinds() {
		return infonewskinds;
	}
	public void setInfonewskinds(InfoNewsKinds infonewskinds) {
		this.infonewskinds = infonewskinds;
	}*/
	
	
	
}
