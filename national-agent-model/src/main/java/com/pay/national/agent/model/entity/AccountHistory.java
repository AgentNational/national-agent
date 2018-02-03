package com.pay.national.agent.model.entity;

import java.util.Date;

/**
 * @author shuyan.qi
 */
public class AccountHistory extends BaseEntity{
    private static final long serialVersionUID = -5004473018513268379L;

    /**
     * 账户编号
     */
    private String accountNo;

    /**
     * 金额
     */
    private Double amount;

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
     *状态
     */
    private String status;

    /**
     *操作(加PLUS、减SUBTRACT)
     */
    private String symbol;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *最后更新时间
     */
    private Date lastUpdateTime;

    /**
     *微信付款单号
     */
    private String wxBillNo;

    /**
     * 错误信息
     */
    private String errorMsg;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
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

    public String getWxBillNo() {
        return wxBillNo;
    }

    public void setWxBillNo(String wxBillNo) {
        this.wxBillNo = wxBillNo;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "AccountHistory{" +
                "accountNo='" + accountNo + '\'' +
                ", amount=" + amount +
                ", userNo='" + userNo + '\'' +
                ", businessCode='" + businessCode + '\'' +
                ", parentBusinessCode='" + parentBusinessCode + '\'' +
                ", status='" + status + '\'' +
                ", symbol='" + symbol + '\'' +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                ", wxBillNo='" + wxBillNo + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}