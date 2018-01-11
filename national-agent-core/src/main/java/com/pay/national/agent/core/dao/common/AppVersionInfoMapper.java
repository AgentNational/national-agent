
package com.pay.national.agent.core.dao.common;

import java.util.Map;

import com.pay.national.agent.model.entity.AppVersionInfo;
import com.pay.national.agent.model.entity.SoftwareVersionInfo;
import com.pay.national.agent.model.enums.OperSys;

/**
 * @ClassName:  AppVersionInfoMapper
 * @Description:
 * @author: xiaodi.fu
 * @date:   2017年9月11日 下午5:30:22
 *
 */
public interface AppVersionInfoMapper {
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
	public Map<String, Object> updateProcess(SoftwareVersionInfo softWareVersionInfo,String osType);

}
