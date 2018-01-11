package com.pay.national.agent.core.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.facade.AppFeedbackFacade;
import com.pay.national.agent.model.annotation.ParamValidate;
import com.pay.national.agent.model.annotation.ParamsValidate;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.constants.RetCodeConstants;


/**
 * @ClassName:  AppFeedbackController
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: xiaodi.fu
 * @date:   2017年9月11日 下午2:38:17
 *
 */
@Controller
@RequestMapping("/appFeedback")
public class AppFeedbackController {

	@Resource
	private AppFeedbackFacade appFeedbackFacade;


	@RequestMapping(value = "/saveFeedback", method = RequestMethod.POST)
	@ParamsValidate(value = {@ParamValidate(name = "content", required = true),
			@ParamValidate(name="osType",required = true),
			@ParamValidate(name="deviceType",required = true),
			@ParamValidate(name="sign",required = true),
			@ParamValidate(name="loginKey",required = true),
			@ParamValidate(name="userName",required = true),
			@ParamValidate(name="version",required = true)})
	public @ResponseBody String saveFeedback(HttpServletRequest request,
			@RequestParam Map<String, String> params) {
		LogUtil.info("method saveFeedback params:{}",JSONUtils.toJsonString(params));
		ReturnBean<Map<String, String>> returnBean = new ReturnBean<Map<String, String>>();
		try {
			 appFeedbackFacade.saveFeedback(params);
			 returnBean.setCode(RetCodeConstants.SUCCESS);
			 returnBean.setMsg(RetCodeConstants.SUCCESS_DESC);
			 LogUtil.info("method saveFeedback date={}",JSONUtils.alibabaJsonString(returnBean));
			 return JSONUtils.alibabaJsonString(returnBean);
		} catch(Exception e) {
			LogUtil.error("method saveFeedback 调用dubbo异常 : ", e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setMsg(RetCodeConstants.ERROR_DESC);
			return JSONUtils.alibabaJsonString(returnBean);
		}
	}


}

