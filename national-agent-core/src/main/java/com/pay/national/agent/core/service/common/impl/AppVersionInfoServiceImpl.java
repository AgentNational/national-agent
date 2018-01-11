
package com.pay.national.agent.core.service.common.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pay.national.agent.core.dao.common.AppVersionInfoMapper;
import com.pay.national.agent.core.service.common.AppVersionInfoService;
import com.pay.national.agent.model.entity.AppVersionInfo;
import com.pay.national.agent.model.enums.OperSys;

/**
 * @ClassName:  AppVersionInfoServiceImpl
 * @Description:APP版本更新
 * @author: xiaodi.fu
 * @date:   2017年9月11日 下午5:40:04
 *
 */
@Service("appVersionInfoService")
public class AppVersionInfoServiceImpl implements AppVersionInfoService {

	@Resource
	private AppVersionInfoMapper appVersionInfoMapper;

	@Override
	public AppVersionInfo getNewestVersion(OperSys operSys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> updateProcess(String version, String osType) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	/*@Resource
	private AppVersionInfoServiceFacade appVersionInfoServiceFacade;

	@Override
	public AppVersionInfo getNewestVersion(OperSys operSys) {
		AppVersionInfo appVersionInfo =  appVersionInfoServiceFacade.getNewestVersion(operSys);
		LogUtil.info("method getNewestVersion appVersionInfo={}",JSONUtils.toJsonString(appVersionInfo));

		return appVersionInfo;
	}

	@Override
	public Map<String, Object> updateProcess(
			String version, String osType) throws Exception {
		SoftwareVersionInfo softWareVersionInfo =  new SoftwareVersionInfo(version);
		SoftwareUpdateInfo softwareUpdateInfo = new SoftwareUpdateInfo();
		OperSys operSys = OperSys.valueOf(osType);

		//获取最新版本信息
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			AppVersionInfoBean appVersionInfo = this.getNewestVersion(operSys);
			LogUtil.info("method updateProcess appVersionInfo={}",JSONUtils.toJsonString(appVersionInfo));
			if(!StringUtils.notBlank(appVersionInfo.getAppSize())){
				map.put("checkStatus", "0");
				return map;
			}
			softwareUpdateInfo.setLatestVersion(appVersionInfo.getVersionNo());
			softwareUpdateInfo.setMinVersion(appVersionInfo.getMinAppVersion());
			softwareUpdateInfo.setUpdateURL(appVersionInfo.getAppDownloadUrl());

			map.put("updatePath", softwareUpdateInfo.getUpdateURL());
			map.put("description", appVersionInfo.getDescription());
			map.put("versionNo", appVersionInfo.getVersionNo());

		} catch (Exception e) {
			LogUtil.info("method updateProcess 获取最新版本信息 error={}",JSONUtils.toJsonString(e));
		}

		try {
			SoftwareVersionInfo latestVersion = new SoftwareVersionInfo(softwareUpdateInfo.getLatestVersion());
			SoftwareVersionInfo minVersion = new SoftwareVersionInfo(softwareUpdateInfo.getMinVersion());
			LogUtil.info("method updateProcess latestVersion={},minVersion={}",latestVersion,minVersion);
			switch (softWareVersionInfo.compareTo(latestVersion)) {
			case SoftwareVersionInfo.LATTER_IS_BIG:
				switch (softWareVersionInfo.compareTo(minVersion)) {
				case SoftwareVersionInfo.LATTER_IS_BIG:
					map.put("checkStatus", "2");
					break;
				case SoftwareVersionInfo.TWO_IS_EQUAL:
					map.put("checkStatus", "1");
				case SoftwareVersionInfo.FORMER_IS_BIG:
					map.put("checkStatus", "1");
				default:
					break;
				}
				break;
			case SoftwareVersionInfo.TWO_IS_EQUAL:
				map.put("checkStatus", "0");
				break;
			case SoftwareVersionInfo.FORMER_IS_BIG:
				LogUtil.debug("the uploaded app version is bigger than lastest versin !");
			    break;
			default:
				LogUtil.debug("app-update error version incomparable!");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.info("method updateProcess ERROR:{}",JSONUtils.toJsonString(e));
		}
		return map;
	}*/
}
