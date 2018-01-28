package com.pay.national.agent.core.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.pay.national.agent.common.annotation.NeedOpenId;
import com.pay.national.agent.common.constants.WeiXinConstant;
import com.pay.national.agent.common.utils.HttpClientUtil;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.common.utils.StringUtils;
import com.pay.national.agent.core.service.wx.WxUserInfoService;
import com.pay.national.agent.model.entity.WxUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * @author shuyan.qi
 */
public class Oauth2AccessTokenInterceptor extends HandlerInterceptorAdapter{
    @Autowired
    private WxUserInfoService wxUserInfoService;

    /**
     * 获取请求来源的真实ip
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LogUtil.info("OAuth2AccessTokenInterceptor 请求来源：" + getIpAddress(request));
        HandlerMethod handlerMethod = null;
        try {
            handlerMethod = (HandlerMethod) handler;
        } catch (Exception e) {
            LogUtil.error("OAuth2AccessTokenInterceptor error 转化 handlerMethod异常 err：{} ", e);
        }

        if (null == handlerMethod) {
            //让springMvc自己处理
            return super.preHandle(request, response, handler);
        }

        NeedOpenId annotation = handlerMethod.getMethodAnnotation(NeedOpenId.class);
        if (null != annotation) {
            String requestUrl = request.getRequestURL().toString();
            String redirectUrl = requestUrl + "?" + delCodeAndStateParam(request);

            //需要获取openId
            if (annotation.needOpenId()) {
                //是否显式授权
                boolean oauth2Flag = annotation.oauth2Flag();
                String code = request.getParameter("code");
                //code为空，获取微信用户授权
                if (code == null || code.length() == 0) {
                    //code为空，先去获取微信用户的授权,然后带code参数再次访问
                    String oAuth2Url = getOAuth2Url(redirectUrl, oauth2Flag);
                    response.sendRedirect(response.encodeRedirectURL(oAuth2Url));
                    return false;
                } else {
                    //通过code获取网页access_token
                    String accessTokenJson = getWxWebAccessToken(code);
                    Map<String, Object> accessTokenMap = JSONObject.parseObject(accessTokenJson, Map.class);

                    //获取accessToken失败，重新授权
                    if (null == accessTokenMap || null != accessTokenMap.get("errcode")) {
                        LogUtil.info("OAuth2AccessTokenInterceptor code:{} 获取access_token失败，重新获取code", code);
                        String oAuth2Url = getOAuth2Url(redirectUrl, oauth2Flag);
                        response.sendRedirect(response.encodeRedirectURL(oAuth2Url));
                        return false;
                    }

                    String openId = (String) accessTokenMap.get("openid");
                    request.getSession().setAttribute(WeiXinConstant.sessinoOpenIdKey,openId);

                    //显式授权获取用户信息，保存到数据库
                    if (oauth2Flag) {
                        String userInfoJson = getWxUserInfo(accessTokenMap.get("access_token"), openId);
                        Map<String, Object> userInfoMap = JSONObject.parseObject(userInfoJson, Map.class);

                        try {
                            if (userInfoMap != null && null == userInfoMap.get("errcode")) {
                                WxUserInfo wxUserInfo = wxUserInfoService.selectByOpenId(openId);
                                if (wxUserInfo != null) {
                                    wxUserInfo.setOptimistic(wxUserInfo.getOptimistic()+1);
                                    wxUserInfo.setCountry(userInfoMap.get("country").toString());
                                    wxUserInfo.setProvince(userInfoMap.get("province").toString());
                                    wxUserInfo.setCity(userInfoMap.get("city").toString());
                                    wxUserInfo.setLanguage(userInfoMap.get("language").toString());
                                    wxUserInfo.setNickname(userInfoMap.get("nickname").toString());
                                    wxUserInfo.setSex(userInfoMap.get("sex").toString());
                                    wxUserInfo.setHeadimgurl(userInfoMap.get("headimgurl").toString());
                                    wxUserInfoService.update(wxUserInfo);
                                }else{
                                    wxUserInfo = new WxUserInfo();
                                    wxUserInfo.setCountry(userInfoMap.get("country").toString());
                                    wxUserInfo.setProvince(userInfoMap.get("province").toString());
                                    wxUserInfo.setCity(userInfoMap.get("city").toString());
                                    wxUserInfo.setLanguage(userInfoMap.get("language").toString());
                                    wxUserInfo.setNickname(userInfoMap.get("nickname").toString());
                                    wxUserInfo.setSex(userInfoMap.get("sex").toString());
                                    wxUserInfo.setCreatetime(new Date());
                                    wxUserInfo.setHeadimgurl(userInfoMap.get("headimgurl").toString());
                                    wxUserInfo.setOpenid(openId);
                                    wxUserInfo.setCreatetime(new Date());
                                    wxUserInfo.setOptimistic(0);
                                    wxUserInfoService.insert(wxUserInfo);
                                }
                            }
                        } catch (Exception e) {
                            LogUtil.error("保存微信用户信息到数据库异常  e:{}",e);
                        }
                    }

                    //如果有需要，重定向到指定页面
                    String callUrl = request.getParameter("call_url");
                    if(StringUtils.isNotBlank(callUrl)){
                        StringBuffer url = new StringBuffer(callUrl);
                        url.append("?")
                           .append("openid=").append(openId);
                        response.sendRedirect(response.encodeRedirectURL(url.toString()));
                        return false;
                    }
                }
            }
        }
        return super.preHandle(request, response, handler);
    }

    /**
     * 将微信用户的openid加到需要跳转url的参数中
     * @param url
     * @param openId
     * @return
     */
    private String addOpenId2Url(String url, String openId) {
        if(!url.contains("?")){
            //url中不包含？
            return url+"?openId="+openId;
        }else{
            //url中包含？但是不包含&
            if(!url.contains("&")){
                return url+"openId="+openId;
            }else {
                //url中包含？也包含&，并且以&结尾
                if(url.endsWith("&")){
                    return url+"openId="+openId;
                }else{
                    //url中包含？也包含&，不是以&结尾
                    return url+"&openId="+openId;
                }
            }
        }
    }

    /**
     * 发送获取微信用户信息的http请求
     *
     * @param access_token
     * @param openid
     * @return
     */
    private String getWxUserInfo(Object access_token, Object openid) {
        StringBuffer userInfoUrl = new StringBuffer();
        userInfoUrl.append(WeiXinConstant.OAUTH2_USERINFO_URL)
                .append("?access_token=")
                .append(access_token)
                .append("&openid=")
                .append(openid)
                .append("&lang=zh_CN");

        String url = userInfoUrl.toString();
        LogUtil.info("OAuth2AccessTokenInterceptor 获取微信用户信息url：{}", url);
        String result = HttpClientUtil.sendGet(userInfoUrl.toString(),null);
        LogUtil.info("OAuth2AccessTokenInterceptor 获取微信用户信息 result：{}", result);
        return result;
    }

    /**
     * 发送获取微信授权网页accessToken的http请求
     *
     * @param code
     * @return
     */
    private String getWxWebAccessToken(String code) {
        StringBuffer accessTokenUrl = new StringBuffer();
        accessTokenUrl.append(WeiXinConstant.OAUTH2_ACCESSTOKEN_URL)
                .append("?appid=")
                .append(WeiXinConstant.APP_ID)
                .append("&secret=")
                .append(WeiXinConstant.APP_SECRET)
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code");

        String url = accessTokenUrl.toString();
        LogUtil.info("OAuth2AccessTokenInterceptor 获取微信授权页面accessToken url：{}", url);
        String result = HttpClientUtil.sendGet(accessTokenUrl.toString(),null);
        LogUtil.info("OAuth2AccessTokenInterceptor 获取微信授权页面accessToken result：{}", result);
        return result;
    }

    /**
     * 获取请求微信用户授权url
     *
     * @param redirectUrl
     * @param oauth2Flag
     * @return
     */
    private String getOAuth2Url(String redirectUrl, boolean oauth2Flag) {
        if (redirectUrl != null) {
            try {
                redirectUrl = URLEncoder.encode(redirectUrl, "utf-8");
            } catch (UnsupportedEncodingException e) {
                LogUtil.error("urlEncode异常!.oauthUrl:{}", e);
            }
        }
        StringBuffer oAuth2Url = new StringBuffer();
        oAuth2Url.append(WeiXinConstant.OAUTH2_AUTHORIZE_URL)
                .append("?appid=")
                .append(WeiXinConstant.APP_ID)
                .append("&redirect_uri=")
                .append(redirectUrl)
                .append("&response_type=code&scope=");

        if (oauth2Flag) {
            //显式授权
            oAuth2Url.append("snsapi_userinfo");
        } else {
            //隐式授权
            oAuth2Url.append("snsapi_base");
        }
        oAuth2Url.append("&state=")
                .append(UUID.randomUUID())
                .append("#wechat_redirect");
        String url = oAuth2Url.toString();
        LogUtil.info("OAuth2AccessTokenInterceptor 请求用户授权URL：{}", url);
        return url;
    }

    /**
     * 排除掉微信传的code和state请求参数
     *
     * @param request
     * @return
     */
    private String delCodeAndStateParam(HttpServletRequest request) {
        String requestParam = "";
        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuffer param = new StringBuffer();
        if (parameterMap != null) {
            Iterator<String> iterator = parameterMap.keySet().iterator();
            while (iterator.hasNext()) {
                String mapKey = iterator.next();
                //排除掉code参数和state参数
                if (!"code".equals(mapKey) && !"state".equals(mapKey)) {
                    String[] vals = parameterMap.get(mapKey);
                    for (String val : vals) {
                        param.append(mapKey)
                                .append("=")
                                .append(val)
                                .append("&");
                    }
                }
            }
            if (param.length() > 1) {
                requestParam = param.substring(0, param.length() - 1);
            } else {
                requestParam = param.toString();
            }
        }
        return requestParam;
    }
	
}
