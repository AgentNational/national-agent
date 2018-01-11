/**
 *
 */
package com.pay.national.agent.core.test.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.pay.national.agent.core.facade.AppAdVertiseInfoFacade;
import com.pay.national.agent.core.test.context.BaseTest;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.entity.AppAdVertiseInfo;

/**
 * @ClassName:  AppAdVertiseInfoServiceTest
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: xiaodi.fu
 * @date:   2017年9月14日 下午5:03:42
 *
 */
public class AppAdVertiseInfoServiceTest extends BaseTest {
	@Resource
	private AppAdVertiseInfoFacade appAdVertiseInfoFacade;
	@Test
	public void testgetAdVertiseInfos(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("advertiseType", "HOME_PAGE");
		params.put("appType", "Android");
		params.put("deviceType", "mx5");
		params.put("sign", "xxxxdwxxxxddxxxd");
		params.put("version", "1.0.0");
		ReturnBean<List<AppAdVertiseInfo>> returnBean = appAdVertiseInfoFacade.getAppAdVertises(params);
		System.out.println(returnBean);


	}

}
