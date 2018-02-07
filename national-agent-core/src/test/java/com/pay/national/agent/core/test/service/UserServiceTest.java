package com.pay.national.agent.core.test.service;

import com.pay.national.agent.core.facade.UserFacade;
import com.pay.national.agent.core.test.context.BaseTest;

import javax.annotation.Resource;

public class UserServiceTest extends BaseTest{

	@Resource
	private UserFacade userFacade;
	

	public static void  main (String [] args){
		String redirectUrl = "http://git.javams.com/na-source/index.html?openId=openIdZhanWeiFu#/home";//重定向地址
		redirectUrl = redirectUrl.replace("openIdZhanWeiFu","12346465464");
		System.out.println(redirectUrl);
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
