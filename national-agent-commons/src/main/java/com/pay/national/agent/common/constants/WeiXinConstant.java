package com.pay.national.agent.common.constants;

/**
 * Created by shuyan.qi on 2018/1/13.
 */
public class WeiXinConstant {
    public static final String COMMON_ACCESS_TOKEN_CACHE ="wx_access_token";//微信 access_token在缓存中的key
//    public static final String APP_ID ="wx7e3931abd0f15feb";
//    public static final String APP_SECRET ="fc410a455971f8ebef1262ecacf53ed4";
    //微信公众号配置
    public static final String APP_ID ="wx9e242df43f4eb301";
    public static final String APP_SECRET ="7bedbf2b2ca2c380e5a891096764f9a7";
    public static final String TOKEN = "liuzhenhui";

    /**
     * 微信用户同意授权，获取code
     */
    public static final String OAUTH2_AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize";

    /**
     * 获取jsApiTicketUrl
     */
    public static final String jsApiTicketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";

    /**
     * 获取授权网页access_token
     */
    public static final String OAUTH2_ACCESSTOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";

    /**
     * 拉取用户信息
     */
    public static final String OAUTH2_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo";

    /** 永久二维码 **/
    public static final String qrcodeUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";// 永久二维码

    public static final String ticketUrl= "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=";

    /**
     * 获取访问微信接口的token地址
     */
    public static final String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token";

    public static String sessinoOpenIdKey ="SESSION_OPEN_ID_KEY";

    /**
     * 企业付款url
     */
    public static String transfersUrl = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
    public static final String payOrderUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";// 公众号支付
    public static final String queryPayOrderUrl = "https://api.mch.weixin.qq.com/pay/orderquery";// 公众号支付订单查询

    /**
     * 拉取用户基本信息
     */
    public static final String getUserInfoUrl = "https://api.weixin.qq.com/cgi-bin/user/info";

}
