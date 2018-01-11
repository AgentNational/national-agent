package com.pay.national.agent.model.beans.results;

import java.util.List;
import java.io.Serializable;

import com.pay.national.agent.model.entity.PosBusiness;

public class PosBusinessListBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3739195490020394505L;

	private String userNo;
	
	private List<PosBusiness> posBusinessList;

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public List<PosBusiness> getPosBusinessList() {
		return posBusinessList;
	}

	public void setPosBusinessList(List<PosBusiness> posBusinessList) {
		this.posBusinessList = posBusinessList;
	}

	@Override
	public String toString() {
		return "PosBusinessListBean [userNo=" + userNo + ", posBusinessList=" + posBusinessList + "]";
	}
	
	
	
}
