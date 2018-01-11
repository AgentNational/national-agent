package com.pay.national.agent.model.entity;

import java.io.Serializable;
import java.util.Date;

import com.pay.national.agent.model.enums.BusinessCode;
import com.pay.national.agent.model.enums.ChildBusinessCode;

public class RewardGatherDay implements Serializable{
	private static final long serialVersionUID = -6920920034066664391L;

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
     * 用户编号
     *
     * @mbggenerated
     */
    private String userNo;

    /**
     * 业务编码
     *
     * @mbggenerated
     */
    private BusinessCode businessCode;

    /**
     * 子业务编码
     *
     * @mbggenerated
     */
    private ChildBusinessCode childBusinessCode;

    /**
     * 奖励日汇总金额
     *
     * @mbggenerated
     */
    private Double rewardAmountTotal;

    /**
     * 奖励日期
     *
     * @mbggenerated
     */
    private Date rewardDate;

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

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public BusinessCode getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(BusinessCode businessCode) {
        this.businessCode = businessCode;
    }

    public ChildBusinessCode getChildBusinessCode() {
        return childBusinessCode;
    }

    public void setChildBusinessCode(ChildBusinessCode childBusinessCode) {
        this.childBusinessCode = childBusinessCode;
    }

    public Double getRewardAmountTotal() {
        return rewardAmountTotal;
    }

    public void setRewardAmountTotal(Double rewardAmountTotal) {
        this.rewardAmountTotal = rewardAmountTotal;
    }

    public Date getRewardDate() {
        return rewardDate;
    }

    public void setRewardDate(Date rewardDate) {
        this.rewardDate = rewardDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@Override
	public String toString() {
		return "RewardGatherDay [id=" + id + ", optimistic=" + optimistic + ", userNo=" + userNo + ", businessCode="
				+ businessCode + ", childBusinessCode=" + childBusinessCode + ", rewardAmountTotal=" + rewardAmountTotal
				+ ", rewardDate=" + rewardDate + ", createTime=" + createTime + "]";
	}
}