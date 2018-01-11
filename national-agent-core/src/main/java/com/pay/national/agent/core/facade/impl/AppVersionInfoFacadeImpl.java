
package com.pay.national.agent.core.facade.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.facade.AppVersionInfoFacade;
import com.pay.national.agent.core.service.common.AppVersionInfoService;

/**
 * @ClassName:  AppVersionInfoFacadeImpl
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: xiaodi.fu
 * @date:   2017年9月12日 上午10:09:56
 *
 */
@Component("appVersionInfoFacade")
public class AppVersionInfoFacadeImpl implements AppVersionInfoFacade {
	@Resource
	private AppVersionInfoService appVersionInfoService;
	@Override
	public Map<String, Object> updateProcess(
			String version, String osType) {
		LogUtil.info("method updateProcess ,version:{},osType:{}",version,osType);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = appVersionInfoService.updateProcess(version, osType);
			LogUtil.info("method updateProcess data={}",JSONUtils.toJsonString(map));
		} catch (Exception e) {
			LogUtil.info("method updateProcess error:{}",e);
		}
		return map;
	}

}
