package com.pay.national.agent.core.service.common.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pay.commons.cache.util.CacheUtils;
import com.pay.national.agent.common.persistence.Page;
import com.pay.commons.utils.lang.StringUtils;
import com.pay.national.agent.common.utils.CommonCodeUtil;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.dao.common.AppMessageInfoMapper;
import com.pay.national.agent.core.service.common.AppMessageInfoService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.query.MessageListQueryBean;
import com.pay.national.agent.model.beans.results.AppMessageListBean;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.AppMessageInfo;
import com.pay.national.agent.model.enums.AppMessageStatus;

@Service("appMessageInfoService")
public class AppMessageInfoServiceImpl implements AppMessageInfoService{
	private static final String SET_READ_LOCK  = "setReadLock1";
	
	@Resource
	private AppMessageInfoMapper appMessageInfoMapper;
	
	/**
	 * 查找当前用户所有的消息
	 */
	@Override
	public Page<List<AppMessageListBean>> findAllAppMessageInfo(MessageListQueryBean queryBean, Page<List<AppMessageListBean>> page) {
		LogUtil.info("findAllAppMessageInfo queryBean:{},page:{}",queryBean,page);
		List<AppMessageListBean>  messageList = appMessageInfoMapper.findAllMessageInfo(queryBean,page);
		page.setObject(messageList);
		return page;
	}

	/**
	 * 查找未读消息条数
	 */
	@Override
	public Integer findUnReadCount(String userNo) {
		LogUtil.info("findUnReadCount userNo:{}",userNo);
		return appMessageInfoMapper.unReadCount(userNo);
	}

	/**
	 * 消息詳情
	 */
	@Override
	public AppMessageInfo findAppMessageInfoDetail(String id) {
		LogUtil.info("findAppMessageInfoDetail id:{}",id);
		return appMessageInfoMapper.selectByPrimaryKey(id);
		
	}

	/**
	 * 获取指定用户的弹窗消息列表
	 * @param userNo
	 * @return
	 */
	@Override
	public List<AppMessageInfo> findPopMessages(String userNo) {
		LogUtil.info("findPopMessages userNo:{}",userNo);
		return appMessageInfoMapper.findPopMessages(userNo);
	}
	
	/**
	 * 修改消息读取状态
	 */
	@Override
	public ReturnBean<Object> updateIsRead(String usreNo,String[] ids) {
		ReturnBean<Object> returnBean = new ReturnBean<>();
		try {
			for (String messageId : ids) {
				try {
					this.updateRead(usreNo, messageId);
				} catch (Exception e) {
					LogUtil.error("updateIsRead db error usreNo:{},ids:{}",usreNo,ids,e);
				}
			}
			returnBean.setCode(RetCodeConstants.SUCCESS);
			returnBean.setMsg(RetCodeConstants.SUCCESS_DESC);
		} catch (Exception e) {
			LogUtil.error("updateIsRead error usreNo:{},ids:{}",usreNo,ids,e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setErrorCode("0300301");
			returnBean.setMsg("修改消息状态为已读异常");
		}
		return returnBean;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateRead(String userName,String messageId){
		String lock = CacheUtils.get(SET_READ_LOCK+userName+messageId, String.class);
		if(StringUtils.isBlank(lock)){
			CacheUtils.setEx(SET_READ_LOCK+userName+messageId, "addMessageLock", 5);//锁的key
			appMessageInfoMapper.updateIsRead(userName, messageId);
		}
	}

	/**
	 * 删除个人消息
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public ReturnBean<Object> deleteMessage(String messageId) {
		LogUtil.info("deleteMessage messageId:{}",messageId);
		ReturnBean<Object> returnBean = new ReturnBean<>();
		try {
			appMessageInfoMapper.updateStatus(AppMessageStatus.DELETE.name(),messageId);
			returnBean.setCode(RetCodeConstants.SUCCESS);
			returnBean.setMsg("删除成功");
		} catch (Exception e) {
			LogUtil.error("deleteMessage error messageId:{},exception:{}",messageId,e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setErrorCode("0300801");
			returnBean.setMsg("删除消息异常");
		}
		return returnBean;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createMessage(AppMessageInfo message) {
		if(null != message){
			try {
				message = validateMessage(message);
				appMessageInfoMapper.insert(message);
			} catch (Exception e) {
				LogUtil.error("createMessage error",e);
				throw e;
			}
		}
	}

	private AppMessageInfo validateMessage(AppMessageInfo message) {
		if(StringUtils.isBlank(message.getId())){
			message.setId(CommonCodeUtil.getPkId());
		}
		if(StringUtils.isBlank(message.getAbleStatus())){
			message.setAbleStatus("DISABLE");
		}
		if(StringUtils.isBlank(message.getIsRead())){
			message.setIsRead("N");
		}
		if(StringUtils.isBlank(message.getOperator())){
			message.setOperator("system");
		}
		if(null == message.getIsPop()){
			message.setIsPop(false);
		}
		if(null == message.getCreateTime()){
			message.setCreateTime(new Date());
		}
		return message;
	}
}
