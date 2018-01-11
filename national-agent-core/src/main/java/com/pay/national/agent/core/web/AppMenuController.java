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
import com.pay.national.agent.core.facade.AppMenuFacade;
import com.pay.national.agent.model.annotation.ParamValidate;
import com.pay.national.agent.model.annotation.ParamsValidate;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.constants.RetCodeConstants;

/**
 * @Description: app菜单管理controller
 * @see: 需要参考的类
 * @version 2017年9月13日 上午10:54:37
 * @author zhenhui.liu
 */
@RequestMapping("/appMenu")
@Controller
public class AppMenuController {

	@Resource
	private AppMenuFacade appMenuFacade;
	
	@RequestMapping(value = "/findAllMenu" ,method=RequestMethod.POST)
	@ParamsValidate(value = {
			@ParamValidate(name="osType",required = true),
			@ParamValidate(name="version",required = true),
			@ParamValidate(name="deviceType",required = true)})
	public @ResponseBody String findAllMenu(HttpServletRequest request,@RequestParam Map<String, String> params){
		LogUtil.info("查找所有菜单 start params:{}",params);
		ReturnBean<Object> returnBean = new ReturnBean<>();
		try {
			return appMenuFacade.findAllMenu(params);
		} catch (Exception e) {
			LogUtil.info("查找所有菜单 error params:{}",params,e);
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("查找失败!");
			return JSONUtils.alibabaJsonString(returnBean);
		}
	}
}
