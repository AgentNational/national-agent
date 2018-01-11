package com.pay.national.agent.core.test.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.pay.national.agent.core.facade.AppMenuFacade;
import com.pay.national.agent.core.test.context.BaseTest;

public class AppMenuFacadeTest extends BaseTest{

	@Resource
	private AppMenuFacade appMenuFacade;
	
	@Test
	public void testFindAllMenu(){
		Map<String, String> params = new HashMap<>();
		//params.put("loginKey", "cd36984fe5ae8c0080e1e17c8618d624");
		String result = appMenuFacade.findAllMenu(params);
		System.out.println(result);
		
	}
}
