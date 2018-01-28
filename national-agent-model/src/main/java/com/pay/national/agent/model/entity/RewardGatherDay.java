package com.pay.national.agent.model.entity;

import java.util.Date;

/**
 * @author shuyan.qi
 */
public class RewardGatherDay extends  BaseEntity{

    private static final long serialVersionUID = -3594279680368386601L;

    /**
     * 用户编号
     */
    private String userNo;

    /**
     *业务编码
     */
    private String businessCode;

    /**
     *父业务编码
     */
    private String parentBusinessCode;

    /**
     *汇总金额
     */
    private Double totalAmount;

    /**
     *奖励日期
     */
    private Date day;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *最后更新时间
     */
    private Date lastUpdateTime;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

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

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
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
        return super.toString()+"RewardGatherDay{" +
                "userNo='" + userNo + '\'' +
                ", businessCode='" + businessCode + '\'' +
                ", parentBusinessCode='" + parentBusinessCode + '\'' +
                ", totalAmount=" + totalAmount +
                ", day=" + day +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }
}