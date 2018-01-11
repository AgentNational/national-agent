package com.pay.national.agent.core.test.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.core.facade.PosFacade;
import com.pay.national.agent.core.test.context.BaseTest;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.query.PosBusinessParamBean;
import com.pay.national.agent.model.beans.query.PosOrderQueryBean;
import com.pay.national.agent.model.beans.results.PosBusinessListBean;
import com.pay.national.agent.model.entity.PosOrder;
import com.pay.national.agent.model.enums.ChildBusinessCode;

public class PosServiceTest extends BaseTest{

	@Resource
	private PosFacade posFacade;
	
	@Test
	public void businessList(){
		ReturnBean<PosBusinessListBean> businessList = posFacade.businessList("123456478946");
		System.out.println(JSONUtils.alibabaJsonString(businessList));
	}
	
	@Test
	public void queryOrders() {
		Page<List<PosOrder>> page = new Page<>();
		PosOrderQueryBean posOrderQueryBean = new PosOrderQueryBean();
		posOrderQueryBean.setUserNo("8619230353");
		String result = posFacade.queryOrders(page, posOrderQueryBean);
		System.out.println(result);
	
	}
	
	@Test
	public void businessProcessing(){
		PosBusinessParamBean posBusinessParamBean = new PosBusinessParamBean();
		posBusinessParamBean.setBusinessCode(ChildBusinessCode.KAYOU_POS.name());
		posBusinessParamBean.setLinkman("sdfd");
		posBusinessParamBean.setPhoneNo("17632669856");
		posBusinessParamBean.setReceiveAddress("fdsjgdfkgfdh");
		posBusinessParamBean.setUserNo("86192365546");
		String result = posFacade.businessProcessing(posBusinessParamBean);
		System.out.println(result);
		
	}
}
