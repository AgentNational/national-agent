package com.pay.national.agent.portal.service.common;

import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.entity.PushMessage;

public interface PushMessageService {
	
	/**
	 * 消息-推送消息
	 * @param messageBean
	 * @return
	 */
	ReturnBean<String> publish(PushMessage pushMessage);

}
