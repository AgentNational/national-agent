package com.pay.national.agent.model.beans.query;

import java.io.Serializable;

public class PosOrderQueryBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3069140497905035363L;

	private String userNo;

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	@Override
	public String toString() {
		return "PosQueryBean [userNo=" + userNo + "]";
	}
	
	
}
