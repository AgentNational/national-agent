package com.pay.national.agent.model.entity;

import java.io.Serializable;
import java.util.Date;

import com.pay.national.agent.model.enums.AccountHistoryStatus;
import com.pay.national.agent.model.enums.BusinessCode;
import com.pay.national.agent.model.enums.ChildBusinessCode;

public class AccountHistory implements Serializable{
	private static final long serialVersionUID = -3152708537835809373L;

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
     * 账户编号
     *
     * @mbggenerated
     */
    private String accountNo;

    /**
     * 操作的金额
     *
     * @mbggenerated
     */
    private Double amount;

    /**
     * 操作后的账户余额
     *
     * @mbggenerated
     */
    private Double balance;

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
     * 关联ID(方便追踪查询)
     *
     * @mbggenerated
     */
    private String queryId;

    /**
     * 状态
     *
     * @mbggenerated
     */
    private AccountHistoryStatus status;

    /**
     * 操作（加plus、减sub）
     *
     * @mbggenerated
     */
    private String symbol;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 操作时间
     *
     * @mbggenerated
     */
    private Date operateTime;

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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
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

    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    public AccountHistoryStatus getStatus() {
        return status;
    }

    public void setStatus(AccountHistoryStatus status) {
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

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

	@Override
	public String toString() {
		return "AccountHistory [id=" + id + ", optimistic=" + optimistic + ", accountNo=" + accountNo + ", amount="
				+ amount + ", balance=" + balance + ", userNo=" + userNo + ", businessCode=" + businessCode
				+ ", childBusinessCode=" + childBusinessCode + ", queryId=" + queryId + ", status=" + status
				+ ", symbol=" + symbol + ", createTime=" + createTime + ", operateTime=" + operateTime + "]";
	}
}