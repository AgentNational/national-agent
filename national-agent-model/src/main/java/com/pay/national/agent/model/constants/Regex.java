package com.pay.national.agent.model.constants;

/**
 * @Description: 正则表达式
 * @see: 需要参考的类
 * @version 2016年11月24日 上午10:40:41
 * @author zhenhui.liu
 */
public class Regex {
	public static final String PHONE = "^1[34587]\\d{9}$"; // 手机号码正则

	public static final String MONEY = "^(([1-9]\\d{0,13})|([0]{1}))(\\.(\\d){0,2})?$"; // 金额正则decimal(14,2)

	public static final String EMAIL = "^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"; //电子邮箱

	public static final String NUMBER = "^[1-9]\\d*$"; //正整数
	
	public static final String NAME = "^[\u4e00-\u9fa5]([\u4e00-\u9fa5]{0,24}\u00b7{0,1}[\u4e00-\u9fa5]{1,24})+$"; //中文姓名
	
	
	
}
