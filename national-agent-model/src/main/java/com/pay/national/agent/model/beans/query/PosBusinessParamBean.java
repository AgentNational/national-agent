package com.pay.national.agent.model.beans.query;

import java.io.Serializable;

public class PosBusinessParamBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8329258199672922841L;

	private String phoneNo;//手机号
	
	private String receiveAddress;//收货地址
	
	private String linkman;//收货人
	
	private String userNo;//用户编号
	
	private String businessCode;//业务编码

	private String checkCode;//验证码
	
	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
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
		return "PosBusinessParamBean [phoneNo=" + phoneNo + ", receiveAddress=" + receiveAddress + ", linkman="
				+ linkman + ", userNo=" + userNo + ", businessCode=" + businessCode + ", checkCode=" + checkCode + "]";
	}
	
	
}
