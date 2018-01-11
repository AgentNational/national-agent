package com.pay.national.agent.portal.service.common.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pay.commons.utils.lang.StringUtils;
import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.CommonCodeUtil;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.AppMessageInfo;
import com.pay.national.agent.model.entity.PushMessage;
import com.pay.national.agent.model.entity.PushUser;
import com.pay.national.agent.portal.dao.common.AppMessageInfoMapper;
import com.pay.national.agent.portal.dao.common.PushMessageMapper;
import com.pay.national.agent.portal.dao.common.PushUserMapper;
import com.pay.national.agent.portal.service.common.AppMessageInfoService;
import com.pay.national.agent.portal.service.common.PushMessageService;
import com.pay.national.agent.portal.service.common.SmsService;

@Service("appMessageInfoService")
public class AppMessageInfoServiceImpl implements AppMessageInfoService{
	@Resource
	private AppMessageInfoMapper appMessageInfoMapper;
	
	@Resource
	private SmsService smsService;
	
	@Resource
	private PushMessageService pushMessageService;
	
	@Resource
	private PushMessageMapper pushMessageMapper;
	
	@Resource
	private PushUserMapper pushUserMapper;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveMessage(AppMessageInfo appMessageInfo) {
		try {
			appMessageInfo = validateMessage(appMessageInfo);
			appMessageInfoMapper.saveMessage(appMessageInfo);
		} catch (Exception e) {
			LogUtil.error("Method saveMessage error",e);
			throw e;
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

	@Override
	public AppMessageInfo getMessage(String appMessageId) {
		AppMessageInfo appMessageInfo = appMessageInfoMapper.getMessageById(appMessageId);
		return appMessageInfo;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateMessage(AppMessageInfo appMessageInfo) {
		try {
			appMessageInfoMapper.update(appMessageInfo);
		} catch (Exception e) {
			LogUtil.error("Method updateMessage error",e);
			throw e;
		}
	}

	@Override
	public List<Map<String, Object>> findAllMessage(Page<Map<String, Object>> page,Map<String, Object> queryParams) {
		List<Map<String, Object>>  appMessageInfoList = appMessageInfoMapper.findAllMessage(page,queryParams);
		return appMessageInfoList;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean sendMessage(String messageId) {
		AppMessageInfo message = appMessageInfoMapper.getMessageById(messageId);
		PushUser pushUser = pushUserMapper.findByUser(message.getUserName());
		LogUtil.info("sendMessage message:{},pushUser:{}",message,pushUser);
		if(null != pushUser){
			PushMessage pushMessage = new PushMessage();
			pushMessage.setClientType(pushUser.getClientType());
			pushMessage.setContent(message.getBriefContent());
			pushMessage.setCreateTime(new Date());
			pushMessage.setDeviceToken(pushUser.getDeviceToken());
			pushMessage.setJumpType("native");
			pushMessage.setModel("messageList");
			pushMessage.setUserName(pushUser.getUserNo());
			ReturnBean<String> returnBean = pushMessageService.publish(pushMessage);
			if(RetCodeConstants.SUCCESS.equals(returnBean.getCode())){
				pushMessage.setStatus("SUCCESS");
				pushMessageMapper.insert(pushMessage);
				return true;
			}else{
				pushMessage.setStatus("FAIL");
				pushMessageMapper.insert(pushMessage);
				return false;
			}
		}
		return false;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteMessage(String messageId) {
		appMessageInfoMapper.deleteMessage(messageId);		
	}
}
