package com.pay.national.agent.model.enums;
/**
 * 业务编码
 * @author shuyan.qi
 * Date:2017年9月11日上午6:29:58
 */
public enum BusinessCode {
	/**
	 * 兴业银行信用卡业务
	 */
	XINGYE("兴业银行信用卡"),
	/**
	 * 浦发银行信用卡业务
	 */
	PUFA("浦发银行信用卡"),
	/**
	 * 平安银行信用卡业务
	 */
	PINGAN("平安银行信用卡");


	private String busienssName;

	private BusinessCode(String busienssName) {
		this.busienssName = busienssName;
	}
}
