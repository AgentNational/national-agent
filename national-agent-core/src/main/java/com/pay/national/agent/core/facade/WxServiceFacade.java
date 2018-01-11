package com.pay.national.agent.core.facade;

/**
 * @Description: 微信服务相关dubbo接口
 * @see: 需要参考的类
 * @version 2017年11月27日 下午6:41:27
 * @author zhenhui.liu
 */
public interface WxServiceFacade {

	/**
	 * @Description 获取推广二维码
	 * @param accessToken
	 * @param content
	 * @return
	 * @see 需要参考的类或方法
	 */
	String createQrcode(String accessToken, String content);

}
