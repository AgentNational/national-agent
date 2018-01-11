package com.pay.national.agent.portal.service.common;

import com.pay.national.agent.model.beans.ReturnBean;

public interface SmsService {
	/**
	 * 发送短信
	 * @param phoneNo
	 * @param content
	 * @return
	 */
	ReturnBean<String> sendSms(String phoneNo,String content);
}
