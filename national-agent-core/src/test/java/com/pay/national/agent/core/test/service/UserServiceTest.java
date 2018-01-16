package com.pay.national.agent.core.test.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.pay.national.agent.core.facade.UserFacade;
import com.pay.national.agent.core.test.context.BaseTest;

public class UserServiceTest extends BaseTest{

	@Resource
	private UserFacade userFacade;
	
	@Test
	public void testSendRegisterCheckCode (){
		Map<String, String> params = new HashMap<>();
		params.put("phoneNo", "13622211426");
		userFacade.sendRegisterCheckCode(params);
	}
	
	/*@Test
	public void testRegister (){
		RegisterParamBean registerParamBean = new RegisterParamBean();
		registerParamBean.setCheckCode("888883");
		registerParamBean.setParentUserNo("0");
		registerParamBean.setPassword("123456");
		registerParamBean.setPhoneNo("13622211425");
		registerParamBean.setRepeatPassword("123456");
		userFacade.register(registerParamBean);
	}*/
}
