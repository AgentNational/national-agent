package com.pay.national.agent.core.test.service;

import javax.annotation.Resource;

import org.junit.Test;

import com.pay.national.agent.core.service.common.RemitPaymentService;
import com.pay.national.agent.core.test.context.BaseTest;
import com.pay.national.agent.model.entity.RemitPayment;

public class RemitPaymentServiceTest extends BaseTest{
	@Resource
	private RemitPaymentService remitPaymentService;
	@Test
	public void baseTest(){
		RemitPayment remitPayment = new RemitPayment();
		remitPayment.setCity("合肥市");
		remitPayment.setProvince("安徽省");
		
		remitPaymentService.createRemitPayment(remitPayment);
		remitPayment.setRemitRequestId("RB2385943543840531");
		remitPaymentService.updateRemitPayment(remitPayment);
		
		RemitPayment remitPayment2 = remitPaymentService.findRemitPaymentByRequestId("RB2385943543840531");
		
		System.out.println(remitPayment2);
	}

}
