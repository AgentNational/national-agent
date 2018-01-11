package com.pay.national.agent.model.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class AppAdVertiseInfo implements Serializable{

	private static final long serialVersionUID = 3707044494794120549L;

	private String id;//ID

    private Integer optimistic;//版本号

    private String description;//描述

    private String imagesUrl;//图片地址

    private String linkUrl;//链接地址

    private Integer indexNo;//序号

    private String advertiseType;//广告类型

    private String className;//类名

    private String properties;//属性名字符串

    private String appType;//系统类型

    private String operatorFlag;//操作员角色

    private String useType;//使用方式

    private String status;//状态

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;//创建时间

    private String[] propertyArray;//属性名数组格式

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagesUrl() {
		return imagesUrl;
	}

	public void setImagesUrl(String imagesUrl) {
		this.imagesUrl = imagesUrl;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public Integer getIndexNo() {
		return indexNo;
	}

	public void setIndexNo(Integer indexNo) {
		this.indexNo = indexNo;
	}

	public String getAdvertiseType() {
		return advertiseType;
	}

	public void setAdvertiseType(String advertiseType) {
		this.advertiseType = advertiseType;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getProperties() {
		return properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getOperatorFlag() {
		return operatorFlag;
	}

	public void setOperatorFlag(String operatorFlag) {
		this.operatorFlag = operatorFlag;
	}

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
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

	public String[] getPropertyArray() {
		return propertyArray;
	}

	public void setPropertyArray(String[] propertyArray) {
		this.propertyArray = propertyArray;
	}

	@Override
	public String toString() {
		return "AppAdVertiseInfo [id=" + id + ", optimistic=" + optimistic
				+ ", description=" + description + ", imagesUrl=" + imagesUrl
				+ ", linkUrl=" + linkUrl + ", indexNo=" + indexNo
				+ ", advertiseType=" + advertiseType + ", className="
				+ className + ", properties=" + properties + ", appType="
				+ appType + ", operatorFlag=" + operatorFlag + ", useType="
				+ useType + ", status=" + status + ", createTime=" + createTime
				+ ", propertyArray=" + Arrays.toString(propertyArray) + "]";
	}



}
