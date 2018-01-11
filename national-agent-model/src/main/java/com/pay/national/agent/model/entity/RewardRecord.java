package com.pay.national.agent.model.entity;

import java.util.Date;

import com.pay.national.agent.model.enums.BusinessCode;
import com.pay.national.agent.model.enums.ChildBusinessCode;
import com.pay.national.agent.model.enums.RewardStatus;

public class RewardRecord {
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
    private BusinessCode businessCode;

    /**
     * 子业务编码
     *
     * @mbggenerated
     */
    private ChildBusinessCode childBusinessCode;

    /**
     * 订单号
     *
     * @mbggenerated
     */
    private String orderNo;

    /**
     * 业务请求流水号
     *
     * @mbggenerated
     */
    private String systemFlowId;

    /**
     * 用户编号
     *
     * @mbggenerated
     */
    private String userNo;

    /**
     * 状态
     *
     * @mbggenerated
     */
    private String status;

    /**
     * 奖励金额
     *
     * @mbggenerated
     */
    private Double rewardAmount;

    /**
     * 奖励状态
     *
     * @mbggenerated
     */
    private RewardStatus rewardStatus;

    /**
     * 奖励时间
     *
     * @mbggenerated
     */
    private Date rewardTime;

    /**
     * 错误码
     *
     * @mbggenerated
     */
    private String errorCode;

    /**
     * 错误信息
     *
     * @mbggenerated
     */
    private String errorMsg;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;
    
    /**
     * 奖励规则id
     */
    private Integer ruleId;

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

	public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSystemFlowId() {
        return systemFlowId;
    }

    public void setSystemFlowId(String systemFlowId) {
        this.systemFlowId = systemFlowId;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
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

    public RewardStatus getRewardStatus() {
        return rewardStatus;
    }

    public void setRewardStatus(RewardStatus rewardStatus) {
        this.rewardStatus = rewardStatus;
    }

    public Date getRewardTime() {
        return rewardTime;
    }

    public void setRewardTime(Date rewardTime) {
        this.rewardTime = rewardTime;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public Integer getRuleId() {
		return ruleId;
	}

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}

	@Override
	public String toString() {
		return "RewardRecord [id=" + id + ", optimistic=" + optimistic + ", businessCode=" + businessCode
				+ ", childBusinessCode=" + childBusinessCode + ", orderNo=" + orderNo + ", systemFlowId=" + systemFlowId
				+ ", userNo=" + userNo + ", status=" + status + ", rewardAmount=" + rewardAmount + ", rewardStatus="
				+ rewardStatus + ", rewardTime=" + rewardTime + ", errorCode=" + errorCode + ", errorMsg=" + errorMsg
				+ ", createTime=" + createTime + ", ruleId=" + ruleId + "]";
	}
}