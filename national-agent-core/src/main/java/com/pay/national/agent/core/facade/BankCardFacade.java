package com.pay.national.agent.core.facade;

import java.util.Map;

/**
 * 
 * @Description: 提供银行卡相关DUBBO服务
 * @see: BankCardFacade 此处填写需要参考的类
 * @version 2016年12月21日 下午4:16:06
 * @author chao.wang
 */
public interface BankCardFacade {

	/**
	 * 
	 * @Description 查询总行信息
	 * @param params
	 * @return
	 * @see 需要参考的类或方法
	 */
	public String findAllSabkName(Map<String, String> args);

	/**
	 * 
	 * @Description 查询支行信息
	 * @param params
	 * @return
	 * @see 需要参考的类或方法
	 */
	public String findAllBankInfo(Map<String, String> args);
}
