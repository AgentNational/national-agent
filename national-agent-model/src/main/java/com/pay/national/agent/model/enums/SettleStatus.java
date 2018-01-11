package com.pay.national.agent.model.enums;
/**
 * 结算状态
 * @author shuyan.qi
 * Date:2017年9月6日下午10:40:35
 */
public enum SettleStatus {
	/**
	 * 初始化
	 */
	INIT,

	/**
	 * 发送付款请求
	 */
	REMIT_REQ_SEND,

	/**
	 * 付款处理中
	 */
	REMIT_PROCESSING,

	/**
	 *付款成功
	 */
	SUCCESS,

	/**
	 * 付款失败
	 */
	FAILED,

	/**
	 * 付款撤销
	 */
	CANCEL,

	/**
	 * 付款已汇出
	 */
	MANUAL_REMITED
}
