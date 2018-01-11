package com.pay.national.agent.model.beans.results;

import java.io.Serializable;

public class UserSettleRuleBean implements Serializable{
	private static final long serialVersionUID = 713327992961662357L;

	/** 结算规则ID */
	private String id;

	/** 用户编号 */
	private String userNo;

	/** 账号 */
	private String accountNo;

	/** 状态 */
	private String status;

	/** 起结金额 */
	private Double startSettleAmount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getStartSettleAmount() {
		return startSettleAmount;
	}

	public void setStartSettleAmount(Double startSettleAmount) {
		this.startSettleAmount = startSettleAmount;
	}

	@Override
	public String toString() {
		return "UserSettleRuleBean [id=" + id + ", userNo=" + userNo + ", accountNo=" + accountNo + ", status=" + status
				+ ", startSettleAmount=" + startSettleAmount + "]";
	}
	
}
