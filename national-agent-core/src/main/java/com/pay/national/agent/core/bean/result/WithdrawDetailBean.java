package com.pay.national.agent.core.bean.result;

import java.io.Serializable;
import java.util.Date;

/**
 * 提现详情bean
 * @author shuyan.qi
 * Date:2017年9月7日上午6:13:10
 */
public class WithdrawDetailBean implements Serializable{
	private static final long serialVersionUID = 5102821153622689946L;
	
	private String bankAccountNo;//银行开户账号
	private String bankCode;//开户行编号
	private String bankName;//开户行名
	private Double remitAmount;//付款金额
	private String remitBillStatus;//付款单状态
	private Date lastUpdateDate;//最后更新时间
	private Date createDate;//创建时间
	private String failReason;//失败原因
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
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public Double getRemitAmount() {
		return remitAmount;
	}
	public void setRemitAmount(Double remitAmount) {
		this.remitAmount = remitAmount;
	}
	public String getRemitBillStatus() {
		return remitBillStatus;
	}
	public void setRemitBillStatus(String remitBillStatus) {
		this.remitBillStatus = remitBillStatus;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getFailReason() {
		return failReason;
	}
	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}
	@Override
	public String toString() {
		return "WithdrawDetailBean [bankAccountNo=" + bankAccountNo + ", bankCode=" + bankCode + ", bankName="
				+ bankName + ", remitAmount=" + remitAmount + ", remitBillStatus=" + remitBillStatus
				+ ", lastUpdateDate=" + lastUpdateDate + ", createDate=" + createDate + ", failReason=" + failReason
				+ "]";
	}

}
