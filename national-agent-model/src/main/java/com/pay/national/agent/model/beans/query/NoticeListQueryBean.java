package com.pay.national.agent.model.beans.query;

import java.io.Serializable;

public class NoticeListQueryBean implements Serializable{
	private static final long serialVersionUID = 4111161900961367375L;

	private String userName;//用户名
	
	private String userGroup;//公告接收用户群体
	
	private String isRead;//已读：Y 未读：N
	
	private String noticeType;//公告类型

	public String getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
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

	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	@Override
	public String toString() {
		return "NoticeListQueryBean [userName=" + userName + ", userGroup=" + userGroup
				+ ", isRead=" + isRead + ", noticeType=" + noticeType + "]";
	}
}
