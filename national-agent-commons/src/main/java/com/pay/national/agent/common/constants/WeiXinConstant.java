package com.pay.national.agent.common.constants;

/**
 * Created by shuyan.qi on 2018/1/13.
 */
public class WeiXinConstant {
    public static final String COMMON_ACCESS_TOKEN_CACHE ="wx_access_token";//微信 access_token在缓存中的key
    public static final String APP_ID ="wx7e3931abd0f15feb";
    public static final String APP_SECRET ="fc410a455971f8ebef1262ecacf53ed4";

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
