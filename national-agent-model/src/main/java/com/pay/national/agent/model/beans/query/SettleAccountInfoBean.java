package com.pay.national.agent.model.beans.query;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @Description: 结算卡信息bean
 * @see: SettleAccountInfoBean 此处填写需要参考的类
 * @version 2016年9月8日 下午4:07:37
 * @author chao.wang
 */
public class SettleAccountInfoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5268310131752487232L;
	private String ownerId; // 持有者ID
	private String ownerRole; // 持有者角色
	private String settleAccountType; // 结算账户类型
	private String bankAccountName; // 银行开户名称,商户公司名称或法人姓名
	private String bankAccountNo; // 银行帐号
	private String bankCode; // 开户行编号
	private String openBankName; // 开户行名称
	private String alliedBankCode; // 联行号
	private String province; // 收款省份
	private String city; // 收款地市
	private Date effTime; // 生效时间
	private Date expTime; // 终止时间
	private Date createTime; // 创建时间
	private String status; // 状态
	private String ownBankCode; // 中行省行标识
	private String sabkCode; // 清分行号

	public String getOwnBankCode() {
		return ownBankCode;
	}

	public void setOwnBankCode(String ownBankCode) {
		this.ownBankCode = ownBankCode;
	}

	public String getSabkCode() {
		return sabkCode;
	}

	public void setSabkCode(String sabkCode) {
		this.sabkCode = sabkCode;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getBankAccountNo() {
		return bankAccountNo;
	}

	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public Date getEffTime() {
		return effTime;
	}

	public void setEffTime(Date effTime) {
		this.effTime = effTime;
	}

	public Date getExpTime() {
		return expTime;
	}

	public void setExpTime(Date expTime) {
		this.expTime = expTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOpenBankName() {
		return openBankName;
	}

	public void setOpenBankName(String openBankName) {
		this.openBankName = openBankName;
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

	public String getAlliedBankCode() {
		return alliedBankCode;
	}

	public void setAlliedBankCode(String alliedBankCode) {
		this.alliedBankCode = alliedBankCode;
	}

	public String getOwnerRole() {
		return ownerRole;
	}

	public void setOwnerRole(String ownerRole) {
		this.ownerRole = ownerRole;
	}

	public String getSettleAccountType() {
		return settleAccountType;
	}

	public void setSettleAccountType(String settleAccountType) {
		this.settleAccountType = settleAccountType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SettleAccountInfoBean [ownerId=" + ownerId + ", ownerRole=" + ownerRole + ", settleAccountType="
				+ settleAccountType + ", bankAccountName=" + bankAccountName + ", bankAccountNo=" + bankAccountNo
				+ ", bankCode=" + bankCode + ", openBankName=" + openBankName + ", alliedBankCode=" + alliedBankCode
				+ ", province=" + province + ", city=" + city + ", effTime=" + effTime + ", expTime=" + expTime
				+ ", createTime=" + createTime + ", status=" + status + ", ownBankCode=" + ownBankCode + ", sabkCode="
				+ sabkCode + "]";
	}
}
