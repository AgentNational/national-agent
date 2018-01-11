package com.pay.national.agent.core.service.common.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.pay.commons.utils.web.HttpClientUtils;
import com.pay.commons.utils.web.HttpClientUtils.Method;
import com.pay.national.agent.common.utils.PropertiesLoader;
import com.pay.national.agent.core.service.common.BankCardService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.constants.RetCodeConstants;

/**
 * 
 * @Description: 这里用一句话描述这个类的作用
 * @see: CommonServiceImpl 此处填写需要参考的类
 * @version 2016年12月21日 下午3:44:36
 * @author chao.wang
 */
@Service("bankCardService")
public class BankCardServiceImpl implements BankCardService {

	private Logger logger = LoggerFactory.getLogger(BankCardServiceImpl.class);

	private static PropertiesLoader propertiesLoader = new PropertiesLoader("system.properties");

	private static final String bankInfoUrl = propertiesLoader.getProperty("customer.cnaps.bankinfo");

	@Override
	public ReturnBean<String> findAllSabkName(Map<String, String> args) {
		logger.info("method findAllSabkName params = " + args);
		ReturnBean<String> returnBean = new ReturnBean<String>(RetCodeConstants.SUCCESS, "查询成功");
		String jsonStr = "";
		try {
			String sabkName = args.get("bankName").trim();
			Map<String, String> params = new HashMap<String, String>();

			params.put("fields", "providerCode,name,providerCode,clearingBankCode");
			params.put("clearBankLevel", "1");
			try {
				params.put("word", URLEncoder.encode(sabkName, "utf-8"));
			} catch (UnsupportedEncodingException e1) {
				logger.error("method findAllSabkName UnsupportedEncodingException", e1);
			}
			StringBuffer sb = new StringBuffer("?fields=providerCode,name,providerCode,clearingBankCode");
			sb.append("&clearBankLevel=1").append("&word=").append(URLEncoder.encode(sabkName, "utf-8"));
			//jsonStr = HttpClientUtils.send(Method.POST, bankInfoUrl, params, true, Charset.forName("UTF-8").name());
			jsonStr = HttpClientUtils.send(Method.POST, bankInfoUrl + sb.toString(), "", true, Charset.forName("UTF-8").name());
			logger.info("method findAllSabkName 请求返回参数为:{}", jsonStr);
			returnBean.setData(jsonStr);
		} catch (Exception e) {
			logger.error("method findAllSabkName 查询银行卡总行信息失败", e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setMsg("查询银行卡总行信息失败");
			return returnBean;
		}
		return returnBean;
	}

	@Override
	public ReturnBean<String> findAllBankInfo(Map<String, String> args) {
		logger.info("method findAllBankInfo params = " + args);
		ReturnBean<String> returnBean = new ReturnBean<String>(RetCodeConstants.SUCCESS, "查询成功");
		String jsonStr = "";
		String bankName = args.get("bankName");
		String bankCode = args.get("bankCode");
		String areaCode = args.get("areaCode");
		if (StringUtils.hasText(bankCode) && "999".equals(bankCode)) {
			return findAllSabkName(args);
		}
		Map<String, String> params = new HashMap<String, String>();

		params.put("areaCode", areaCode);
		params.put("fields", "providerCode,name,providerCode,clearingBankCode");
		StringBuffer sb = new StringBuffer("?fields=providerCode,name,providerCode,clearingBankCode");
		sb.append("&areaCode=").append(areaCode);
		if (StringUtils.hasText(bankCode)) {
			params.put("providerCode", bankCode);
			sb.append("&providerCode=").append(bankCode);
		}
		try {
			params.put("word", URLEncoder.encode(bankName, "utf-8"));
			sb.append("&word=").append(URLEncoder.encode(bankName, "utf-8"));
		} catch (UnsupportedEncodingException e1) {
			logger.error("method findAllBankInfo UnsupportedEncodingException", e1);
		}
		logger.info("method findAllBankInfo 上送参数：" + params);
		try {
			//jsonStr = HttpClientUtils.send(Method.POST, bankInfoUrl, params, true, Charset.forName("UTF-8").name());
			jsonStr = HttpClientUtils.send(Method.POST, bankInfoUrl + sb.toString(), "", true, Charset.forName("UTF-8").name());
			returnBean.setData(jsonStr);
		} catch (Exception e) {
			logger.error("method findAllBankInfo 查询银行卡支行信息失败", e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setMsg("查询银行卡支行信息失败");
			return returnBean;
		}
		logger.info("findAllBankInfoJson 查询返回：" + jsonStr);
		return returnBean;
	}

}
