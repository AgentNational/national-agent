package com.pay.national.agent.model.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 *
 * @author shuyan.qi
 */
public class BusinessOrder extends BaseEntity {
    private static final long serialVersionUID = 9062166379737197106L;
    /**
     * 用户编号
     */
    private String userNo;

    /**
     *微信用户公众号访问唯一凭证
     */
    private String openId;

    /**
     *业务名称
     */
    private String businessName;

    /**
     *业务编码
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
     *客户名称
     */
    private String customerName;

    /**
     *客户手机号
     */
    private String customerPhone;

    /**
     *奖励金额
     */
    private Double rewardAmount;

    /**
     *交易金额
     */
    private Double transAmount;

    /**
     *合作方业务用户名称
     */
    private String partnerBusinessName;

    /**
     *合作方业务用户编号
     */
    private String partnerBusinessNo;

    /**
     *创建时间
     */
    @JSONField(format = "yyyy.MM.dd")
    private Date createTime;

    /**
     *最后更新时间
     */
    private Date lastUpdateTime;

    /**
     *收货地址
     */
    private String receiveAddress;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
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

    public Double getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(Double rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    public Double getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(Double transAmount) {
        this.transAmount = transAmount;
    }

    public String getPartnerBusinessName() {
        return partnerBusinessName;
    }

    public void setPartnerBusinessName(String partnerBusinessName) {
        this.partnerBusinessName = partnerBusinessName;
    }

    public String getPartnerBusinessNo() {
        return partnerBusinessNo;
    }

    public void setPartnerBusinessNo(String partnerBusinessNo) {
        this.partnerBusinessNo = partnerBusinessNo;
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

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    @Override
    public String toString() {
        return super.toString()+"BusinessOrder{" +
                "userNo='" + userNo + '\'' +
                ", openId='" + openId + '\'' +
                ", businessName='" + businessName + '\'' +
                ", businessCode='" + businessCode + '\'' +
                ", parentBusinessCode='" + parentBusinessCode + '\'' +
                ", status='" + status + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", rewardAmount=" + rewardAmount +
                ", transAmount=" + transAmount +
                ", partnerBusinessName='" + partnerBusinessName + '\'' +
                ", partnerBusinessNo='" + partnerBusinessNo + '\'' +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                ", receiveAddress='" + receiveAddress + '\'' +
                '}';
    }
}