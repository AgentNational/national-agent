package com.pay.national.agent.model.entity;

import java.util.Date;

public class AppNoticeRead {
    /**
     * 主键
     *
     * @mbggenerated
     */
    private String id;

    /**
     * 版本号
     *
     * @mbggenerated
     */
    private Integer optimistic;

    /**
     * 消息ID
     *
     * @mbggenerated
     */
    private String noticeId;

    /**
     * 推送的客户名
     *
     * @mbggenerated
     */
    private String userName;

    /**
     * 已读时间
     *
     * @mbggenerated
     */
    private Date readTime;

    /**
     * 公告状态
     *
     * @mbggenerated
     */
    private String status;

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

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getReadTime() {
        return readTime;
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	@Override
	public String toString() {
		return "AppNoticeRead [id=" + id + ", optimistic=" + optimistic + ", noticeId=" + noticeId + ", userName="
				+ userName + ", readTime=" + readTime + ", status=" + status + "]";
	}
}