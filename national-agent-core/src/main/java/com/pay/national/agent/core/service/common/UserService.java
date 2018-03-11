package com.pay.national.agent.core.service.common;

import com.pay.national.agent.model.entity.AppUser;

public interface UserService {



	/**
	 * 公众号用户注册
	 * @param fromUserName
	 * @param eventKey
	 */
	void register(String fromUserName, String eventKey);

	/**
	 * 校验是否需要填写手机号
	 * @param openId
	 * @return
	 */
    String checkPhone(String openId);

	/**
	 * 完善手机号
	 * @param openId
	 * @param checkCode
	 * @param phoneNo
	 * @return
	 */
	String completelPhone(String openId, String checkCode, String phoneNo);

	/**
	 * 
	 * @param openId
	 * @param phoneNo
	 * @return
	 */
    String sendCheckCode(String openId, String phoneNo);

	/**
	 * 校验是否有代理权限
	 * @param userNo
	 * @return
	 */
	String checkAgentRight(String userNo);


	/**
	 * 查找用户信息
	 * @param userNo
	 * @return
	 */
	AppUser findUserInfo(String userNo);
}
