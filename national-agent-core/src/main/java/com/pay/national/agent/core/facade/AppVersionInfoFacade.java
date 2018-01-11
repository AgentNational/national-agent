/**
 *
 */
package com.pay.national.agent.core.facade;

import java.util.Map;

import com.pay.national.agent.model.entity.SoftwareVersionInfo;

/**
 * @ClassName:  AppVersionInfoFacade
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: xiaodi.fu
 * @date:   2017年9月12日 上午10:04:44
 *
 */
public interface AppVersionInfoFacade {
	public Map<String, Object> updateProcess(String version,String osType);
}
