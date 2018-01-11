/**
 * 
 */
package com.pay.national.agent.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.national.agent.core.dao.user.TestUserMapper;
import com.pay.national.agent.core.service.TestService;
import com.pay.national.agent.model.entity.TestUser;

/**
 * @Description: 一句话描述类的用法
 * @see: 需要参考的类
 * @version 2017年9月5日 上午11:30:48
 * @author zhenhui.liu
 */
@Service("testService")
public class TestServiceImpl implements TestService{

	@Autowired
	private TestUserMapper testUserMapper;
	
	@Override
	public String test(String userName, String pwd) {
		TestUser testUser = new TestUser();
		testUser.setUserName(userName);
		testUser.setPwd(pwd);
		testUserMapper.insert(testUser);
		return null;
	}

}
