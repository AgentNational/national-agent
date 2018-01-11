package com.pay.national.agent.core.bean.request;

import java.io.Serializable;

/**
 * 信用卡业务订单查询bean
 * @author shuyan.qi
 * Date:2017年9月11日下午11:25:28
 */
public class CreditOrderQueryBean implements Serializable{
	private static final long serialVersionUID = 1801351017651889693L;
	
	private String userNo;

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	@Override
	public String toString() {
		return "CreditOrderQueryBean [userNo=" + userNo + "]";
	}
	
}
