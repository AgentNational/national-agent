package com.pay.national.agent.core.web;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.core.facade.BankCardFacade;
import com.pay.national.agent.model.annotation.ParamValidate;
import com.pay.national.agent.model.annotation.ParamsValidate;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.constants.RetCodeConstants;

/**
 * @Description: 查询银行信息
 * @see: 需要参考的类
 * @version 2017年9月12日 上午9:19:12
 * @author zhenhui.liu
 */
@Controller
@RequestMapping("/bankCard")
public class BankCardController {

	private Logger logger = LoggerFactory.getLogger(BankCardController.class);

	@Resource
	private BankCardFacade bankCardFacade;

	/**
	 * @Description 查询总行信息
	 * @param params
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping(value = "/findAllSabkName",method=RequestMethod.POST)
	@ParamsValidate(value = { @ParamValidate(name = "loginKey", required = true),
			@ParamValidate(name = "bankName", required = true), @ParamValidate(name = "sign", required = true) })
	public @ResponseBody String findAllSabkName(@RequestParam Map<String, String> params) {
		logger.info("method findAllSabkName,request params = {}", params);
		ReturnBean<String> returnBean = new ReturnBean<String>();
		try {
			return bankCardFacade.findAllSabkName(params);
		} catch (Exception e) {
			logger.error("method findAllSabkName 请求失败 ", e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setMsg(RetCodeConstants.ERROR_DESC);
		}
		return JSONUtils.alibabaJsonString(returnBean);
	}

	/**
	 * @Description 查询支行信息
	 * @param params
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping(value = "/findAllBankInfo",method=RequestMethod.POST)
	@ParamsValidate(value = { @ParamValidate(name = "loginKey", required = true),
			@ParamValidate(name = "bankName", required = true), @ParamValidate(name = "lefuAreaCode", required = true),
			@ParamValidate(name = "sign", required = true) })
	public @ResponseBody String findAllBankInfo(@RequestParam Map<String, String> params) {
		logger.info("method findAllBankInfo,request params = {}", params);
		ReturnBean<String> returnBean = new ReturnBean<String>();
		try {
			return bankCardFacade.findAllBankInfo(params);
		} catch (Exception e) {
			logger.error("method findAllSabkName 请求失败 ", e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setMsg(RetCodeConstants.ERROR_DESC);
		}
		return JSONUtils.alibabaJsonString(returnBean);
	}
}
