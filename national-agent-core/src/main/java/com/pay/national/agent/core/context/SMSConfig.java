package com.pay.national.agent.core.context;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Description: 短信服务配置
 * @see: SMSConfig 此处填写需要参考的类
 * @version 2016年8月24日 下午6:24:20
 * @author qinji.xu
 */
@Configuration
@PropertySource("classpath:/sms.properties")
public class SMSConfig {
/*
	@Value("${sms.server.host}")
	private String host;
	@Value("${sms.server.port}")
	private int port;

	@Bean
	public CamelClientApiImpl camelClient()
	{
		CamelClientApiImpl api = CamelClientApiImpl.getInstance();
		api.setHost(host);
		api.setPort(port);
		return api;
	}*/
}
