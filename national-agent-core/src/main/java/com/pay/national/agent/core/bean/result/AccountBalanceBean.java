package com.pay.national.agent.core.bean.result;

import java.io.Serializable;

public class AccountBalanceBean implements Serializable{
	private static final long serialVersionUID = 2444485992685550171L;
	
	private String userNo;
	private String accountNo;
	private Double balance;
	private String status;
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "AccountBalanceBean [userNo=" + userNo + ", accountNo=" + accountNo + ", balance=" + balance
				+ ", status=" + status + "]";
	}
}
