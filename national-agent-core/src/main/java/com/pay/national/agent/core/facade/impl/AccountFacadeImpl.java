package com.pay.national.agent.core.facade.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.core.bean.result.AccountBalanceBean;
import com.pay.national.agent.core.bean.result.WithdrawDetailBean;
import com.pay.national.agent.core.facade.AccountFacade;
import com.pay.national.agent.core.service.common.AccountService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.entity.AccountHistory;
import com.pay.national.agent.model.enums.BusinessCode;
import com.pay.national.agent.model.enums.ChildBusinessCode;

@Component("accountFacade")
public class AccountFacadeImpl implements AccountFacade{
	
	@Resource
	private AccountService accountService;

	@Override
	public ReturnBean<String> withdrawDeposit(String userNo, Double withdrawAmount) {
		return accountService.withdrawDeposit(userNo,withdrawAmount);
	}

	@Override
	public ReturnBean<AccountBalanceBean> accountBalance(String userNo) {
		return accountService.accountBalance(userNo);
	}

	@Override
	public ReturnBean<Page<List<AccountHistory>>> accountHistoryList(String userNo, String businessCode, String date,Page<List<AccountHistory>> page) {
		return accountService.accountHistoryList(userNo,businessCode,date,page);
	}
	
	@Override
	public ReturnBean<WithdrawDetailBean> findWithdrawDetail(String userNo, String requestId) {
		return accountService.findWithdrawDetail(userNo,requestId);
	}

	@Override
	public ReturnBean<String> credit(String userNo, double amount) {
		return accountService.credit(userNo, amount,null,BusinessCode.CREDIT_CARD, ChildBusinessCode.XINGYE);
	}

}
