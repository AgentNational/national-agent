/**
 * 
 */
package com.pay.national.agent.core.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.national.agent.core.facade.TestFacade;
import com.pay.national.agent.core.service.TestService;

/**
 * @Description: 一句话描述类的用法
 * @see: 需要参考的类
 * @version 2017年9月5日 上午11:08:24
 * @author zhenhui.liu
 */
@Service("testFacade")
public class TestFacadeImpl implements TestFacade{

	@Autowired
	private TestService testService;
	
	@Override
	public String helloWorld(String userName, String pwd) {
		return testService.test(userName, pwd);
	}

}
