package com.pay.national.agent.core.facade.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pay.national.agent.core.facade.PushMsgServiceFacade;
import com.pay.national.agent.core.service.common.PushMsgService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.query.PushMessageBean;

/**
 * 
 * @author shuyan.qi
 * Date:2017年9月8日上午12:50:19
 */
@Service("pushMsgServiceFacade")
public class PushMsgServiceFacadeImpl implements PushMsgServiceFacade{
	
	@Resource
	private PushMsgService pushMsgService;
	
	@Override
	public ReturnBean<String> register(String userNo, String pushId, String osType,String deviceToken) {
		return pushMsgService.register(pushId, osType, userNo,deviceToken);
	}


	@Override
	public ReturnBean<String> unbind(String userNo) {
		return pushMsgService.unbunding(userNo);
	}


	@Override
	public ReturnBean<String> push(PushMessageBean bean) {
		return pushMsgService.publish(bean);
	}

}
