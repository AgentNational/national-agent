package com.pay.national.agent.common.constants;

/**
 * Created by shuyan.qi on 2018/1/13.
 */
public class WeiXinConstant {
    public static final String COMMON_ACCESS_TOKEN_CACHE ="wx_access_token";//微信 access_token在缓存中的key
    public static final String APP_ID ="wx1e86ade34d844bf3";
    public static final String APP_SECRET ="58d90bc03812beba9628bad7f460c3ba";

    /**
     * 微信用户同意授权，获取code
     */
    public static final String OAUTH2_AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize";

    /**
     * 获取授权网页access_token
     */
    public static final String OAUTH2_ACCESSTOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";

    /**
     * 拉取用户信息
     */
    public static final String OAUTH2_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo";
}
