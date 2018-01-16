package com.pay.national.agent.core.test.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.pay.commons.cache.util.CacheUtils;
import com.pay.national.agent.core.facade.UserFacade;
import com.pay.national.agent.core.test.context.BaseTest;
import com.pay.national.agent.model.beans.query.LoginParamBean;
import com.pay.national.agent.model.constants.RedisKeys;
import com.pay.national.agent.model.entity.User;

public class UserFacadeTest extends BaseTest{

	@Resource
	private UserFacade userFacade;
	
	@Test
	public void testSendRegisterCheckCode (){
		Map<String, String> params = new HashMap<>();
		params.put("phoneNo", "13622211427");
		userFacade.sendRegisterCheckCode(params);
	}
	
	/*@Test
	public void testRegister (){
		RegisterParamBean registerParamBean = new RegisterParamBean();
		registerParamBean.setCheckCode("298512");
		registerParamBean.setParentUserNo("0");
		registerParamBean.setPassword("123456");
		registerParamBean.setPhoneNo("13622211427");
		registerParamBean.setRepeatPassword("123456");
		userFacade.register(registerParamBean);
	}*/
	
	@Test
	public void testLogin(){
		LoginParamBean loginParamBean = new LoginParamBean();
		loginParamBean.setCoordinate("123156|151351");
		loginParamBean.setDeviceNo("051324654131");
		loginParamBean.setDeviceType("IPHONE7");
		loginParamBean.setOsType("IOS");
		loginParamBean.setVersion("1.0.0");
		loginParamBean.setPassword("12345678");
		loginParamBean.setUserName("13622211425");
		String result = userFacade.login(loginParamBean);
		System.out.println(result);
		User user = CacheUtils.get(RedisKeys.LOGIN_USER_INFO_PREFIX+"", User.class);
		
		System.out.println(user);
	}
	
	@Test
	public void test(){
		User user = CacheUtils.get(RedisKeys.LOGIN_USER_INFO_PREFIX+"2c3501c9fc9e9c47fb70b0b757dc67c0", User.class);
		System.out.println(user);
		userFacade.logout("2c3501c9fc9e9c47fb70b0b757dc67c0");
		User user1 = CacheUtils.get(RedisKeys.LOGIN_USER_INFO_PREFIX+"2c3501c9fc9e9c47fb70b0b757dc67c0", User.class);
		System.out.println(user1);
		
	}
	
	/*@Test
	public void testUpdatePassword(){
		UpdatePasswordParamBean updatePasswordParamBean = new UpdatePasswordParamBean();
		updatePasswordParamBean.setLoginKey("4ef0ff7569267f447ae56a33634a718e");
		updatePasswordParamBean.setPassword("123456");
		updatePasswordParamBean.setNewPassword("654321");
		userFacade.updatePassword(updatePasswordParamBean);
	}*/
	
	@Test
	public void testSendFindPasswordCheckCode(){
		Map<String, String> params = new HashMap<>();
		params.put("phoneNo", "13622211425");
		userFacade.sendFindPasswordCheckCode(params);
	}
	
	@Test
	public void testFindPassword(){
		Map<String, String> params = new HashMap<>();
		params.put("phoneNo", "13622211425");
		params.put("code", "549082");
		params.put("password", "12345678");
		userFacade.findPassword(params);
	}
}
