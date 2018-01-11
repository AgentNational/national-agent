package com.pay.national.agent.model.beans.results;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class AppNoticeListBean implements Serializable{
	private static final long serialVersionUID = -1684121345373738144L;

	private String id;//主键
	
	private String noticeType;//公告类型
	
	private String userGroup;//针对用户群体
	
	private String isRead;//已读：Y 未读：N
	
	private String usableStatus;//公告状态
	
	@JSONField(format="yyyy-MM-dd")
	private Date createTime;//创建时间
	
	private String title;//标题
	
	private String briefContent;//简要内容

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	public String getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
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

	public String getUsableStatus() {
		return usableStatus;
	}

	public void setUsableStatus(String usableStatus) {
		this.usableStatus = usableStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "AppNoticeListBean [id=" + id + ", noticeType=" + noticeType +  ", userGroup=" + userGroup + ", isRead=" + isRead
				+ ", usableStatus=" + usableStatus + ", createTime=" + createTime + ", title=" + title
				+ ", briefContent=" + briefContent + "]";
	}
	
	
}
