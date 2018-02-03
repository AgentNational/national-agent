package com.pay.national.agent.core.service.common;

public interface UserService {



	/**
	 * 公众号用户注册
	 * @param fromUserName
	 * @param eventKey
	 */
	void register(String fromUserName, String eventKey);
}
