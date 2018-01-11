package com.pay.national.agent.model.beans.results;

import java.io.Serializable;
import java.util.List;

import com.pay.national.agent.model.entity.CreditCardBusiness;

public class CreditCardBusinessListBean implements Serializable{
	private static final long serialVersionUID = 4963065827573571394L;
	private String userNo;
	private List<CreditCardBusiness> busList;
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public List<CreditCardBusiness> getBusList() {
		return busList;
	}
	public void setBusList(List<CreditCardBusiness> busList) {
		this.busList = busList;
	}
	@Override
	public String toString() {
		return "CreditCardBusinessListBean [userNo=" + userNo + ", busList=" + busList + "]";
	}
}
