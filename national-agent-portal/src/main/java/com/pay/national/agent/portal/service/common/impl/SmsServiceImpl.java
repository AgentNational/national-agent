package com.pay.national.agent.portal.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pay.camel.client.api.CamelClientApi;
import com.pay.camel.remote.bean.CamelResponse;
import com.pay.camel.remote.bean.Goods;
import com.pay.camel.remote.bean.MessageLevel;
import com.pay.camel.remote.bean.MessageSendType;
import com.pay.camel.remote.bean.MessageType;
import com.pay.camel.remote.bean.MessgReceiver;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.portal.service.common.SmsService;

@Service("/smsService")
public class SmsServiceImpl implements SmsService{
	private static final String APP_CODE = "agentmobile";
	private static final String CAMEL_TOKEN = "fahEFC3FgqvjuwZdxLO/GQ==";
	private static final String busiType = "全民代理";
	@Resource
	private CamelClientApi camelClient;

	@Override
	public ReturnBean<String> sendSms(String phoneNo, String content) {
		ReturnBean<String> returnBean = new ReturnBean<>();
		Goods goods = new Goods();
        goods.setMessgType(MessageType.NOTE);
        goods.setMessgLevel(MessageLevel.INFO);
        goods.setAppCode(APP_CODE);
        goods.setBusiType(busiType);
        goods.setToken(CAMEL_TOKEN);
        goods.setContent(content);
        List<MessageSendType> messgSendTypes = new ArrayList<MessageSendType>();
        messgSendTypes.add(MessageSendType.SMS);
        goods.setMessgSendTypes(messgSendTypes);
        MessgReceiver messgReceiver = new MessgReceiver();
        goods.setMessgReceiver(messgReceiver);
        messgReceiver.setPhone(phoneNo);
        CamelResponse response = null;
        //调用短信平台发送短信
		try {
			response = camelClient.send(goods);
			if(response.getResult()){
				returnBean.setCode(RetCodeConstants.SUCCESS);
			}else{
				returnBean.setCode(RetCodeConstants.FAIL);
			}
		} catch (Exception e) {
			LogUtil.error("sendSms error:",e);
			returnBean.setCode(RetCodeConstants.ERROR);
		}
		return returnBean;
	}

}
