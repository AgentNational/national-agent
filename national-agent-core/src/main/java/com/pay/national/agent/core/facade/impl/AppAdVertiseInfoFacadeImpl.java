package com.pay.national.agent.core.facade.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.facade.AppAdVertiseInfoFacade;
import com.pay.national.agent.core.service.common.AppAdVertiseInfoService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.AppAdVertiseInfo;
/**
 *
 * @ClassName:  AppAdVertiseInfoFadeImpl
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: xiaodi.fu
 * @date:   2017年9月11日 上午11:45:40
 *
 */
@Component("appAdVertiseInfoFacade")
public class AppAdVertiseInfoFacadeImpl implements AppAdVertiseInfoFacade{
	@Resource
	private AppAdVertiseInfoService appAdVertiseInfoService;

	@Override
	public ReturnBean<List<AppAdVertiseInfo>> getAppAdVertises(@RequestParam Map<String, String> params) {

		ReturnBean<List<AppAdVertiseInfo>> returnBean = new ReturnBean<List<AppAdVertiseInfo>>();
		List<AppAdVertiseInfo> list = null;
		try{
			list = appAdVertiseInfoService.getAdVertiseInfos(params);
			returnBean.setData(list);
			returnBean.setCode(RetCodeConstants.SUCCESS);
			returnBean.setMsg(RetCodeConstants.SUCCESS_DESC);
			LogUtil.info("method getAppAdVertises data={}",JSONUtils.toJsonString(returnBean));
		}catch (Exception e) {
			LogUtil.info("method getAppAdVertises error:{}",e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setMsg(RetCodeConstants.ERROR_DESC);
		  }
		return returnBean;
	}

}
