package com.pay.national.agent.core.facade;

import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.query.PushMessageBean;

public interface PushMsgServiceFacade {

	/**
	 * 消息-用户注册
	 * @param pushId 设备对应生成的pushId
	 * @param osType 设备类型
	 * @param userNo 在消息平台注册的用户编号（确保唯一）
	 * @param deviceToken 设备标识
	 * @return
	 */
	ReturnBean<String> register(String userNo, String pushId, String osType,String deviceToken);

	/**
	 * 消息-用户解绑
	 * @param userNo 在消息平台注册的用户编号
	 * @return
	 */
	ReturnBean<String> unbind(String userNo);

	/**
	 * 测试推送
	 * @param bean
	 * @return
	 */
	ReturnBean<String> push(PushMessageBean bean);

}
