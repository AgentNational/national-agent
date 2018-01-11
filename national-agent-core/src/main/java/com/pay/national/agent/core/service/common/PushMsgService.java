package com.pay.national.agent.core.service.common;

import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.query.PushMessageBean;

/**
 * 消息推送service(目前使用的是LFT的推送服务)
 * @author shuyan.qi
 * Date:2017年9月7日下午11:44:20
 */
public interface PushMsgService {
	/**
	 * 消息-用户注册
	 * @param pushId 设备对应生成的pushId
	 * @param osType 设备类型
	 * @param userNo 在消息平台注册的用户编号（确保唯一）
	 * @param deviceToken 设备号
	 * @return
	 */
	ReturnBean<String> register(String pushId,String osType,String userNo,String deviceToken);
	
	/**
	 * 消息-用户解绑
	 * @param userNo 在消息平台注册的用户编号
	 * @return
	 */
	ReturnBean<String> unbunding(String userNo);
	
	/**
	 * 消息-推送消息
	 * @param messageBean
	 * @return
	 */
	ReturnBean<String> publish(PushMessageBean messageBean);
}
