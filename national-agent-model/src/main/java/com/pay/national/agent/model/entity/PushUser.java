package com.pay.national.agent.model.entity;

import java.util.Date;

public class PushUser {
    /**
     * 主键
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * 版本号
     *
     * @mbggenerated
     */
    private Integer optimistic;

    /**
     * 用户名
     *
     * @mbggenerated
     */
    private String userNo;

    /**
     * 通知栏内容
     *
     * @mbggenerated
     */
    private String pushId;

    /**
     * 客户端类型
     *
     * @mbggenerated
     */
    private String clientType;

    /**
     * 应用标识
     *
     * @mbggenerated
     */
    private String appId;

    /**
     * 设备标记
     *
     * @mbggenerated
     */
    private String deviceToken;

    /**
     * 状态
     *
     * @mbggenerated
     */
    private String status;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 最后更新时间
     *
     * @mbggenerated
     */
    private Date lastUpdateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOptimistic() {
        return optimistic;
    }

    public void setOptimistic(Integer optimistic) {
        this.optimistic = optimistic;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

	@Override
	public String toString() {
		return "PushUser [id=" + id + ", optimistic=" + optimistic + ", userNo=" + userNo + ", pushId=" + pushId
				+ ", clientType=" + clientType + ", appId=" + appId + ", deviceToken=" + deviceToken + ", status="
				+ status + ", createTime=" + createTime + ", lastUpdateTime=" + lastUpdateTime + "]";
	}
}