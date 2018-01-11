package com.pay.national.agent.core.facade;

import java.util.List;
import java.util.Map;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.results.AppMessageDetailBean;
import com.pay.national.agent.model.beans.results.AppMessageListBean;
import com.pay.national.agent.model.beans.results.AppPopListBean;

public interface AppMessageInfoFacade {
	/**
	 * @Description 查询当前用户所有的消息
	 * @param userNo 用户编号
	 * @param currentPage 页码
	 * @return
	 * @see 需要参考的类或方法
	 */
	public ReturnBean<Page<List<AppMessageListBean>>> findAllAppMessageInfo(String userNo,Integer currentPage);

	/**
	 * @Description 查询消息详情
	 * @param messageId
	 * @return
	 * @see 需要参考的类或方法
	 */
	public ReturnBean<AppMessageDetailBean> findAppMessageInfoDetail(String messageId);

	/**
	 * 查询弹窗消息和弹窗公告列表
	 * @param userNo 用户编号
	 * @param userRole 用户角色
	 * @return
	 */
	public ReturnBean<List<AppPopListBean>> findPopList(String userNo,String userRole);

	/**
	 * 查询用户未读消息数和未读公告数
	 * @param userNo 用户编号
	 * @param userRole 用户角色
	 * @return
	 */
	public ReturnBean<Map<String,Integer>> findUnReadCount(String userNo,String userRole);
	
	/**
	 * @Description 修改读取状态
	 * @param usreNo
	 * @param ids
	 * @see 需要参考的类或方法
	 */
	public ReturnBean<Object> updateIsRead(String usreNo,String[] ids);

	/**
	 * 删除个人消息
	 * @param messageId
	 * @return
	 */
	public ReturnBean<Object> deleteMessage(String messageId);

}
