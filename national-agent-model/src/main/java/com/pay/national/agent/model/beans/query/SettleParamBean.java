package com.pay.national.agent.model.beans.query;

import java.io.Serializable;
import java.util.Date;

public class SettleParamBean implements Serializable{
	private static final long serialVersionUID = 9031646105069338281L;
	private String systemCode;//系统编码
	private String userNo;//用户编号
	private Date settleTime;//结算时间
	private Double settleAmount;//结算金额
	public String getSystemCode() {
		return systemCode;
	}
	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public Date getSettleTime() {
		return settleTime;
	}
	public void setSettleTime(Date settleTime) {
		this.settleTime = settleTime;
	}
	public Double getSettleAmount() {
		return settleAmount;
	}
	public void setSettleAmount(Double settleAmount) {
		this.settleAmount = settleAmount;
	}
	@Override
	public String toString() {
		return "SettleParamBean [systemCode=" + systemCode + ", userNo=" + userNo + ", settleTime=" + settleTime
				+ ", settleAmount=" + settleAmount + "]";
	}
	
}
