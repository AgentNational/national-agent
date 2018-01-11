package com.pay.national.agent.model.beans.results;

import java.io.Serializable;
import java.util.Date;

public class UserAccountBean implements Serializable{
	private static final long serialVersionUID = -86915879662434318L;

	/** 账号 */
	private String accountNo;

	/** 用户编号 */
	private String userNo;

	/** 总余额 */
	private double balance;

	/** 在途金额 */
	private double transitBalance;

	/** 冻结金额 */
	private double freezeBalance;

	/** 状态 */
	private String status;

	/** 开户日期 */
	private Date openDate;
	
	/** 备注 */
	private String remark;

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getTransitBalance() {
		return transitBalance;
	}

	public void setTransitBalance(double transitBalance) {
		this.transitBalance = transitBalance;
	}

	public double getFreezeBalance() {
		return freezeBalance;
	}

	public void setFreezeBalance(double freezeBalance) {
		this.freezeBalance = freezeBalance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "MyAccountBean [accountNo=" + accountNo + ", userNo=" + userNo + ", balance=" + balance
				+ ", transitBalance=" + transitBalance + ", freezeBalance=" + freezeBalance + ", status=" + status
				+ ", openDate=" + openDate + ", remark=" + remark + "]";
	}

	
}
