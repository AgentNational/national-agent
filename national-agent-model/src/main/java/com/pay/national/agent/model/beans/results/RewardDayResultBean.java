package com.pay.national.agent.model.beans.results;

import java.io.Serializable;

public class RewardDayResultBean implements Serializable{
	private static final long serialVersionUID = -5053903193077633042L;
	private String day;
	private String userNo;
	private Double amountOfDay;
	private String businessCode;
	private String childBusinessCode;
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public Double getAmountOfDay() {
		return amountOfDay;
	}
	public void setAmountOfDay(Double amountOfDay) {
		this.amountOfDay = amountOfDay;
	}
	public String getBusinessCode() {
		return businessCode;
	}
	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}
	public String getChildBusinessCode() {
		return childBusinessCode;
	}
	public void setChildBusinessCode(String childBusinessCode) {
		this.childBusinessCode = childBusinessCode;
	}
	@Override
	public String toString() {
		return "RewardDayResultBean [day=" + day + ", userNo=" + userNo + ", amountOfDay=" + amountOfDay
				+ ", businessCode=" + businessCode + ", childBusinessCode=" + childBusinessCode + "]";
	}
}
