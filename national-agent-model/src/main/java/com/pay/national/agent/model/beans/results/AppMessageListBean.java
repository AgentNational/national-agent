package com.pay.national.agent.model.beans.results;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class AppMessageListBean implements Serializable{
	private static final long serialVersionUID = -4713230107643513781L;
private String id;//主键id
	
	private String msgType;//消息类型
	
	@JSONField(format="yyyy-MM-dd")
	private Date createTime;//创建时间
	
	private String title;//标题
	
	private String briefContent;//简要内容
	
	private String userName;//推送的用户名
	
	private String isRead;//是否已读
	
	private String ableStatus;//消息状态

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBriefContent() {
		return briefContent;
	}

	public void setBriefContent(String briefContent) {
		this.briefContent = briefContent;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	public String getAbleStatus() {
		return ableStatus;
	}

	public void setAbleStatus(String ableStatus) {
		this.ableStatus = ableStatus;
	}

	@Override
	public String toString() {
		return "AppMessageListBean [id=" + id + ", msgType=" + msgType + ", createTime=" + createTime + ", title="
				+ title + ", briefContent=" + briefContent + ", userName=" + userName + ", isRead=" + isRead
				+ ", ableStatus=" + ableStatus + "]";
	}
	

}
