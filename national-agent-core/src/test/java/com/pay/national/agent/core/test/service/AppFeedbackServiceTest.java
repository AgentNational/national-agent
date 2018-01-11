/**
 *
 */
package com.pay.national.agent.core.test.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.pay.national.agent.core.facade.AppFeedbackFacade;
import com.pay.national.agent.core.test.context.BaseTest;

/**
 * @ClassName:  AppFeedbackServiceTest
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: xiaodi.fu
 * @date:   2017年9月15日 上午9:57:01
 *
 */
public class AppFeedbackServiceTest extends BaseTest {
	@Resource
	private AppFeedbackFacade appFeedbackFacade;

	@Test
	public void testsaveFeedback(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("userName", "18660607691");
		params.put("osType", "Android");
		params.put("deviceType", "mx5");
		params.put("sign", "xxxxdwxxxxddxxxd");
		params.put("version", "1.0.0");
		params.put("content", "意见反馈，全名代理");
		//params.put("clientType", "a");
		appFeedbackFacade.saveFeedback(params);
		System.out.println("反馈意见保存成功");

	}

}
