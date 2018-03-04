package com.pay.national.agent.core.service.common.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.common.utils.PropertiesLoader;
import com.pay.national.agent.core.service.common.CheckCodeInfoService;
import com.pay.national.agent.core.service.common.SmsService;
import com.pay.national.agent.model.entity.CheckCodeInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


/**
 * @Description: 一句话描述类的用法
 * @see: 需要参考的类
 * @version 2017年9月6日 上午11:11:50
 * @author zhenhui.liu
 */
@Service("smsService")
public class SmsServiceImpl implements SmsService{


	private static PropertiesLoader property = new PropertiesLoader("system.properties");


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


	//产品名称:云通信短信API产品,开发者无需替换
	static final String product = "Dysmsapi";
	//产品域名,开发者无需替换
	static final String domain = "dysmsapi.aliyuncs.com";

	// TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
	static final String accessKeyId = "LTAImcRc77EQt4iY";
	static final String accessKeySecret = "sVQb0IZu6ZS2iVThFQn2rlOd6sdv6s";

	@Resource
	private  CheckCodeInfoService checkCodeInfoService;

	public   Boolean sendSms(String openId,String phoneNo) throws ClientException {

		//可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		//初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);

		//组装请求对象-具体描述见控制台-文档部分内容
		SendSmsRequest request = new SendSmsRequest();
		//必填:待发送手机号
		request.setPhoneNumbers(phoneNo);
		//必填:短信签名-可在短信控制台中找到
		request.setSignName("全民代理");
		//必填:短信模板-可在短信控制台中找到
		request.setTemplateCode("SMS_125022164");

		//生成验证码 6位随机数
		Random r = new Random();
		DecimalFormat ddf = new DecimalFormat("#000000");
		Integer codeInt = r.nextInt(999999);
		String code = ddf.format(codeInt);
		//可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		request.setTemplateParam("{\"code\":"+code+"}");

		SendSmsResponse sendSmsResponse;
		try{
			sendSmsResponse = acsClient.getAcsResponse(request);
			//判断是否发送成功
			if(sendSmsResponse.getCode().equals("OK")){//发送成功
				//当前时间往后推五分钟
				CheckCodeInfo checkCodeInfo = new CheckCodeInfo();
				checkCodeInfo.setOpenId(openId);
				checkCodeInfo.setAbleStatus("ENABLE");
				checkCodeInfo.setCode(code);
				checkCodeInfo.setOptimistic(0);
				checkCodeInfo.setPhoneNo(phoneNo);
				checkCodeInfo.setCreateTime(new Date());
				checkCodeInfo.setEffectTime(new Date());
				long currentTime = System.currentTimeMillis() ;
				currentTime +=5*60*1000;
				checkCodeInfo.setExpireTime(new Date(currentTime));
				checkCodeInfoService.insert(checkCodeInfo);
				return true;
			}else{
				return  false;
			}
		}catch (Exception e){
			LogUtil.error("发送短信验证码异常",e);
			return false;
		}
	}


	public  static QuerySendDetailsResponse querySendDetails(String bizId) throws ClientException {

		//可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		//初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);

		//组装请求对象
		QuerySendDetailsRequest request = new QuerySendDetailsRequest();
		//必填-号码
		request.setPhoneNumber("15000000000");
		//可选-流水号
		request.setBizId(bizId);
		//必填-发送日期 支持30天内记录查询，格式yyyyMMdd
		SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
		request.setSendDate(ft.format(new Date()));
		//必填-页大小
		request.setPageSize(10L);
		//必填-当前页码从1开始计数
		request.setCurrentPage(1L);

		//hint 此处可能会抛出异常，注意catch
		QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);

		return querySendDetailsResponse;
	}

	public static void main(String[] args) throws ClientException, InterruptedException {

		SmsServiceImpl smsService = new SmsServiceImpl();
		//发短信
		Boolean aaa = smsService.sendSms("fdsagsfagfdas","17600695056");
		System.out.println(aaa);
		/*System.out.println("短信接口返回的数据----------------");
		System.out.println("Code=" + response.getCode());
		System.out.println("Message=" + response.getMessage());
		System.out.println("RequestId=" + response.getRequestId());
		System.out.println("BizId=" + response.getBizId());

		Thread.sleep(3000L);

		//查明细
		if(response.getCode() != null && response.getCode().equals("OK")) {
			QuerySendDetailsResponse querySendDetailsResponse = querySendDetails(response.getBizId());
			System.out.println("短信明细查询接口返回数据----------------");
			System.out.println("Code=" + querySendDetailsResponse.getCode());
			System.out.println("Message=" + querySendDetailsResponse.getMessage());
			int i = 0;
			for(QuerySendDetailsResponse.SmsSendDetailDTO smsSendDetailDTO : querySendDetailsResponse.getSmsSendDetailDTOs())
			{
				System.out.println("SmsSendDetailDTO["+i+"]:");
				System.out.println("Content=" + smsSendDetailDTO.getContent());
				System.out.println("ErrCode=" + smsSendDetailDTO.getErrCode());
				System.out.println("OutId=" + smsSendDetailDTO.getOutId());
				System.out.println("PhoneNum=" + smsSendDetailDTO.getPhoneNum());
				System.out.println("ReceiveDate=" + smsSendDetailDTO.getReceiveDate());
				System.out.println("SendDate=" + smsSendDetailDTO.getSendDate());
				System.out.println("SendStatus=" + smsSendDetailDTO.getSendStatus());
				System.out.println("Template=" + smsSendDetailDTO.getTemplateCode());
			}
			System.out.println("TotalCount=" + querySendDetailsResponse.getTotalCount());
			System.out.println("RequestId=" + querySendDetailsResponse.getRequestId());
		}*/

	}


}
