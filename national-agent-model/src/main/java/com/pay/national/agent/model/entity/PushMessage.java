package com.pay.national.agent.model.entity;

import java.io.Serializable;
import java.util.Date;

public class PushMessage implements Serializable{
	private static final long serialVersionUID = 8081232855619220746L;

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
     * 推送的客户名
     *
     * @mbggenerated
     */
    private String userName;

    /**
     * 通知栏内容
     *
     * @mbggenerated
     */
    private String content;

    /**
     * 客户端类型
     *
     * @mbggenerated
     */
    private String clientType;

    /**
     * 设备标识
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
     * 跳转方式(nodata:不跳转,native:原生页面,h5：h5页面，link:外链)
     */
    private String jumpType;
    
   
    /**
     * 跳转H5的URL
     *
     * @mbggenerated
     */
    private String h5Url;

    /**
     * 跳转外链的URL
     *
     * @mbggenerated
     */
    private String linkUrl;

    /**
     * 原生model名
     *
     * @mbggenerated
     */
    private String model;

    /**
     * 原生model属性名，格式:key1;key2;key3;
     *
     * @mbggenerated
     */
    private String properties;

    /**
     * 原生model属性值，格式:{"key1":"val1","key2":"val2","key3":"val3"}
     */
    private String vals;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
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

	public String getJumpType() {
		return jumpType;
	}

	public void setJumpType(String jumpType) {
		this.jumpType = jumpType;
	}

	public String getH5Url() {
		return h5Url;
	}

	public void setH5Url(String h5Url) {
		this.h5Url = h5Url;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getProperties() {
		return properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

	public String getVals() {
		return vals;
	}

	public void setVals(String vals) {
		this.vals = vals;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "PushMessage [id=" + id + ", optimistic=" + optimistic + ", userName=" + userName + ", content="
				+ content + ", clientType=" + clientType + ", deviceToken=" + deviceToken + ", status=" + status
				+ ", jumpType=" + jumpType + ", h5Url=" + h5Url + ", linkUrl=" + linkUrl + ", model=" + model
				+ ", properties=" + properties + ", vals=" + vals + ", createTime=" + createTime + "]";
	}
}