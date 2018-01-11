package com.pay.national.agent.core.service.common.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pay.national.agent.common.utils.PropertiesLoader;
import com.pay.national.agent.core.service.common.SmsService;



/**
 * @Description: 一句话描述类的用法
 * @see: 需要参考的类
 * @version 2017年9月6日 上午11:11:50
 * @author zhenhui.liu
 */
@Service("smsService")
public class SmsServiceImpl implements SmsService{

	private static final Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);
	
	private static PropertiesLoader property = new PropertiesLoader("system.properties");
	
	private static final String APP_CODE = "nationalagent";
	
	private static final String busiType = "全民代理_短信";
	
	private static final String CAMEL_TOKEN = "wTW0FJwysX5rVdWCwY6pMg==";

	@Override
	public boolean sendCheckCodeSms(String phoneNo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sendFindPasswordCheckCode(String phoneNo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sendKaYouPosCheckCode(String phoneNo) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/*@Resource
	private CamelClientApi camelClient;
	
	@Override
	public boolean sendCheckCodeSms(String phoneNo) {
		boolean result = false;
		//读取注册短信发送内容
		String sendMessage = property.getProperty("user.sms.varifyCode");
		Integer  checkCodeExpire = 120; 
		//生成验证码 6位随机数
		Random r = new Random();
		DecimalFormat ddf = new DecimalFormat("#000000");
		Integer codeInt = r.nextInt(999999);
		String code = ddf.format(codeInt);
//		String code = "888888";
		logger.info("手机号：{} 验证码是：{}" ,phoneNo, code);
		sendMessage = MessageFormat.format(sendMessage, new Object[] { code });
		logger.info("password checkcode message:" + sendMessage);

		//组装发送短信参数
		Goods goods = new Goods();
        goods.setMessgType(MessageType.NOTE);
        goods.setMessgLevel(MessageLevel.INFO);
        goods.setAppCode(APP_CODE);
        goods.setBusiType(busiType);
        goods.setToken(CAMEL_TOKEN);
        goods.setContent(sendMessage);
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
				result = true;
			}else{
				result = false;
			}
		} catch (Exception e) {
			logger.error("Method sendSms error:",e);
		}
		logger.info("验证码放入redis："+RedisKeys.REGISTER_CHECK_CODE_PREFIX+phoneNo);
		RedisUtils.setEx(RedisKeys.REGISTER_CHECK_CODE_PREFIX+phoneNo, code,
				checkCodeExpire);
		return result;
	}

	@Override
	public boolean sendFindPasswordCheckCode(String phoneNo) {
		boolean result = false;
		//读取注册短信发送内容
		String sendMessage = property.getProperty("user.sms.findPassword");
		Integer  checkCodeExpire = 120; 
		//生成验证码 6位随机数
		Random r = new Random();
		DecimalFormat ddf = new DecimalFormat("#000000");
		Integer codeInt = r.nextInt(999999);
		String code = ddf.format(codeInt);
		logger.info("手机号：{} 验证码是：{}" ,phoneNo, code);
		sendMessage = MessageFormat.format(sendMessage, new Object[] { code });
		logger.info("sendFindPasswordCheckCode message:" + sendMessage);

		//组装发送短信参数
		Goods goods = new Goods();
        goods.setMessgType(MessageType.NOTE);
        goods.setMessgLevel(MessageLevel.INFO);
        goods.setAppCode(APP_CODE);
        goods.setBusiType(busiType);
        goods.setToken(CAMEL_TOKEN);
        goods.setContent(sendMessage);
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
				result = true;
			}else{
				result = false;
			}
		} catch (Exception e) {
			logger.error("Method sendSms error:",e);
		}
		logger.info("验证码放入redis："+RedisKeys.FIND_PASSWORD_CHECK_CODE_PREFIX+phoneNo);
		RedisUtils.setEx(RedisKeys.FIND_PASSWORD_CHECK_CODE_PREFIX+phoneNo, code,
				checkCodeExpire);
		return result;
	
	}

	@Override
	public boolean sendKaYouPosCheckCode(String phoneNo) {
		boolean result = false;
		//读取注册短信发送内容
		String sendMessage = property.getProperty("user.sms.varifyCode");
		Integer  checkCodeExpire = 120; 
		//生成验证码 6位随机数
		Random r = new Random();
		DecimalFormat ddf = new DecimalFormat("#000000");
		Integer codeInt = r.nextInt(999999);
		String code = ddf.format(codeInt);
//		String code = "888888";
		logger.info("手机号：{} 验证码是：{}" ,phoneNo, code);
		sendMessage = MessageFormat.format(sendMessage, new Object[] { code });
		logger.info("sendKaYouPosCheckCode message:" + sendMessage);

		//组装发送短信参数
		Goods goods = new Goods();
        goods.setMessgType(MessageType.NOTE);
        goods.setMessgLevel(MessageLevel.INFO);
        goods.setAppCode(APP_CODE);
        goods.setBusiType(busiType);
        goods.setToken(CAMEL_TOKEN);
        goods.setContent(sendMessage);
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
				result = true;
			}else{
				result = false;
			}
		} catch (Exception e) {
			logger.error("Method sendSms error:",e);
		}
		logger.info("验证码放入redis："+RedisKeys.KAYOU_POS_CHECK_CODE_PREFIX+phoneNo);
		RedisUtils.setEx(RedisKeys.KAYOU_POS_CHECK_CODE_PREFIX+phoneNo, code,
				checkCodeExpire);
		return result;
	}*/

}
