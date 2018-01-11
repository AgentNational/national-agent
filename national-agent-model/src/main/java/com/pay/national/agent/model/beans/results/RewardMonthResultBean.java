package com.pay.national.agent.model.beans.results;

import java.io.Serializable;

public class RewardMonthResultBean implements Serializable{
	private static final long serialVersionUID = -3253078468982708058L;
	private String month;
	private Double amountOfMonth;
	private String userNo;
	private String businessCode;
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Double getAmountOfMonth() {
		return amountOfMonth;
	}
	public void setAmountOfMonth(Double amountOfMonth) {
		this.amountOfMonth = amountOfMonth;
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
	@Override
	public String toString() {
		return "RewardMonthResultBean [month=" + month + ", amountOfMonth=" + amountOfMonth + ", userNo=" + userNo
				+ ", businessCode=" + businessCode + "]";
	}
}
