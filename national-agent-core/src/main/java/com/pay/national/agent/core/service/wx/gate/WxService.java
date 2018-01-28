package com.pay.national.agent.core.service.wx.gate;

import java.util.Map;

/**
 * 项目调用微信相关接口
 */
public interface WxService {

    /**
     * 获取微信接口全局调用凭据
     * @return
     */
    String getAccessToken(String appId,String appsecret);

    /**
     * 获取生效的AccessToken
     * @param appId
     * @param appsecret
     * @return
     */
    String getEffectAccessToken(String appId,String appsecret);

    /**
     * 生成永久生效的推广带参数二维码
     * @param accessToken
     * @param content
     * @return
     */
    String createQRCode(String accessToken,String content);


    /**
     * @Description 创建企业付款
     * @param respXml
     *            企业付款请求对象
     * @return
     * @throws Exception
     * @see
     */
    Map<String, String> createEnterPrisePayment(String respXml) throws Exception;


    /**
     * @Description  创建微信支付订单
     * @param xml
     * @return
     * @see
     */
    String createWxPayOrder(String xml);

    /**
     * @Description  获取微信支付订单
     * @param xml
     * @return
     * @see
     */
    String getWxPayOrder(String xml);
}
