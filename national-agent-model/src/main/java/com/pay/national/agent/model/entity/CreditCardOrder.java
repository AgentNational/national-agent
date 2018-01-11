package com.pay.national.agent.model.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.pay.national.agent.model.enums.ChildBusinessCode;
import com.pay.national.agent.model.enums.CreditCardOrderStatus;

public class CreditCardOrder implements Serializable{
	private static final long serialVersionUID = -271037030945113960L;

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
     * 订单号
     *
     * @mbggenerated
     */
    private String orderNo;

    /**
     * 用户编号
     *
     * @mbggenerated
     */
    private String userNo;

    /**
     * 信用卡类型
     *
     * @mbggenerated
     */
    private String cradType;

    /**
     * 业务编码
     *
     * @mbggenerated
     */
    private ChildBusinessCode businessCode;

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
    private CreditCardOrderStatus status;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    @JSONField(format="yyyy.MM.dd")
    private Date createTime;

    /**
     * 客户姓名
     *
     * @mbggenerated
     */
    private String customerName;

    /**
     * 客户手机号
     *
     * @mbggenerated
     */
    private String customerPhone;

    /**
     * 处理日期
     *
     * @mbggenerated
     */
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    @JSONField(format="yyyy.MM.dd")
    private Date processTime;

    /**
     * 最后更新日期
     *
     * @mbggenerated
     */
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    @JSONField(format="yyyy.MM.dd")
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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getCradType() {
        return cradType;
    }

    public void setCradType(String cradType) {
        this.cradType = cradType;
    }

    public ChildBusinessCode getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(ChildBusinessCode businessCode) {
        this.businessCode = businessCode;
    }

    public Double getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(Double rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    public CreditCardOrderStatus getStatus() {
        return status;
    }

    public void setStatus(CreditCardOrderStatus status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Date getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Date processTime) {
        this.processTime = processTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

	@Override
	public String toString() {
		return "CreditCardOrder [id=" + id + ", optimistic=" + optimistic + ", orderNo=" + orderNo + ", userNo="
				+ userNo + ", cradType=" + cradType + ", businessCode=" + businessCode + ", rewardAmount="
				+ rewardAmount + ", status=" + status + ", createTime=" + createTime + ", customerName=" + customerName
				+ ", customerPhone=" + customerPhone + ", processTime=" + processTime + ", lastUpdateTime="
				+ lastUpdateTime + "]";
	}
}