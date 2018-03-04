package com.pay.national.agent.model.beans.query;

import com.pay.national.agent.model.enums.BusinessCode;
import com.pay.national.agent.model.enums.ParentBusinessCode;

/**
 * 入账参数
 *
 * @author shuyan.qi
 * @date 2018/1/30
 */
public class DepositParam {
    private String accountNo;
    private String userNo;
    private double amount;
    private String businessCode;
    private String parentBusinessCode;

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public void setParentBusinessCode(String parentBusinessCode) {
        this.parentBusinessCode = parentBusinessCode;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public double getAmount() {
        return amount;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public String getParentBusinessCode() {
        return parentBusinessCode;
    }

    @Override
    public String toString() {
        return "DepositParam{" +
                "accountNo='" + accountNo + '\'' +
                ", userNo='" + userNo + '\'' +
                ", amount=" + amount +
                ", businessCode=" + businessCode +
                ", parentBusinessCode=" + parentBusinessCode +
                '}';
    }
}
