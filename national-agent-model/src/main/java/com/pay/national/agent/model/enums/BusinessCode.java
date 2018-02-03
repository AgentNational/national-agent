package com.pay.national.agent.model.enums;
/**
 * 业务编码
 * @author shuyan.qi
 * Date:2017年9月11日上午6:29:58
 */
public enum BusinessCode {
	JIAOTONG("交通银行信用卡"),

	PUFA("浦发银行信用卡"),

	PINGAN("平安银行信用卡"),

	XZF("新中付"),

	YIPIAO("易票二维码水牌"),

	REMIT_YAJIN("提现(扣除押金39元)"),

	REMIT_USER("提现");

	private String busienssName;

	private BusinessCode(String busienssName) {
		this.busienssName = busienssName;
	}

	public String getBusienssName(){
		return this.busienssName;
	}
}
