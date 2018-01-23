package com.pay.national.agent.core.service.wx.gate.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pay.national.agent.common.utils.HttpClientUtil;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.dao.wx.AccessTokenManagerMapper;
import com.pay.national.agent.core.service.wx.gate.WxService;
import com.pay.national.agent.model.constants.WeixinConstants;
import com.pay.national.agent.model.entity.AccessTokenManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service("wxService")
public class WxServiceImpl implements WxService {
    @Resource
    private AccessTokenManagerMapper accessTokenManagerMapper;

    @Override
    public  String getAccessToken(String appId, String appsecret) {
        LogUtil.info("geta服务getAccessToken请求参数 appid:{},appsecret:{}", appId, appsecret);
        StringBuilder sb = new StringBuilder();
        sb.append("grant_type=").append("client_credential").append("&appid=").append(appId).append("&secret=")
                .append(appsecret);
        String content = HttpClientUtil.sendGet(WeixinConstants.tokenUrl, sb.toString());
        LogUtil.info("获取AccessToken 返回结果content:{}", content);
        JSONObject obj = JSON.parseObject(content);
        String accessToken = (String) obj.get("access_token");
        LogUtil.info("获取AccessToken 返回结果accessToken:{}", accessToken);
        return accessToken;
    }

    /**
     * 获取有效的微信接口访问凭证
     * 由于获取操作存在 检查在运行策略 为保证操作的原子性，需要添加同步锁。
     *
     * @param appId
     * @param appsecret
     * @return
     */
    @Override
    public synchronized String getEffectAccessToken(String appId, String appsecret) {
        //查询数据库是否有生效的accessToken
        AccessTokenManager accessTokenManager = accessTokenManagerMapper.findAccessTokenByTime(new Date());
        if (accessTokenManager == null) {//accessToken已经失效
            accessTokenManager = new AccessTokenManager();
            String accessToken = getAccessToken(appId, appsecret
            );
            accessTokenManager.setAccessToken(accessToken);
            accessTokenManager.setCreateTime(new Date());
            accessTokenManager.setEffectTime(new Date());
            accessTokenManager.setExpireTime(new Date(System.currentTimeMillis()+60*60*1000));//过期时间往后推一个小时
            accessTokenManager.setOptimistic(0);
            accessTokenManager.setStatus("ENABLE");
            accessTokenManagerMapper.insert(accessTokenManager);
        }
        return accessTokenManager.getAccessToken();
    }
    public  String createQRCode(String accessToken,String content){
        LogUtil.info("geta服务createQrcode请求参数 accessToken:{},sence:{}", accessToken, content);
        StringBuilder sb = new StringBuilder(WeixinConstants.qrcodeUrl);
        sb.append(accessToken);
        String result = HttpClientUtil.sendPost(sb.toString(), content);
        LogUtil.info("geta服务createQrcode 返回结果:{}", result);
        JSONObject obj = JSON.parseObject(result);
        String ticket = (String) obj.get("ticket");
        return ticket;
    }


}
