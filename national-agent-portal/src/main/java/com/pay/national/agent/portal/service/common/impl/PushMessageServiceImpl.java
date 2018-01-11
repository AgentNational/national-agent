package com.pay.national.agent.portal.service.common.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pay.astrotrain.client.ATMessage;
import com.pay.astrotrain.client.ATProducer;
import com.pay.astrotrain.client.exceptions.ATException;
import com.pay.astrotrain.client.message.StringMessage;
import com.pay.astrotrain.client.producer.DefaultATProducer;
import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.common.utils.PropertiesLoader;
import com.pay.national.agent.common.utils.StringUtils;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.query.PushMessageBean;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.PushMessage;
import com.pay.national.agent.portal.service.common.PushMessageService;

@Service("pushMessageService")
public class PushMessageServiceImpl implements PushMessageService{
	private static final PropertiesLoader property = new PropertiesLoader("astrotrain-producer.properties");
	private static final String mcTopic;
	private static final String appId;
	
	static{
		mcTopic = property.getProperty("mc.topic");
		appId = property.getProperty("mc.app");
    }
	
	@Resource
	private DefaultATProducer mcProducer;

	@Override
	public ReturnBean<String> publish(PushMessage pushMessage) {
		
		PushMessageBean pushMessageBean = new PushMessageBean();
		pushMessageBean.setApp(appId);
		pushMessageBean.setPush_bar_txt(pushMessage.getContent());
		pushMessageBean.setUser_no(pushMessage.getUserName());
		setRedirectParams(pushMessage,pushMessageBean);
		
		LogUtil.info("publish pushMessageBean:{}",JSONUtils.alibabaJsonString(pushMessageBean));
		ReturnBean<String> returnBean = new ReturnBean<String>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
		ATProducer atProducer = mcProducer.createProducer(mcTopic);
		
		String publishContent = JSONUtils.alibabaJsonString(pushMessageBean);
		StringMessage msg = new StringMessage(publishContent);
		String msgKey = pushMessageBean.getUser_no()+"_"+System.currentTimeMillis();
		LogUtil.info("publish 推送消息 msgKey :{}",msgKey);
		msg.setProperty(ATMessage.MSG_KEYS,msgKey);//确保唯一
		try {
			atProducer.send(msg);
		} catch (ATException e) {
			LogUtil.error("publish 推送消息    自定义异常:{}",e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setMsg("推送消息异常");
		}catch (Throwable e){
			LogUtil.error("publish 推送消息  error:{}",e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setMsg("推送消息异常");
		}
		return returnBean;
	}

	private void setRedirectParams(PushMessage pushMessage,PushMessageBean pushMessageBean) {
		Map<String, String> redirect_params = null;
		try {
			redirect_params = new HashMap<String, String>();
			String type = pushMessage.getJumpType();
			if(StringUtils.isNotBlank(type))  redirect_params.put("type",type);
			String keys = pushMessage.getProperties();
			if(StringUtils.isNotBlank(keys))  redirect_params.put("keys", keys);
			String model = pushMessage.getModel();
			if(StringUtils.isNotBlank(model))  redirect_params.put("redirect", model);
			String vals = pushMessage.getVals();
			if(StringUtils.isNotBlank(vals)){
				Map<String,String> map1 = JSONUtils.toObject(vals,Map.class);
				redirect_params.putAll(map1);
			}
			pushMessageBean.setRedirect_params(JSONUtils.alibabaJsonString(redirect_params));
		} catch (Exception e) {
			LogUtil.error("setRedirectParams error:{},pushMessage:{}",e,pushMessage);
		}
	}

}
