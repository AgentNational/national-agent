package com.pay.national.agent.model.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * app公告
 * 
 * @author shuyan.qi
 *
 */
public class AppNoticeInfo implements Serializable {
	private static final long serialVersionUID = 9099574294376643459L;

	private String id;

	private Integer optimistic;

	private String title;

	private String content;

	private String noticeType;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date beginTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date endTime;

	private Boolean isPop;

	private String usableStatus;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	private String briefContent;

	private String operator;

	private String userGroup;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date lastUpdateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getOptimistic() {
		return optimistic;
	}

	public void setOptimistic(Integer optimistic) {
		this.optimistic = optimistic;
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

	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Boolean getIsPop() {
		return isPop;
	}

	public void setIsPop(Boolean isPop) {
		this.isPop = isPop;
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

	public String getBriefContent() {
		return briefContent;
	}

	public void setBriefContent(String briefContent) {
		this.briefContent = briefContent;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}

	@Override
	public String toString() {
		return "AppNoticeInfo [id=" + id + ", optimistic=" + optimistic + ", title=" + title + ", content=" + content
				+ ", noticeType=" + noticeType + ", beginTime=" + beginTime + ", endTime=" + endTime + ", isPop="
				+ isPop + ", usableStatus=" + usableStatus + ", createTime=" + createTime + ", briefContent="
				+ briefContent + ", operator=" + operator + ", userGroup=" + userGroup + ", lastUpdateTime="
				+ lastUpdateTime + "]";
	}

}