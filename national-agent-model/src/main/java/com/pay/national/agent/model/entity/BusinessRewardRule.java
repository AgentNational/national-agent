package com.pay.national.agent.model.entity;

import java.util.Date;

/**
 * @author shuyan.qi
 */
public class BusinessRewardRule extends BaseEntity{
    private static final long serialVersionUID = 1617610114311697230L;

    /**
     * 业务编码
     */
    private String businessCode;

    /**
     *父业务编码
     */
    private String parentBusinessCode;

    /**
     *状态
     */
    private String status;

    /**
     *奖励方式
     */
    private String rewardType;

    /**
     *奖励金额
     */
    private Double rewardAmount;

    /**
     *奖励比例
     */
    private Double rewardProportion;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *最后更新时间
     */
    private Date lastUpdateTime;

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getParentBusinessCode() {
        return parentBusinessCode;
    }

    public void setParentBusinessCode(String parentBusinessCode) {
        this.parentBusinessCode = parentBusinessCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRewardType() {
        return rewardType;
    }

    public void setRewardType(String rewardType) {
        this.rewardType = rewardType;
    }

    public Double getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(Double rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    public Double getRewardProportion() {
        return rewardProportion;
    }

    public void setRewardProportion(Double rewardProportion) {
        this.rewardProportion = rewardProportion;
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
        return super.toString()+"BusinessRewardRule{" +
                "businessCode='" + businessCode + '\'' +
                ", parentBusinessCode='" + parentBusinessCode + '\'' +
                ", status='" + status + '\'' +
                ", rewardType='" + rewardType + '\'' +
                ", rewardAmount=" + rewardAmount +
                ", rewardProportion=" + rewardProportion +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }
}