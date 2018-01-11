package com.pay.national.agent.model.entity;

import java.util.Date;

public class SettleCard {
    /**
     * 主键
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 版版号
     *
     * @mbggenerated
     */
    private Integer optimistic;

    /**
     * 持有用户id
     *
     * @mbggenerated
     */
    private String ownerId;

    /**
     * 状态ENABLE可用DISABLE禁用
     *
     * @mbggenerated
     */
    private String status;

    /**
     * 结算账户类型
     *
     * @mbggenerated
     */
    private String settleAccountType;

    /**
     * 银行开户名称
     *
     * @mbggenerated
     */
    private String bankAccountName;

    /**
     * 银行帐号
     *
     * @mbggenerated
     */
    private String bankAccountNo;

    /**
     * 开户行编号
     *
     * @mbggenerated
     */
    private String bankCode;

    /**
     * 开户行名称
     *
     * @mbggenerated
     */
    private String bankName;

    /**
     * 联行号
     *
     * @mbggenerated
     */
    private String alliedBankCode;

    /**
     * 清分行号
     *
     * @mbggenerated
     */
    private String sabkCode;

    /**
     * 分行号
     *
     * @mbggenerated
     */
    private String ownBankCode;

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
     * 创建日期
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 最后更新日期
     *
     * @mbggenerated
     */
    private Date lastUpdateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOptimistic() {
        return optimistic;
    }

    public void setOptimistic(Integer optimistic) {
        this.optimistic = optimistic;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSettleAccountType() {
        return settleAccountType;
    }

    public void setSettleAccountType(String settleAccountType) {
        this.settleAccountType = settleAccountType;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
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

    public String getOwnBankCode() {
        return ownBankCode;
    }

    public void setOwnBankCode(String ownBankCode) {
        this.ownBankCode = ownBankCode;
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
		return "SettleCard [id=" + id + ", optimistic=" + optimistic + ", ownerId=" + ownerId + ", status=" + status
				+ ", settleAccountType=" + settleAccountType + ", bankAccountName=" + bankAccountName
				+ ", bankAccountNo=" + bankAccountNo + ", bankCode=" + bankCode + ", bankName=" + bankName
				+ ", alliedBankCode=" + alliedBankCode + ", sabkCode=" + sabkCode + ", ownBankCode=" + ownBankCode
				+ ", province=" + province + ", city=" + city + ", createTime=" + createTime + ", lastUpdateTime="
				+ lastUpdateTime + "]";
	}
}