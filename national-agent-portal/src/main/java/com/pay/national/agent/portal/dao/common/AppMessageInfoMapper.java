package com.pay.national.agent.portal.dao.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.model.entity.AppMessageInfo;

public interface AppMessageInfoMapper {
	/**
	 *  添加消息
	 * @param appMessageInfo
	 */
	void saveMessage(AppMessageInfo appMessageInfo);

	/**
	 * 通过id获取message
	 * @param appMessageId
	 * @return
	 */
	AppMessageInfo getMessageById(@Param("id")String id);
	
	/**
	 * 查询消息列表
	 * @param page
	 * @param appMessageInfo
	 * @return
	 */
	List<Map<String, Object>> findAllMessage(@Param("page")Page<Map<String, Object>> page,@Param("queryParams") Map<String, Object> queryParams);

	/**
	 * 删除消息
	 * @param messageId
	 */
	void deleteMessage(@Param("messageId")String messageId);

	/**
	 * 更新消息
	 * @param appMessageInfo
	 */
	void update(@Param("message")AppMessageInfo appMessageInfo);
}