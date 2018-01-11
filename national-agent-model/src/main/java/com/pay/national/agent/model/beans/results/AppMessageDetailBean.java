package com.pay.national.agent.model.beans.results;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class AppMessageDetailBean implements Serializable{
	private static final long serialVersionUID = -8319991123856641655L;

	private String id;//主键id
	
	private String userName;//接收消息的用户名
	
	private String title;//消息标题
	
	private String content;//消息内容
	
	@JSONField(format="yyyy-MM-dd")
	private Date createTime;//创建时间
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "AppMessageDetailBean [id=" + id + ", userName=" + userName + ", title=" + title + ", content=" + content
				+ ", createTime=" + createTime + "]";
	}
	
	

}
