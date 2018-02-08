package com.pay.national.agent.model.beans.results;

/**
 * 账户信息
 *
 * @author shuyan.qi
 * @date 2018/2/7
 */
public class AccountInfoBean {
    //余额
    private double balance;
    //账户状态
    private String accStatus;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccStatus() {
        return accStatus;
    }

    public void setAccStatus(String accStatus) {
        this.accStatus = accStatus;
    }

    @Override
    public String toString() {
        return "AccountInfoBean{" +
                "balance=" + balance +
                ", accStatus='" + accStatus + '\'' +
                '}';
    }
}
