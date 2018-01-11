/**
 *
 */
package com.pay.national.agent.core.test.service;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.pay.national.agent.core.facade.AppVersionInfoFacade;
import com.pay.national.agent.core.test.context.BaseTest;
import com.pay.national.agent.model.entity.SoftwareVersionInfo;

/**
 * @ClassName:  AppVersionInfoFacadeTest
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: xiaodi.fu
 * @date:   2017年9月14日 下午2:49:24
 *
 */
public class AppVersionInfoFacadeTest extends BaseTest {
	@Resource
	private AppVersionInfoFacade appVersionInfoFacade;

	@Test
	public void testupdateProcess() throws Exception{
		String osType = "ANDROID";
		SoftwareVersionInfo softWareVersionInfo = new SoftwareVersionInfo("1");
		Map<String, Object> result = appVersionInfoFacade.updateProcess("1", osType);
		System.out.println(result);
	}


}
