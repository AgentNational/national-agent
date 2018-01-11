package com.pay.national.agent.model.beans.query;

import java.io.Serializable;

public class MessageListQueryBean implements Serializable{
	private static final long serialVersionUID = 3364284756414579365L;
	
	private String userName;//接收消息的用户名
	
	private String isRead;//已读：Y 未读：N
	
	private String msgType;//消息类型

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

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	@Override
	public String toString() {
		return "MessageListQueryBean [userName=" + userName +  ", isRead=" + isRead
				+ ", msgType=" + msgType + "]";
	}

}
