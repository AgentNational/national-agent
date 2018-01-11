package com.pay.national.agent.model.beans.results;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class AppPopListBean implements Serializable{
	private static final long serialVersionUID = 5257234030928208697L;

	private String id;//主键id
	
	private String type;//标明是公告还是个人消息
	
	private String title;//标题
	
	private String content;//内容
	
	@JSONField(format="yyyy-MM-dd")
	private Date createTime;//创建时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "AppPopListBean [id=" + id + ", type=" + type + ", title=" + title + ", content=" + content
				+ ", createTime=" + createTime + "]";
	}

}
