package com.pay.national.agent.model.entity;

import java.util.Date;

import com.pay.national.agent.model.enums.ChildBusinessCode;

public class RewardRule {
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
     * 奖励金额
     *
     * @mbggenerated
     */
    private Double rewardAmount;

    /**
     * 状态
     *
     * @mbggenerated
     */
    private String status;

    /**
     * 子业务编码
     *
     * @mbggenerated
     */
    private ChildBusinessCode childBusinessCode;

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

    /**
     * 交易金额
     *
     * @mbggenerated
     */
    private Double transAmount;

    /**
     * 备用字段1
     *
     * @mbggenerated
     */
    private String remark1;

    /**
     * 备用字段2
     *
     * @mbggenerated
     */
    private String remark2;

    /**
     * 备用字段3
     *
     * @mbggenerated
     */
    private String remark3;

    /**
     * 备用字段4
     *
     * @mbggenerated
     */
    private String remark4;

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

    public Double getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(Double rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ChildBusinessCode getChildBusinessCode() {
        return childBusinessCode;
    }

    public void setChildBusinessCode(ChildBusinessCode childBusinessCode) {
        this.childBusinessCode = childBusinessCode;
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

    public Double getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(Double transAmount) {
        this.transAmount = transAmount;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

    public String getRemark4() {
        return remark4;
    }

    public void setRemark4(String remark4) {
        this.remark4 = remark4;
    }

	@Override
	public String toString() {
		return "RewardRule [id=" + id + ", optimistic=" + optimistic + ", rewardAmount=" + rewardAmount + ", status="
				+ status + ", childBusinessCode=" + childBusinessCode + ", createTime=" + createTime
				+ ", lastUpdateTime=" + lastUpdateTime + ", transAmount=" + transAmount + ", remark1=" + remark1
				+ ", remark2=" + remark2 + ", remark3=" + remark3 + ", remark4=" + remark4 + "]";
	}
}