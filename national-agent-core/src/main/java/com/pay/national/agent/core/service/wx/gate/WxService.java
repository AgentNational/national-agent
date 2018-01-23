package com.pay.national.agent.core.service.wx.gate;

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

    String createQRCode(String accessToken,String content);
}
