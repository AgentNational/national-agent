package com.pay.national.agent.model.entity;

import java.util.Date;

/**
 * @author shuyan.qi
 */
public class Account extends BaseEntity{
    private static final long serialVersionUID = 7586802297030809226L;

    /**
     * 账户编号
     */
    private String accountNo;

    /**
     * 账户余额
     */
    private Double balance;

    /**
     *在途金额
     */
    private Double transAmount;

    /**
     *冻结金额
     */
    private Double frozenAmount;

    /**
     *用户编号
     */
    private String userNo;

    /**
     *状态
     */
    private String status;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *最后更新时间
     */
    private Date lastUpdateTime;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(Double transAmount) {
        this.transAmount = transAmount;
    }

    public Double getFrozenAmount() {
        return frozenAmount;
    }

    public void setFrozenAmount(Double frozenAmount) {
        this.frozenAmount = frozenAmount;
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
        return super.toString()+"Account{" +
                "accountNo='" + accountNo + '\'' +
                ", balance=" + balance +
                ", transAmount=" + transAmount +
                ", frozenAmount=" + frozenAmount +
                ", userNo='" + userNo + '\'' +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }
}