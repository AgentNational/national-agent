package com.pay.national.agent.core.service.common;

import java.util.List;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.query.MessageListQueryBean;
import com.pay.national.agent.model.beans.results.AppMessageListBean;
import com.pay.national.agent.model.entity.AppMessageInfo;

public interface AppMessageInfoService {

	/**
	 * @Description 查找所有的消息
	 * @param MessageListQueryBean 
	 * @param Page
	 * @return
	 * @see 需要参考的类或方法
	 */
	Page<List<AppMessageListBean>> findAllAppMessageInfo(MessageListQueryBean queryBean, Page<List<AppMessageListBean>> page);
	
	/**
	 * @Description 查找未读消息条数
	 * @param userNo 用户编号
	 * @return
	 * @see 需要参考的类或方法
	 */
	Integer findUnReadCount(String userNo);

	/**
	 * @Description 消息詳情
	 * @param messageId 个人消息id
	 * @return
	 * @see 需要参考的类或方法
	 */
	AppMessageInfo findAppMessageInfoDetail(String messageId);

	/**
	 * 获取指定用户的弹窗消息列表
	 * @param userNo 用户编号
	 * @return
	 */
	List<AppMessageInfo> findPopMessages(String userNo);
	
	/**
	 * @Description 修改消息读取状态
	 * @param usreNo 用户编号
	 * @param ids 
	 * @see 需要参考的类或方法
	 */
	ReturnBean<Object> updateIsRead(String usreNo,String[] ids);

	/**
	 * 删除个人消息
	 * @param messageId 个人消息ID
	 */
	ReturnBean<Object> deleteMessage(String messageId);
	
	/**
	 * 新建个人消息
	 * @param message
	 * @return
	 */
	void createMessage(AppMessageInfo message);

}
