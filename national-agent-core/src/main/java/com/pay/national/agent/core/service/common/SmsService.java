package com.pay.national.agent.core.service.common;

import com.aliyuncs.exceptions.ClientException;

/**
 * @Description: 短信相关service
 * @see: 需要参考的类
 * @version 2017年9月6日 上午11:07:58
 * @author zhenhui.liu
 */
public interface SmsService {

	/**
	 * @Description 注册发送短信验证
	 * @param phone
	 * @param msg
	 * @return
	 * @see 需要参考的类或方法
	 */
	boolean sendCheckCodeSms(String phoneNo);

	/**
	 * @Description 找回密码发送短信验证码
	 * @param phoneNo
	 * @return
	 * @see 需要参考的类或方法
	 */
	boolean sendFindPasswordCheckCode(String phoneNo);

	/**
	 * @Description 领取卡友pos验证码
	 * @param phoneNo
	 * @return
	 * @see 需要参考的类或方法
	 */
	boolean sendKaYouPosCheckCode(String phoneNo);

	Boolean sendSms (String openId,String phoneNo) throws ClientException;
}
