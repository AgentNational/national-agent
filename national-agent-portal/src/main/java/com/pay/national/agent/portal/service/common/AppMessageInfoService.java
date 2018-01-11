package com.pay.national.agent.portal.service.common;

import java.util.List;
import java.util.Map;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.model.entity.AppMessageInfo;

public interface AppMessageInfoService {
	/**
	 * 添加消息
	 * @param appMessageInfo
	 * @return 
	 */
	void saveMessage(AppMessageInfo appMessageInfo);

	/**
	 * 通过id获取app消息
	 * @param appMessageId
	 * @return
	 */
	AppMessageInfo getMessage(String appMessageId);

	/**
	 * 修改消息
	 * @param appMessageInfo
	 * @return 
	 */
	void updateMessage(AppMessageInfo appMessageInfo);

	/**
	 * 查询消息列表
	 * @param page
	 * @param appMessageInfo
	 * @return
	 */
	List<Map<String, Object>> findAllMessage(Page<Map<String, Object>> page, Map<String, Object> queryParams);

	/**
	 * 推送消息
	 * @param messageId 
	 * @return
	 */
	boolean sendMessage(String messageId);

	/**
	 * 删除消息
	 * @param messageId
	 */
	void deleteMessage(String messageId);
}
