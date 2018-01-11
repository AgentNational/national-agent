package com.pay.national.agent.model.beans;

import java.io.Serializable;

import com.pay.national.agent.model.entity.CreditCardOrder;

public class CreditCardOrderMatchBean implements Serializable{
	private static final long serialVersionUID = -3853606445434044932L;

	private Integer creditCardUserId;
	
	private CreditCardOrder order;

	public Integer getCreditCardUserId() {
		return creditCardUserId;
	}

	public void setCreditCardUserId(Integer creditCardUserId) {
		this.creditCardUserId = creditCardUserId;
	}

	public CreditCardOrder getOrder() {
		return order;
	}

	public void setOrder(CreditCardOrder order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "CreditCardOrderMatchBean [creditCardUserId=" + creditCardUserId + ", order=" + order + "]";
	}
}
