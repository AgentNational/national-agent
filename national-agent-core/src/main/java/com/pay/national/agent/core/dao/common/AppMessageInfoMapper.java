package com.pay.national.agent.core.dao.common;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.model.beans.query.MessageListQueryBean;
import com.pay.national.agent.model.beans.results.AppMessageListBean;
import com.pay.national.agent.model.entity.AppMessageInfo;

public interface AppMessageInfoMapper {
	/**
	 * @Description 查询消息列表
	 * @param userName
	 * @return
	 * @see 需要参考的类或方法
	 */
	List<AppMessageListBean> findAllMessageInfo(@Param("queryBean")MessageListQueryBean queryBean, @Param("page")Page<List<AppMessageListBean>> page);

    /**
     * 查询有效消息中未读消息的条数
     * @param userName
     * @return
     */
	Integer unReadCount(@Param("userName")String userName);

	/**
	 * 获取消息
	 * @param id
	 * @return
	 */
	AppMessageInfo selectByPrimaryKey(@Param("id")String id);

	/**
	 * 获取指定用户的弹窗消息列表
	 * @param userName
	 * @return
	 */
	List<AppMessageInfo> findPopMessages(String userName);
	
	 /**
	 * 修改消息读取状态
	 * @param ids 
	 * @param userName 
	 * @Description 
	 * @see 需要参考的类或方法
	 */
	void updateIsRead(@Param("userName")String userName, @Param("id")String id);

	/**
	 * 修改消息状态
	 * @param status
	 * @param messageId
	 */
	void updateStatus(@Param("status")String status,@Param("messageId") String messageId);

	/**
	 * 新建消息
	 * @param message
	 */
	void insert(AppMessageInfo message);
}