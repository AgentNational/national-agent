package com.pay.national.agent.model.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
/**
 * app个人消息
 * @author shuyan.qi
 *
 */
public class AppMessageInfo implements Serializable{
	private static final long serialVersionUID = 9170803569526931952L;
	private String id;
    private Integer optimistic;
    private String operator;
    private String msgType;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String title;
    private String content;
    private String userName;
    private String isRead;
    private String ableStatus;
    private String briefContent;
    private Boolean isPop;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    private Long pushId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date beginTime;

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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getBriefContent() {
        return briefContent;
    }

    public void setBriefContent(String briefContent) {
        this.briefContent = briefContent;
    }

    public Boolean getIsPop() {
        return isPop;
    }

    public void setIsPop(Boolean isPop) {
        this.isPop = isPop;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

	public Long getPushId() {
		return pushId;
	}

	public void setPushId(Long pushId) {
		this.pushId = pushId;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	@Override
	public String toString() {
		return "AppMessageInfo [id=" + id + ", optimistic=" + optimistic + ", operator=" + operator + ", msgType="
				+ msgType + ", createTime=" + createTime + ", title=" + title + ", content=" + content + ", userName="
				+ userName + ", isRead=" + isRead + ", ableStatus=" + ableStatus + ", briefContent=" + briefContent
				+ ", isPop=" + isPop + ", lastUpdateTime=" + lastUpdateTime + ", endTime=" + endTime + ", pushId="
				+ pushId + ", beginTime=" + beginTime + "]";
	}

}