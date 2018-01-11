package com.pay.national.agent.model.entity;

import java.io.Serializable;
import java.util.Date;

import com.pay.national.agent.model.enums.ChildBusinessCode;

public class CreditCardBusiness implements Serializable{
	private static final long serialVersionUID = 1141619060653944785L;

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
     * 业务编码
     *
     * @mbggenerated
     */
    private ChildBusinessCode businessCode;

    /**
     * 业务名称
     *
     * @mbggenerated
     */
    private String businessName;

    /**
     * 状态
     *
     * @mbggenerated
     */
    private String status;

    /**
     * 业务奖励金额
     *
     * @mbggenerated
     */
    private Double rewardAmount;

    /**
     * 特点
     *
     * @mbggenerated
     */
    private String feature;

    /**
     * 奖励标准
     *
     * @mbggenerated
     */
    private String rewardStandard;

    /**
     * 跳转链接
     *
     * @mbggenerated
     */
    private String link;

    /**
     * 下标
     *
     * @mbggenerated
     */
    private Integer showIndex;

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

    public ChildBusinessCode getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(ChildBusinessCode businessCode) {
        this.businessCode = businessCode;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(Double rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getRewardStandard() {
        return rewardStandard;
    }

    public void setRewardStandard(String rewardStandard) {
        this.rewardStandard = rewardStandard;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getShowIndex() {
        return showIndex;
    }

    public void setShowIndex(Integer showIndex) {
        this.showIndex = showIndex;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@Override
	public String toString() {
		return "CreditCardBusiness [id=" + id + ", optimistic=" + optimistic + ", businessCode=" + businessCode
				+ ", businessName=" + businessName + ", status=" + status + ", rewardAmount=" + rewardAmount
				+ ", feature=" + feature + ", rewardStandard=" + rewardStandard + ", link=" + link + ", showIndex="
				+ showIndex + ", createTime=" + createTime + "]";
	}

}