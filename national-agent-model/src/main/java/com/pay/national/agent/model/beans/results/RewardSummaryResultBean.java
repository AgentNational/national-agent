package com.pay.national.agent.model.beans.results;

import java.io.Serializable;
import java.util.List;

public class RewardSummaryResultBean implements Serializable{
	private static final long serialVersionUID = -2682620775446494481L;
	private Double amountOfAll;
	private List<RewardMonthResultBean> monthLists;
	
	public Double getAmountOfAll() {
		return amountOfAll;
	}
	public void setAmountOfAll(Double amountOfAll) {
		this.amountOfAll = amountOfAll;
	}
	public List<RewardMonthResultBean> getMonthLists() {
		return monthLists;
	}
	public void setMonthLists(List<RewardMonthResultBean> monthLists) {
		this.monthLists = monthLists;
	}
	@Override
	public String toString() {
		return "RewardSummaryResultBean [amountOfAll=" + amountOfAll + ", monthLists=" + monthLists + "]";
	}
}
