package com.pay.national.agent.core.facade.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pay.national.agent.common.exception.NationalAgentException;
import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.core.facade.BankCardFacade;
import com.pay.national.agent.core.service.common.BankCardService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.constants.RetCodeConstants;

/**
 * 
 * @Description: 银行卡相关DUBBO服务
 * @see: BankCardFacadeImpl 此处填写需要参考的类
 * @version 2016年12月21日 下午4:19:39
 * @author chao.wang
 */
@Service("bankCardFacade")
public class BankCardFacadeImpl implements BankCardFacade {
	private Logger logger = LoggerFactory.getLogger(BankCardFacadeImpl.class);

	@Resource
	private BankCardService bankCardService;

	@Override
	public String findAllSabkName(Map<String, String> args) {
		logger.info("dubbo method findAllSabkName params = {}", args);
		ReturnBean<String> returnBean = new ReturnBean<String>();
		try {
			return JSONUtils.alibabaJsonString(bankCardService.findAllSabkName(args));
		} catch (NationalAgentException e) {
			logger.error("查询银行卡总行信息失败!", e);
			returnBean.setCode(e.getCode());
			returnBean.setMsg(e.getMessage());
			return JSONUtils.alibabaJsonString(returnBean);
		} catch (Exception e) {
			logger.error("查询银行卡总行信息失败!", e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setMsg(RetCodeConstants.ERROR_DESC);
			return JSONUtils.alibabaJsonString(returnBean);
		}
	}

	@Override
	public String findAllBankInfo(Map<String, String> args) {
		logger.info("dubbo method findAllBankInfo params = {}", args);
		ReturnBean<String> returnBean = new ReturnBean<String>();
		try {
			return JSONUtils.alibabaJsonString(bankCardService.findAllBankInfo(args));
		} catch (NationalAgentException e) {
			logger.error("查询支行信息失败!", e);
			returnBean.setCode(e.getCode());
			returnBean.setMsg(e.getMessage());
			return JSONUtils.alibabaJsonString(returnBean);
		} catch (Exception e) {
			logger.error("查询支行信息失败!", e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setMsg(RetCodeConstants.ERROR_DESC);
			return JSONUtils.alibabaJsonString(returnBean);
		}
	}

}
