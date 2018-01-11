package com.pay.national.agent.model.entity;

import java.util.Date;

public class RemitPayment {
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
     * 状态
     *
     * @mbggenerated
     */
    private String status;

    /**
     * 结算账户编号
     *
     * @mbggenerated
     */
    private String settleAccount;

    /**
     * 结算规则ID
     *
     * @mbggenerated
     */
    private String settleRuleId;

    /**
     * 结算卡ID
     *
     * @mbggenerated
     */
    private String settleCardId;

    /**
     * 省份
     *
     * @mbggenerated
     */
    private String province;

    /**
     * 城市
     *
     * @mbggenerated
     */
    private String city;

    /**
     * 银行编号
     *
     * @mbggenerated
     */
    private String bankCode;

    /**
     * 银联号
     *
     * @mbggenerated
     */
    private String alliedBankCode;

    /**
     * 清分联行号
     *
     * @mbggenerated
     */
    private String sabkCode;

    /**
     * 开户行
     *
     * @mbggenerated
     */
    private String openBankName;

    /**
     * 分行号
     *
     * @mbggenerated
     */
    private String ownBankCode;

    /**
     * 银行账户类型
     *
     * @mbggenerated
     */
    private String bankAccountType;

    /**
     * 银行账户名称
     *
     * @mbggenerated
     */
    private String bankAccountName;

    /**
     * 银行卡号
     *
     * @mbggenerated
     */
    private String bankCardNo;

    /**
     * 结算金额
     *
     * @mbggenerated
     */
    private Double settleAmount;

    /**
     * 付款金额
     *
     * @mbggenerated
     */
    private Double remitAmount;

    /**
     * 付款费用
     *
     * @mbggenerated
     */
    private Double remitFee;

    /**
     * 费用类型
     *
     * @mbggenerated
     */
    private String feeType;

    /**
     * 付款业务编码
     *
     * @mbggenerated
     */
    private String remitBusCode;

    /**
     * 费用业务编码
     *
     * @mbggenerated
     */
    private String feeBusCode;

    /**
     * 是否加急
     *
     * @mbggenerated
     */
    private String isUrgent;

    /**
     * 付款状态
     *
     * @mbggenerated
     */
    private String remitStatus;

    /**
     * 付款单ID
     *
     * @mbggenerated
     */
    private String remitPaymentId;

    /**
     * 付款请求ID
     *
     * @mbggenerated
     */
    private String remitRequestId;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSettleAccount() {
        return settleAccount;
    }

    public void setSettleAccount(String settleAccount) {
        this.settleAccount = settleAccount;
    }

    public String getSettleRuleId() {
        return settleRuleId;
    }

    public void setSettleRuleId(String settleRuleId) {
        this.settleRuleId = settleRuleId;
    }

    public String getSettleCardId() {
        return settleCardId;
    }

    public void setSettleCardId(String settleCardId) {
        this.settleCardId = settleCardId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getAlliedBankCode() {
        return alliedBankCode;
    }

    public void setAlliedBankCode(String alliedBankCode) {
        this.alliedBankCode = alliedBankCode;
    }

    public String getSabkCode() {
        return sabkCode;
    }

    public void setSabkCode(String sabkCode) {
        this.sabkCode = sabkCode;
    }

    public String getOpenBankName() {
        return openBankName;
    }

    public void setOpenBankName(String openBankName) {
        this.openBankName = openBankName;
    }

    public String getOwnBankCode() {
        return ownBankCode;
    }

    public void setOwnBankCode(String ownBankCode) {
        this.ownBankCode = ownBankCode;
    }

    public String getBankAccountType() {
        return bankAccountType;
    }

    public void setBankAccountType(String bankAccountType) {
        this.bankAccountType = bankAccountType;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public Double getSettleAmount() {
        return settleAmount;
    }

    public void setSettleAmount(Double settleAmount) {
        this.settleAmount = settleAmount;
    }

    public Double getRemitAmount() {
        return remitAmount;
    }

    public void setRemitAmount(Double remitAmount) {
        this.remitAmount = remitAmount;
    }

    public Double getRemitFee() {
        return remitFee;
    }

    public void setRemitFee(Double remitFee) {
        this.remitFee = remitFee;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getRemitBusCode() {
        return remitBusCode;
    }

    public void setRemitBusCode(String remitBusCode) {
        this.remitBusCode = remitBusCode;
    }

    public String getFeeBusCode() {
        return feeBusCode;
    }

    public void setFeeBusCode(String feeBusCode) {
        this.feeBusCode = feeBusCode;
    }

    public String getIsUrgent() {
        return isUrgent;
    }

    public void setIsUrgent(String isUrgent) {
        this.isUrgent = isUrgent;
    }

    public String getRemitStatus() {
        return remitStatus;
    }

    public void setRemitStatus(String remitStatus) {
        this.remitStatus = remitStatus;
    }

    public String getRemitPaymentId() {
        return remitPaymentId;
    }

    public void setRemitPaymentId(String remitPaymentId) {
        this.remitPaymentId = remitPaymentId;
    }

    public String getRemitRequestId() {
        return remitRequestId;
    }

    public void setRemitRequestId(String remitRequestId) {
        this.remitRequestId = remitRequestId;
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
		return "RemitPayment [id=" + id + ", optimistic=" + optimistic + ", userNo=" + userNo + ", status=" + status
				+ ", settleAccount=" + settleAccount + ", settleRuleId=" + settleRuleId + ", settleCardId="
				+ settleCardId + ", province=" + province + ", city=" + city + ", bankCode=" + bankCode
				+ ", alliedBankCode=" + alliedBankCode + ", sabkCode=" + sabkCode + ", openBankName=" + openBankName
				+ ", ownBankCode=" + ownBankCode + ", bankAccountType=" + bankAccountType + ", bankAccountName="
				+ bankAccountName + ", bankCardNo=" + bankCardNo + ", settleAmount=" + settleAmount + ", remitAmount="
				+ remitAmount + ", remitFee=" + remitFee + ", feeType=" + feeType + ", remitBusCode=" + remitBusCode
				+ ", feeBusCode=" + feeBusCode + ", isUrgent=" + isUrgent + ", remitStatus=" + remitStatus
				+ ", remitPaymentId=" + remitPaymentId + ", remitRequestId=" + remitRequestId + ", createTime="
				+ createTime + ", lastUpdateTime=" + lastUpdateTime + "]";
	}
    
    
}