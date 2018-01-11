package com.pay.national.agent.core.service.common.impl;

import org.springframework.stereotype.Service;

import com.pay.national.agent.core.service.common.PushMsgService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.query.PushMessageBean;

@Service("pushMsgService")
public class PushMsgServiceImpl implements PushMsgService{

	@Override
	public ReturnBean<String> register(String pushId, String osType, String userNo, String deviceToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReturnBean<String> unbunding(String userNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReturnBean<String> publish(PushMessageBean messageBean) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*@Resource
	private DefaultATProducer mcProducer;
	
	private static final PropertyUtil propertyUtil = PropertyUtil.getInstance("system");
	private static String payMessageUrl;//pay统一消息平台url
	private static String appId;//app在消息中心配置的唯一标识
	
	static{
		payMessageUrl = propertyUtil.getProperty("mc.message.url");
		appId = propertyUtil.getProperty("mc.app.id");
    }
	
	@Resource
	private PushUserMapper pushUserMapper;

	*//**
	 * 消息-用户注册
	 * @param map
	 * @return
	 *//*
	@Override
	public ReturnBean<String> register(String pushId,String osType,String userNo,String deviceToken) {
		ReturnBean<String> returnBean = new ReturnBean<String>();
		if(StringUtils.isNotBlank(userNo) && StringUtils.isNotBlank(appId) 
				&& StringUtils.isNotBlank(pushId) && StringUtils.isNotBlank(osType)){
			HashMap<String, String> paramsMap = new HashMap<String,String>();
			paramsMap.put("user_no",userNo );//用户编号
			paramsMap.put("app",appId );//app在消息中心配置的唯一标识
			paramsMap.put("push_id",pushId );//极光注册id
			paramsMap.put("os_type",osType );//app客户端操作系统
			String result = "";
			Map<String,Object> resultMap = null;
			PushUser pushUser = null;
			String pushStatus = "";
			try {
				result = HttpClientUtils.send(HttpClientUtils.Method.GET, payMessageUrl+"mc/register", paramsMap);
				LogUtil.info("register http result userNo:{},pushId:{},osType:{},result:{}",userNo,pushId,osType,result);
				if(StringUtils.isNotBlank(result)){
					resultMap = JSON.parseObject(result, Map.class);
				}
				
				if(resultMap != null){
					if("0000".equals(resultMap.get("code"))){
						returnBean.setCode(RetCodeConstants.SUCCESS);
						returnBean.setMsg("注册消息推送账号成功");
						pushStatus = "SUCCESS";
					}else{
						returnBean.setCode(RetCodeConstants.FAIL);
						returnBean.setMsg("注册消息推送账号失败");
						pushStatus = "FAIL";
					}
				}else{
					returnBean.setCode(RetCodeConstants.FAIL);
					returnBean.setMsg("注册消息推送账号失败");
					pushStatus = "FAIL";
				}
				
				pushUser = pushUserMapper.findByUser(userNo);
				if(null != pushUser){
					pushUser.setAppId(appId);
					pushUser.setClientType(osType);
					pushUser.setDeviceToken(deviceToken);
					pushUser.setPushId(pushId);
					pushUser.setLastUpdateTime(new Date());
					pushUser.setStatus(pushStatus);
					pushUserMapper.updateByPrimaryKey(pushUser);
				}else{
					pushUser = new PushUser();
					pushUser.setAppId(appId);
					pushUser.setClientType(osType);
					pushUser.setCreateTime(new Date());
					pushUser.setDeviceToken(deviceToken);
					pushUser.setPushId(pushId);
					pushUser.setUserNo(userNo);
					pushUser.setStatus(pushStatus);
					pushUserMapper.insert(pushUser);
				}
			} catch (IOException e) {
				LogUtil.error("register http error userNo:{},pushId:{},osType:{},exception:{}",userNo,pushId,osType,e);
				returnBean.setCode(RetCodeConstants.ERROR);
				returnBean.setErrorCode("0300101");
				returnBean.setMsg("注册消息推送账号异常");
			}
		}else{
			LogUtil.error("Method register userNo:{},pushId:{},osType:{},appId:{}",userNo,pushId,osType,appId);
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("注册消息推送账号失败,注册信息不完整");
		}
		return returnBean;
	}

	*//**
	 * 消息-用户解绑
	 * @param map
	 * @return
	 *//*
	@Override
	public ReturnBean<String> unbunding(String userNo) {
		ReturnBean<String> returnBean = new ReturnBean<String>();
		Map<String, String> paramsMap  = new HashMap<String, String>();
		paramsMap.put("user_no", userNo);
		paramsMap.put("app", appId);
		String result = "";
		Map<String,Object> resultMap = null;
		try {
			result = HttpClientUtils.send(HttpClientUtils.Method.GET, payMessageUrl+"mc/unregister", paramsMap);
			LogUtil.info("unbunding userNo:{},result：{}",userNo,result);
			resultMap = JSON.parseObject(result, Map.class);
			
			if(resultMap != null){
				if("0000".equals(resultMap.get("code"))){
					returnBean.setCode(RetCodeConstants.SUCCESS);
					returnBean.setMsg(RetCodeConstants.SUCCESS_DESC);
					PushUser pushUser = pushUserMapper.findByUser(userNo);
					if(null != pushUser){
						pushUser.setStatus("UNBIND");
						pushUser.setLastUpdateTime(new Date());
						pushUserMapper.updateByPrimaryKey(pushUser);
					}
				}else{
					returnBean.setCode(RetCodeConstants.FAIL);
					returnBean.setMsg("解绑失败");
				}
			}else{
				returnBean.setCode(RetCodeConstants.FAIL);
				returnBean.setMsg("解绑失败");
			}
		} catch (IOException e) {
			LogUtil.error("unbunding http error userNo:{},exception:{}",userNo,e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setErrorCode("0300201");
			returnBean.setMsg("解绑异常");
		}
		return returnBean;
	}
	
	*//**
	 * 消息-推送消息
	 * @param messageBean
	 * @return
	 *//*
	@Override
	public ReturnBean<String> publish(PushMessageBean messageBean) {
		messageBean.setApp(appId);
		LogUtil.info("消息平台 推送消息   messageBean:{}",JSONUtils.alibabaJsonString(messageBean));
		ReturnBean<String> returnBean = new ReturnBean<String>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
		PropertyUtil properties = PropertyUtil.getInstance("astrotrain-producer");
		ATProducer atProducer = mcProducer.createProducer(properties.getProperty("mc.topic"));
		
		String publishContent = JSONUtils.alibabaJsonString(messageBean);
		StringMessage msg = new StringMessage(publishContent);
		String msgKey = messageBean.getUser_no()+"_"+System.currentTimeMillis();
		LogUtil.info("消息平台 推送消息 msgKey :{}",msgKey);
		msg.setProperty(ATMessage.MSG_KEYS,msgKey);//确保唯一
		try {
			atProducer.send(msg);
		} catch (ATException e) {
			LogUtil.error("消息平台 推送消息    自定义异常:{}",e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setMsg("推送消息异常");
		}catch (Throwable e){
			LogUtil.error("消息平台 推送消息  error:{}",e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setMsg("推送消息异常");
		}
		return returnBean;
	}*/
}
