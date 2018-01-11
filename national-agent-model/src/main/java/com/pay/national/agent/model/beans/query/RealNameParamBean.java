package com.pay.national.agent.model.beans.query;

import java.io.Serializable;

/**
 * @Description: 实名认证参数bean
 * @see: 需要参考的类
 * @version 2017年9月11日 下午2:00:20
 * @author zhenhui.liu
 */
public class RealNameParamBean implements Serializable{

	
	private static final long serialVersionUID = -2024449408222955890L;

	private String loginKey;//登录标示
	
	private String bankAccountName;//真实姓名
	
	private String identityNo;//身份证号
	
	private String bankAccountNo;//银行卡号
	
	private String bankName;//开户行名称
	
	private String bankCode;//开户行编号
	
	private String alliedBankCode;//开户行联行号
	
	private String sabkCode;//开户行清分行号
	
	private String province;//开户行省份
	
	private String city;//开户行城市
	
	private String areaCode;//地区码
	
	private String coordinate;//经纬度

	public String getLoginKey() {
		return loginKey;
	}

	public void setLoginKey(String loginKey) {
		this.loginKey = loginKey;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public String getBankAccountNo() {
		return bankAccountNo;
	}

	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getAlliedBankCode() {
		return alliedBankCode;
	}

	public void setAlliedBankCode(String alliedBankCode) {
		this.alliedBankCode = alliedBankCode;
	}

	public String getSabkCode() {
		return sabkCode;
	}

	public void setSabkCode(String sabkCode) {
		this.sabkCode = sabkCode;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	@Override
	public String toString() {
		return "RealNameParamBean [loginKey=" + loginKey + ", bankAccountName=" + bankAccountName + ", identityNo="
				+ identityNo + ", bankAccountNo=" + bankAccountNo + ", bankName=" + bankName + ", bankCode=" + bankCode
				+ ", alliedBankCode=" + alliedBankCode + ", sabkCode=" + sabkCode + ", province=" + province + ", city="
				+ city + ", areaCode=" + areaCode + ", coordinate=" + coordinate + "]";
	}

	
}
