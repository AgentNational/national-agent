package com.pay.national.agent.core.service.common;

import java.util.Map;

import com.pay.national.agent.model.entity.AppVersionInfo;
import com.pay.national.agent.model.enums.OperSys;

/**
 * @ClassName:  AppVersionInfoService
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: xiaodi.fu
 * @date:   2017年9月11日 下午5:37:29
 *
 */
public interface AppVersionInfoService {
	/**
	 *
	 * @Title: getNewestVersion
	 * @Description: 获取对应的应用下载信息
	 * @param: @param operSys
	 * @param: @param appStatus
	 * @param: @param appType
	 * @param: @return
	 * @return: AppVersionInfo
	 * @throws
	 */
	public AppVersionInfo getNewestVersion(OperSys operSys);

	/**
	 *
	 * @Title: updateProcess
	 * @Description: 检查app是否有更新需求
	 * @param: @param softWareVersionInfo
	 * @param: @param osType
	 * @param: @return
	 * @return: Map<String,Object>
	 * @throws
	 */
	public Map<String, Object> updateProcess(String version,String osType)throws Exception ;

}
