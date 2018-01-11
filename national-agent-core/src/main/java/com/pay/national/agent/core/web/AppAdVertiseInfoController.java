package com.pay.national.agent.core.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.facade.AppAdVertiseInfoFacade;
import com.pay.national.agent.model.annotation.ParamValidate;
import com.pay.national.agent.model.annotation.ParamsValidate;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.AppAdVertiseInfo;

@Controller
@RequestMapping("/appAdvertiseInfo")
public class AppAdVertiseInfoController {

	@Resource
	private AppAdVertiseInfoFacade appAdVertiseInfoFade;

	@RequestMapping(value ="/getAppAdVertiseInfo",method = RequestMethod.POST)
	@ResponseBody
	@ParamsValidate(value = {@ParamValidate(name = "advertiseType", required = true),
			@ParamValidate(name="osType",required = true),
			@ParamValidate(name="deviceType",required = true),
			@ParamValidate(name="sign",required = true),
			@ParamValidate(name="version",required = true)})
	public String getAppAdVertiseInfo(HttpServletRequest request,
			@RequestParam Map<String, String> params){
		String appType = params.get("osType");
		params.put("appType", appType);
		ReturnBean<List<AppAdVertiseInfo>> returnBean = new ReturnBean<List<AppAdVertiseInfo>>();
		try{
			returnBean = appAdVertiseInfoFade.getAppAdVertises(params);
			returnBean.setCode(RetCodeConstants.SUCCESS);
			returnBean.setMsg(RetCodeConstants.SUCCESS_DESC);
			LogUtil.info("method getAppAdVertiseInfo banner查询接口data={}",JSON.toJSONString(returnBean));
		}catch (Exception e) {
			LogUtil.error("method getAppAdVertiseInfo banner查询接口异常 ERROR:{}",e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setMsg(RetCodeConstants.ERROR_DESC);
		  }
		return JSON.toJSONString(returnBean);

	}

}
