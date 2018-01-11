package com.pay.national.agent.core.service.common;

import java.util.Map;

import com.pay.national.agent.model.beans.ReturnBean;

/**
 * 
 * @Description: 提供银行卡相关的服务
 * @see: CommonService 此处填写需要参考的类
 * @version 2016年12月21日 下午3:44:01
 * @author chao.wang
 */
public interface BankCardService {

	/**
	 * 
	 * @Description 查询总行信息
	 * @param params
	 * @return
	 * @see 需要参考的类或方法
	 */
	public ReturnBean<String> findAllSabkName(Map<String, String> args);

	/**
	 * 
	 * @Description 查询支行信息
	 * @param params
	 * @return
	 * @see 需要参考的类或方法
	 */
	public ReturnBean<String> findAllBankInfo(Map<String, String> args);
}
