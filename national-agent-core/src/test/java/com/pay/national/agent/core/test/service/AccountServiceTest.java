package com.pay.national.agent.core.test.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.core.service.common.AccountService;
import com.pay.national.agent.core.test.context.BaseTest;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.entity.AccountHistory;
import com.pay.national.agent.model.enums.BusinessCode;
import com.pay.national.agent.model.enums.ChildBusinessCode;

public class AccountServiceTest extends BaseTest{
	@Resource
	private AccountService accountService;
	
	@Test
	public void accountHistoryListTest(){
		String userNo = "123";
		//String businessCode = "TEST";
		String startDate = "2017-09-10";
		Page<List<AccountHistory>> page  = new Page<>();
		page.setShowCount(3);
		ReturnBean<Page<List<AccountHistory>>> returnBean = accountService.accountHistoryList(userNo, null, startDate, page);
		String alibabaJsonString = JSONUtils.alibabaJsonString(returnBean);
		System.out.println(alibabaJsonString);
	}
	
	@Test
	public void createHistoryTest(){
		AccountHistory accountHistory = new AccountHistory();
		accountHistory.setAccountNo("52423");
		accountHistory.setUserNo("123");
		accountHistory.setBusinessCode(BusinessCode.CREDIT_CARD);
		accountHistory.setChildBusinessCode(ChildBusinessCode.XINGYE);
		accountHistory.setCreateTime(new Date());
		accountHistory.setAmount(45.5D);
		
		accountService.createAccountHistory(accountHistory);
	}
	
	@Test
	public void credit(){
		ReturnBean<String> returnBean = accountService.credit("123", 45.6D, "2343423",BusinessCode.CREDIT_CARD,ChildBusinessCode.XINGYE);
		System.out.println(returnBean);
	}

}
