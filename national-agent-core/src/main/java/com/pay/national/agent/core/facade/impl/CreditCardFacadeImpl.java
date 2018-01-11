package com.pay.national.agent.core.facade.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.core.bean.request.CreditOrderQueryBean;
import com.pay.national.agent.core.facade.CreditCardFacade;
import com.pay.national.agent.core.service.credit.CreditCardService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.results.CreditCardBusinessListBean;
import com.pay.national.agent.model.entity.CreditCardOrder;
import com.pay.national.agent.model.enums.ChildBusinessCode;

@Service("creditCardFacade")
public class CreditCardFacadeImpl implements CreditCardFacade{

	@Resource
	private CreditCardService creditCardService;
	
	
	
	@Override
	public ReturnBean<CreditCardBusinessListBean> businessList(String userNo) {
		return creditCardService.businessList(userNo);
	}

	@Override
	public ReturnBean<String> transactBusiness(String userNo, String businessCode, String customerName,
			String customerPhone) {
		return  creditCardService.createOrder(userNo,ChildBusinessCode.valueOf(businessCode),customerName,customerPhone);
	}

	@Override
	public ReturnBean<Page<List<CreditCardOrder>>> queryOrders(CreditOrderQueryBean query,
			Page<List<CreditCardOrder>> page) {
		return creditCardService.queryOrders(query,page);
	}
	
}
