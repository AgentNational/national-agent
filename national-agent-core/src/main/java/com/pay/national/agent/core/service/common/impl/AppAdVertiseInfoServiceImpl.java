package com.pay.national.agent.core.service.common.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.dao.common.AppAdVertiseInfoMapper;
import com.pay.national.agent.core.service.common.AppAdVertiseInfoService;
import com.pay.national.agent.model.entity.AppAdVertiseInfo;


@Service("appAdVertiseInfoService")
public class AppAdVertiseInfoServiceImpl implements AppAdVertiseInfoService {
	@Resource
	private AppAdVertiseInfoMapper appAdVertiseInfoMapper;
	@Override
	public List<AppAdVertiseInfo> getAdVertiseInfos(@RequestParam Map<String, String> params) {

		List<AppAdVertiseInfo> list = null;
		try{
			list = appAdVertiseInfoMapper.getAppAdVertiseInfos(params);
			if(list != null){
				setProperttArray(list);
			}
			LogUtil.info("method getAdVertiseInfos banner查询 data = {}",JSON.toJSONString(list));
		}catch (Exception e) {
			LogUtil.info("method getAdVertiseInfos banner查询 error:{}",e);

		  }
		return list;
	}

	/**
	 * 为AppAdvertiseInfo对象设置属性
	 * @param advertiseInfoList
	 */
	private void setProperttArray(List<AppAdVertiseInfo> advertiseInfoList){
		for (AppAdVertiseInfo appAdvertiseInfo : advertiseInfoList) {
			String properties = appAdvertiseInfo.getProperties();
			if(!"".equals(properties) && properties != null){
				appAdvertiseInfo.setPropertyArray(properties.split(","));
			}
		}
	}

}
