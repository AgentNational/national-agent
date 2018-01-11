package com.pay.national.agent.model.beans.query;

import java.io.Serializable;
import java.util.Date;

/**
 * 入账参数bean
 * @author shuyan.qi
 * Date:2017年9月7日下午10:23:28
 */
public class CreditParamBean implements Serializable{
	private static final long serialVersionUID = 5131562891245972811L;
	/** 用户编号 */
	private String userNo;

	/** 账号 */
	private String accountNo;

	/** 参考号 */
	private String systemFlowId;

	/** 业务标志 */
	private String bussinessCode;

	/**	金额 */
	private Double amount;

	/** 交易时间 **/
	private Date transDate;

	/** 操作员 */
	private String operator;

	/** 说明 */
	private String remark;

	/** 资金通道编码 */
	private String fundChannelCode;

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

	public String getSystemFlowId() {
		return systemFlowId;
	}

	public void setSystemFlowId(String systemFlowId) {
		this.systemFlowId = systemFlowId;
	}

	public String getBussinessCode() {
		return bussinessCode;
	}

	public void setBussinessCode(String bussinessCode) {
		this.bussinessCode = bussinessCode;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getTransDate() {
		return transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFundChannelCode() {
		return fundChannelCode;
	}

	public void setFundChannelCode(String fundChannelCode) {
		this.fundChannelCode = fundChannelCode;
	}

	@Override
	public String toString() {
		return "CreditParamBean [userNo=" + userNo + ", accountNo=" + accountNo + ", systemFlowId=" + systemFlowId
				+ ", bussinessCode=" + bussinessCode + ", amount=" + amount + ", transDate=" + transDate + ", operator="
				+ operator + ", remark=" + remark + ", fundChannelCode=" + fundChannelCode + "]";
	}
}
