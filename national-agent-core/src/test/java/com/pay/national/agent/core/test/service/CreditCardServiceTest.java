package com.pay.national.agent.core.test.service;

import java.util.List;

import javax.annotation.Resource;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.core.bean.request.CreditOrderQueryBean;
import com.pay.national.agent.core.service.credit.CreditCardService;
import com.pay.national.agent.core.test.context.BaseTest;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.results.CreditCardBusinessListBean;
import com.pay.national.agent.model.entity.CreditCardOrder;
import com.pay.national.agent.model.enums.ChildBusinessCode;

public class CreditCardServiceTest extends BaseTest{
	@Resource
	private CreditCardService creditCardService;
	
	@org.junit.Test
	public void createOrderTest(){
		for (int i = 0; i < 10; i++) {
			ReturnBean<String> returnBean = creditCardService.createOrder("123", ChildBusinessCode.valueOf("XINGYE"), "戚墅堰"+i, "13523384544");
		}
	}
	
	@org.junit.Test
	public void businessListTest(){
		ReturnBean<CreditCardBusinessListBean> returnBean = creditCardService.businessList("123");
		String result = JSONUtils.alibabaJsonString(returnBean);
		System.out.println(result);
	}
	
	@org.junit.Test
	public void queryOrders(){
		CreditOrderQueryBean query = new CreditOrderQueryBean();
		query.setUserNo("123");
		Page<List<CreditCardOrder>> page = new Page<List<CreditCardOrder>>();
		page.setCurrentPage(1);
		page.setShowCount(10);
		ReturnBean<Page<List<CreditCardOrder>>> returnBean = creditCardService.queryOrders(query, page);
		String result = JSONUtils.alibabaJsonString(returnBean);
		System.out.println(result);
	}
}
